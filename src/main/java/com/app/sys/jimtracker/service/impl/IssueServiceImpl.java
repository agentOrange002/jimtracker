package com.app.sys.jimtracker.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.sys.jimtracker.dto.DocumentIssueDto;
import com.app.sys.jimtracker.dto.IssueDto;
import com.app.sys.jimtracker.dto.UserDto;
import com.app.sys.jimtracker.entity.CategoryEntity;
import com.app.sys.jimtracker.entity.IssueEntity;
import com.app.sys.jimtracker.entity.UserEntity;
import com.app.sys.jimtracker.exceptions.AppServiceException;
import com.app.sys.jimtracker.repository.CategoryRepository;
import com.app.sys.jimtracker.repository.IssueRepository;
import com.app.sys.jimtracker.repository.UserRepository;
import com.app.sys.jimtracker.service.IssueService;
import com.app.sys.jimtracker.tool.Utils;
import com.app.sys.jimtracker.ui.model.response.ErrorMessages;

@Service
public class IssueServiceImpl implements IssueService {
	@Autowired
	IssueRepository issueRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	Utils utils;

	@Override
	public boolean publicSaveIssue(IssueDto issue) {
		boolean returnValue = false;
		IssueEntity issueEntity = new IssueEntity();
		BeanUtils.copyProperties(issue, issueEntity);
		issueEntity.setDateReported(new Date());
		issueEntity.setIssueId(utils.generateIssueId(10));
		issueEntity.setIssueStatus("CREATED");
		IssueEntity storedissueEntity = issueRepository.save(issueEntity);
		if (storedissueEntity != null) {
			returnValue = true;
		}
		return returnValue;
	}

	@Override
	public IssueDto saveIssue(IssueDto issue, String userId) {
		IssueDto returnValue = new IssueDto();
		UserEntity userEntity = userRepository.findByUserId(userId);
		IssueEntity issueEntity = new IssueEntity();
		BeanUtils.copyProperties(issue, issueEntity);
		issueEntity.setDateReported(new Date());
		issueEntity.setIssueId(utils.generateIssueId(10));
		issueEntity.setIssueStatus("CREATED");
		issueEntity.setIssueUserDetails(userEntity);
		IssueEntity storedissueEntity = issueRepository.save(issueEntity);
		UserEntity owner = storedissueEntity.getIssueUserDetails();
		UserDto ownerDto = new ModelMapper().map(owner, UserDto.class);
		returnValue = new ModelMapper().map(storedissueEntity, IssueDto.class);
		returnValue.setIssueUserDetails(ownerDto);
		return returnValue;
	}

