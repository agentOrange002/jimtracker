package com.app.sys.jimtracker.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.sys.jimtracker.dto.TaskDto;
import com.app.sys.jimtracker.service.TaskService;
import com.app.sys.jimtracker.ui.model.request.TaskRequestModel;
import com.app.sys.jimtracker.ui.model.response.TaskResponseModel;

@RestController
@RequestMapping({ "/api/tasks" })
public class TaskController {
	
	@Autowired
	TaskService taskService;
	
	@PostMapping(path="/", consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public TaskResponseModel createTask(@RequestBody TaskRequestModel requestModel) {		
		TaskDto transferDto = new ModelMapper().map(requestModel, TaskDto.class);
		TaskDto dto = taskService.createTask(transferDto, requestModel.getTicketid(), requestModel.getUserid());		
		return new ModelMapper().map(dto, TaskResponseModel.class);
	}
	
	@GetMapping(path="/{taskid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public TaskResponseModel getTaskByTaskId(@PathVariable String taskid) {
		TaskDto dto = taskService.getTaskByTaskId(taskid);		
		return new ModelMapper().map(dto, TaskResponseModel.class);
	}
	
	@GetMapping(path="/all",produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<TaskResponseModel> getAllTask() {
		List<TaskResponseModel> result  = new ArrayList<TaskResponseModel>();
		List<TaskDto> listDto = taskService.getAllTask();
		ModelMapper mapper = new ModelMapper();
		for(TaskDto dto: listDto) {
			TaskResponseModel responseModel = mapper.map(dto, TaskResponseModel.class);
			result.add(responseModel);
		}
		return result;
	}
	
	@GetMapping(path="/all/support/{userid}",produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<TaskResponseModel> getAllTask(@PathVariable String userid) {
		List<TaskResponseModel> result  = new ArrayList<TaskResponseModel>();
		List<TaskDto> listDto = taskService.getAllTaskBySupportId(userid);
		ModelMapper mapper = new ModelMapper();
		for(TaskDto dto: listDto) {
			TaskResponseModel responseModel = mapper.map(dto, TaskResponseModel.class);
			result.add(responseModel);
		}
		return result;
	}
	
	@GetMapping(path="/all/ticket/{ticketid}",produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<TaskResponseModel> getAllTaskByTicketId(@PathVariable String ticketid) {
		List<TaskResponseModel> result  = new ArrayList<TaskResponseModel>();
		List<TaskDto> listDto = taskService.getAllTaskByTicketId(ticketid);
		ModelMapper mapper = new ModelMapper();
		for(TaskDto dto: listDto) {
			TaskResponseModel responseModel = mapper.map(dto, TaskResponseModel.class);
			result.add(responseModel);
		}
		return result;
	}
	
}
