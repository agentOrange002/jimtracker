package com.app.sys.jimtracker.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.sys.jimtracker.dto.IssueDto;
import com.app.sys.jimtracker.dto.IssueLogDto;
import com.app.sys.jimtracker.dto.UserDto;
import com.app.sys.jimtracker.entity.IssueEntity;
import com.app.sys.jimtracker.entity.IssueLogEntity;
import com.app.sys.jimtracker.entity.UserEntity;
import com.app.sys.jimtracker.repository.IssueLogRepository;
import com.app.sys.jimtracker.repository.IssueRepository;
import com.app.sys.jimtracker.repository.UserRepository;
import com.app.sys.jimtracker.service.IssueLogService;
import com.app.sys.jimtracker.tool.Utils;

@Service
public class IssueLogServiceImpl implements IssueLogService {
	@Autowired
	IssueLogRepository issueLogRepository;

	@Autowired
	IssueRepository issueRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	Utils utils;

	@Override
	public IssueLogDto saveIssueLog(IssueLogDto issueLog, String issueId, String userId) {
		IssueLogDto returnValue = new IssueLogDto();

		/* ERROR */
		ModelMapper amodelMapper = new ModelMapper();
		amodelMapper.getConfiguration().setAmbiguityIgnored(true);
		IssueEntity issueEnity = issueRepository.findIssueByIssueId(issueId);
		UserEntity userEntity = userRepository.findByUserId(userId);
		/* ERROR */

		IssueLogEntity entity = amodelMapper.map(issueLog, IssueLogEntity.class);
		entity.setIssueDetails(issueEnity);
		entity.setIssueLogUserDetails(userEntity);
		entity.setIssueLogId(utils.generateIssueLogId(10));
		entity.setLogDate(new Date());
		IssueLogEntity saveEntity = issueLogRepository.save(entity);

		IssueEntity ie = saveEntity.getIssueDetails();
		UserEntity ue = saveEntity.getIssueLogUserDetails();

		IssueDto issueDto = new ModelMapper().map(ie, IssueDto.class);
		UserDto userDto = new ModelMapper().map(ue, UserDto.class);

		returnValue = new ModelMapper().map(saveEntity, IssueLogDto.class);
		returnValue.setIssueDto(issueDto);
		returnValue.setUserDto(userDto);
		return returnValue;
	}

	@Override
	public List<IssueLogDto> getIssueLogById(String issueId) {
		IssueEntity ie = issueRepository.findIssueByIssueId(issueId);
		List<IssueLogDto> returnValue = new ArrayList<IssueLogDto>();
		List<IssueLogEntity> listentity = issueLogRepository.findAllByIssueDetails(ie);
		ModelMapper mapper = new ModelMapper();
		for (IssueLogEntity entity : listentity) {

			IssueLogDto dto = mapper.map(entity, IssueLogDto.class);

			ModelMapper mm = new ModelMapper();
			UserEntity uent = entity.getIssueLogUserDetails();
			IssueEntity ient = entity.getIssueDetails();
			UserDto udto = mm.map(uent, UserDto.class);
			IssueDto idto = mm.map(ient, IssueDto.class);

			dto.setUserDto(udto);
			dto.setIssueDto(idto);
			returnValue.add(dto);
		}
		return returnValue;
	}

}