	@Override
	public IssueDto getIssueByIssueId(String issueId) {
		IssueDto returnValue = new IssueDto();
		IssueEntity issueEntity = issueRepository.findIssueByIssueId(issueId);
		if (issueEntity == null)
			throw new AppServiceException(ErrorMessages.ISSUE_NOT_FOUND.getErrorMessage());
		UserEntity userEntity = issueEntity.getIssueUserDetails();
		UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);
		returnValue = new ModelMapper().map(issueEntity, IssueDto.class);
		returnValue.setIssueUserDetails(userDto);
		return returnValue;
	}

	@Override
	public IssueDto updateIssue(String issueId, IssueDto issue) {
		IssueDto returnValue = new IssueDto();
		IssueEntity issueEntity = issueRepository.findIssueByIssueId(issueId);
		if (issueEntity == null)
			throw new AppServiceException(ErrorMessages.ISSUE_NOT_FOUND.getErrorMessage());
		issueEntity.setDescription(issue.getDescription());
		issueEntity.setSubject(issue.getSubject());
		/* issueEntity.setFkUserId(issue.getFkUserId()); */
		IssueEntity updateEntity = issueRepository.save(issueEntity);
		UserEntity userEntity = updateEntity.getIssueUserDetails();
		UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);
		returnValue = new ModelMapper().map(updateEntity, IssueDto.class);
		returnValue.setIssueUserDetails(userDto);
		return returnValue;
	}

	@Override
	public void deleteIssue(String issueId) {
		IssueEntity issueEntity = issueRepository.findIssueByIssueId(issueId);
		if (issueEntity == null)
			throw new AppServiceException(ErrorMessages.ISSUE_NOT_FOUND.getErrorMessage());
		issueRepository.delete(issueEntity);
	}

	@Override
	public List<IssueDto> getIssueList() {
		List<IssueDto> returnValue = new ArrayList<IssueDto>();
		List<IssueEntity> listEntity = issueRepository.findAll();
		ModelMapper modelMapper = new ModelMapper();
		for (IssueEntity entity : listEntity) {
			returnValue.add(modelMapper.map(entity, IssueDto.class));
		}
		return returnValue;
	}

	@Override
	public IssueDto assignSupport(String issueId, String userId, String categoryName) {
		IssueDto returnDto = new IssueDto();
		IssueEntity issue = issueRepository.findIssueByIssueId(issueId);
		UserEntity user = userRepository.findByUserId(userId);
		CategoryEntity category = categoryRepository.findByName(categoryName);
		issue.setIssueStatus("OPENED");
		issue.setDateOpened(new Date());
		issue.setSupportUserDetails(user);
		issue.setCategoryDetails(category);
		IssueEntity updatedIssue = issueRepository.save(issue);
		UserEntity owner = updatedIssue.getIssueUserDetails();
		UserEntity support = updatedIssue.getSupportUserDetails();
		UserDto ownerDto = new ModelMapper().map(owner, UserDto.class);
		UserDto supportDto = new ModelMapper().map(support, UserDto.class);
		returnDto = new ModelMapper().map(updatedIssue, IssueDto.class);
		returnDto.setIssueUserDetails(ownerDto);
		returnDto.setSupportUserDetails(supportDto);
		return returnDto;
	}

	@Override
	public IssueDto ownedthisIssue(String issueId, String userId) {
		IssueEntity issue = issueRepository.findIssueByIssueId(issueId);
		if (issue == null)
			throw new AppServiceException(ErrorMessages.ISSUE_NOT_FOUND.getErrorMessage());
		UserEntity user = userRepository.findByUserId(userId);
		IssueEntity entity = issueRepository.findIssueByIssueIdAndIssueUserDetails(issueId, user);
		if (entity != null)
			throw new AppServiceException(ErrorMessages.ISSUE_HAS_ALREADY_OWNDED.getErrorMessage());
		IssueDto returnDto = new IssueDto();
		String name = user.getFullName();
		/*
		 * String name = new StringBuilder(user.getFirstName() + ' ' +
		 * user.getLastName()).toString();
		 */
		issue.setIssueUserDetails(user);
		issue.setReportedBy(name);
		IssueEntity updatedIssue = issueRepository.save(issue);
		UserEntity updatedUser = updatedIssue.getIssueUserDetails();
		UserDto updateduserdto = new ModelMapper().map(updatedUser, UserDto.class);
		returnDto = new ModelMapper().map(updatedIssue, IssueDto.class);
		returnDto.setIssueUserDetails(updateduserdto);
		return returnDto;
	}

	@Override
	public List<DocumentIssueDto> getIssueDocuments(String emailProvided) {
		List<DocumentIssueDto> returnList = new ArrayList<DocumentIssueDto>();
		List<IssueEntity> listEntity = issueRepository.findByEmailProvided(emailProvided);
		ModelMapper modelMapper = new ModelMapper();
		for (IssueEntity entity : listEntity) {
			/*
			 * DocumentIssueDto dto = new DocumentIssueDto(); dto = modelMapper.map(entity,
			 * DocumentIssueDto.class);
			 * dto.setCategory(entity.getCategoryDetails().getName());
			 * dto.setIssueUserDetails(entity.getIssueUserDetails().getUserId());
			 * dto.setSupportUserDetails(entity.getSupportUserDetails().getUserId());
			 */
			returnList.add(modelMapper.map(entity, DocumentIssueDto.class));
		}
		return returnList;
	}

}
