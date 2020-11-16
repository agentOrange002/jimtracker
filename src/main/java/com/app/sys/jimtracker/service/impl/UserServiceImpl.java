package com.app.sys.jimtracker.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.sys.jimtracker.dto.UserDto;
import com.app.sys.jimtracker.entity.PasswordResetTokenEntity;
import com.app.sys.jimtracker.entity.RoleEntity;
import com.app.sys.jimtracker.entity.UserEntity;
import com.app.sys.jimtracker.exceptions.AppServiceException;
import com.app.sys.jimtracker.repository.AddressRepository;
import com.app.sys.jimtracker.repository.AuthorityRepository;
import com.app.sys.jimtracker.repository.PasswordResetTokenRepository;
import com.app.sys.jimtracker.repository.RoleRepository;
import com.app.sys.jimtracker.repository.UserRepository;
import com.app.sys.jimtracker.security.UserPrincipal;
import com.app.sys.jimtracker.service.UserService;
import com.app.sys.jimtracker.tool.AmazonSES;
import com.app.sys.jimtracker.tool.Utils;
import com.app.sys.jimtracker.ui.model.response.ErrorMessages;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired 
	AddressRepository addressRepository;

	@Autowired
	AuthorityRepository authorityRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	Utils utils;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	PasswordResetTokenRepository passwordResetTokenRepository;

	@Autowired
	AmazonSES amazonSES;

	@Override
	public UserDto createUser(UserDto user) {
		if (userRepository.findByEmail(user.getEmail()) != null)
			throw new AppServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());

		UserEntity userEntity = new ModelMapper().map(user, UserEntity.class);
		RoleEntity roleUser = roleRepository.findByName("ROLE_USER");
		String publicUserId = utils.generateUserId(30);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setUserId(publicUserId);
		userEntity.setEmailVerificationToken(utils.generateEmailVerificationToken(publicUserId));
		userEntity.setEmailVerificationStatus(false);
		userEntity.setRoles(Arrays.asList(roleUser));
		UserEntity storedUserDetails = userRepository.save(userEntity);
		UserDto returnValue = new UserDto();
		returnValue = new ModelMapper().map(storedUserDetails, UserDto.class);
		amazonSES.verifyEmail(returnValue);
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);
		return new UserPrincipal(userEntity);
		/*
		 * return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(),
		 * userEntity.getEmailVerificationStatus(), true, true, true, new
		 * ArrayList<>());
		 */
	}

	@Override
	public UserDto getUser(String email) {
		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)throw new UsernameNotFoundException(email);		
		BeanUtils.copyProperties(userEntity,returnValue);	
		return returnValue;
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null) throw new AppServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());		
		return new ModelMapper().map(userEntity, UserDto.class);
	}

	@Override
	public UserDto updateUser(String userId, UserDto user) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)	throw new AppServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		userEntity.setFirstName(user.getFirstName());
		userEntity.setMiddleName(user.getMiddleName());
		userEntity.setLastName(user.getLastName());
		userEntity.setFullName(user.getFullName());
		UserEntity updatedUserEntity = userRepository.save(userEntity);
		return new ModelMapper().map(updatedUserEntity, UserDto.class);
	}

	@Override
	public void deleteUser(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)
			throw new AppServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		userRepository.delete(userEntity);
	}

	@Override
	public boolean verifyEmailToken(String token) {
		boolean returnValue = false;
		UserEntity userEntity = userRepository.findUserByEmailVerificationToken(token);
		if (userEntity != null) {
			boolean hastokenExpired = Utils.hasTokenExpired(token);
			if (!hastokenExpired) {
				userEntity.setEmailVerificationToken(null);
				userEntity.setEmailVerificationStatus(Boolean.TRUE);
				userRepository.save(userEntity);
				returnValue = true;
			}
		}

		return returnValue;
	}

	@Override
	public boolean requestPasswordReset(String email) {
		boolean returnValue = false;
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null) {
			return returnValue;
		}
		String token = new Utils().generatePasswordResetToken(userEntity.getUserId());
		PasswordResetTokenEntity passwordResetTokenEntity = new PasswordResetTokenEntity();
		passwordResetTokenEntity.setToken(token);
		passwordResetTokenEntity.setUserDetails(userEntity);
		passwordResetTokenRepository.save(passwordResetTokenEntity);
		returnValue = new AmazonSES().sendPasswordResetRequest(userEntity.getFirstName(), userEntity.getEmail(), token);
		return returnValue;
	}

	@Override
	public boolean resetPassword(String token, String password) {
		boolean returnValue = false;

		if (Utils.hasTokenExpired(token)) {
			return returnValue;
		}

		PasswordResetTokenEntity passwordResetTokenEntity = passwordResetTokenRepository.findByToken(token);

		if (passwordResetTokenEntity == null) {
			return returnValue;
		}

		// Prepare new password
		String encodedPassword = bCryptPasswordEncoder.encode(password);

		// Update User password in database
		UserEntity userEntity = passwordResetTokenEntity.getUserDetails();
		userEntity.setEncryptedPassword(encodedPassword);
		UserEntity savedUserEntity = userRepository.save(userEntity);

		// Verify if password was saved successfully
		if (savedUserEntity != null && savedUserEntity.getEncryptedPassword().equalsIgnoreCase(encodedPassword)) {
			returnValue = true;
		}

		// Remove Password Reset token from database
		passwordResetTokenRepository.delete(passwordResetTokenEntity);

		return returnValue;
	}

	@Override
	public boolean authResetPassword(String id, String password) {
		boolean returnValue = false;
		String encodedPassword = bCryptPasswordEncoder.encode(password);

		UserEntity userEntity = userRepository.findByUserId(id);
		userEntity.setEncryptedPassword(encodedPassword);
		UserEntity savedUserEntity = userRepository.save(userEntity);

		// Verify if password was saved successfully
		if (savedUserEntity != null && savedUserEntity.getEncryptedPassword().equalsIgnoreCase(encodedPassword)) {
			returnValue = true;
		}

		return returnValue;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<UserDto> returnList = new ArrayList<UserDto>();
		List<UserEntity> entityList = userRepository.findAll();
		ModelMapper mapper = new ModelMapper();
		for (UserEntity entity : entityList) {
			UserDto dto = mapper.map(entity, UserDto.class);
			returnList.add(dto);
		}
		return returnList;
	}

	@Override
	public List<UserDto> getAllUsersByRole(String roleName) {
		RoleEntity role = roleRepository.findByName(roleName);
		List<UserDto> returnList = new ArrayList<UserDto>();
		List<UserEntity> entityList = userRepository.findByRoles(role);
		ModelMapper mapper = new ModelMapper();
		for (UserEntity entity : entityList) {
			UserDto dto = mapper.map(entity, UserDto.class);
			returnList.add(dto);
		}
		return returnList;
	}
}
