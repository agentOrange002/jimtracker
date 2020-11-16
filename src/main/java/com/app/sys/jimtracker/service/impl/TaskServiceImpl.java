package com.app.sys.jimtracker.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.sys.jimtracker.dto.TaskDto;
import com.app.sys.jimtracker.entity.TaskEntity;
import com.app.sys.jimtracker.entity.TicketEntity;
import com.app.sys.jimtracker.entity.UserEntity;
import com.app.sys.jimtracker.exceptions.AppServiceException;
import com.app.sys.jimtracker.repository.TaskRepository;
import com.app.sys.jimtracker.repository.TicketRepository;
import com.app.sys.jimtracker.repository.UserRepository;
import com.app.sys.jimtracker.service.TaskService;
import com.app.sys.jimtracker.tool.Utils;
import com.app.sys.jimtracker.ui.model.response.ErrorMessages;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	Utils utils;
	
	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public TaskDto createTask(TaskDto dto, String ticketid, String userid) {		
		TicketEntity ticketEntity = ticketRepository.findByTicketId(ticketid);	
		if (ticketEntity == null)throw new AppServiceException(ErrorMessages.TASKID_NOT_FOUND.getErrorMessage());
		UserEntity userEntity = userRepository.findByUserId(userid);		
		TaskEntity entity = new TaskEntity();
		entity.setSubject(dto.getSubject());
		entity.setDescription(dto.getDescription());
		entity.setTaskId(utils.generateTaskId(10));
		entity.setDateOpened(new Date());
		entity.setTicketDetails(ticketEntity);
		entity.setUsertaskDetails(userEntity);
		TaskEntity saveEntity = taskRepository.save(entity);
		return new ModelMapper().map(saveEntity, TaskDto.class);		
	}

	@Override
	public TaskDto getTaskByTaskId(String taskid) {
		TaskEntity entity = taskRepository.findByTaskId(taskid);	
		if (entity == null)throw new AppServiceException(ErrorMessages.TASKID_NOT_FOUND.getErrorMessage());
		return new ModelMapper().map(entity, TaskDto.class);
	}

	@Override
	public List<TaskDto> getAllTask() {
		List<TaskEntity> allTasks = taskRepository.findAll();
		List<TaskDto> returnList = new ArrayList<TaskDto>();
		ModelMapper mapper = new ModelMapper();
		for(TaskEntity entity: allTasks) {
			TaskDto dto = mapper.map(entity, TaskDto.class);
			returnList.add(dto);
		}		
		return returnList;
	}
	
	@Override
	public List<TaskDto> getAllTaskBySupportId(String userid) {
		List<TaskEntity> allTasks = taskRepository.findTasksUsingSupportId(userid);
		List<TaskDto> returnList = new ArrayList<TaskDto>();
		ModelMapper mapper = new ModelMapper();
		for(TaskEntity entity: allTasks) {
			TaskDto dto = mapper.map(entity, TaskDto.class);
			returnList.add(dto);
		}		
		return returnList;
	}

	@Override
	public List<TaskDto> getAllTaskByTicketId(String ticketid) {
		TicketEntity ticketEntity = ticketRepository.findByTicketId(ticketid);
		List<TaskEntity> allTasks = taskRepository.findTasksByTicketDetails(ticketEntity);
		List<TaskDto> returnList = new ArrayList<TaskDto>();
		ModelMapper mapper = new ModelMapper();
		for(TaskEntity entity: allTasks) {
			TaskDto dto = mapper.map(entity, TaskDto.class);
			returnList.add(dto);
		}		
		return returnList;
	}

}
