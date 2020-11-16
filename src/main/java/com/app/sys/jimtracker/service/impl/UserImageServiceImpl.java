package com.app.sys.jimtracker.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.sys.jimtracker.dto.UserImageDto;
import com.app.sys.jimtracker.entity.UserEntity;
import com.app.sys.jimtracker.entity.UserImageEntity;
import com.app.sys.jimtracker.repository.UserImageRepository;
import com.app.sys.jimtracker.repository.UserRepository;
import com.app.sys.jimtracker.service.UserImageService;

@Service
public class UserImageServiceImpl implements UserImageService {
	@Autowired
	UserImageRepository userImageRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public UserImageDto uploadUserImage(UserImageDto userImageDto) {		
		UserImageEntity userImageEntity = new ModelMapper().map(userImageDto,UserImageEntity.class);	
		UserImageEntity saveUserImage = userImageRepository.save(userImageEntity);		
		return new ModelMapper().map(saveUserImage, UserImageDto.class);
	}

	@Override
	public UserImageDto getUserImageByUserId(String userId) {		
		UserEntity userEntity = userRepository.findByUserId(userId);			
		UserImageEntity userImageEntity  = userImageRepository.findByUserImageDetails(userEntity);		
		return new ModelMapper().map(userImageEntity,UserImageDto.class);
	}

	@Override
	public UserImageDto updateUserImage(UserImageDto transferDto, String userId) {
		byte[] imageBytes = transferDto.getImage();	
		UserEntity userEntity = userRepository.findByUserId(userId);		
		UserImageEntity userImageEntity = userImageRepository.findByUserImageDetails(userEntity);
		userImageEntity.setImage(imageBytes);
		UserImageEntity updateduserImageEntity = userImageRepository.save(userImageEntity);	
		return new ModelMapper().map(updateduserImageEntity,UserImageDto.class);
	}

}
