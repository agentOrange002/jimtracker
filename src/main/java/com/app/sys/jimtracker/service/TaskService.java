package com.app.sys.jimtracker.service;

import java.util.List;

import com.app.sys.jimtracker.dto.TaskDto;

public interface TaskService {
	TaskDto createTask(TaskDto dto,String ticketid, String userid);
	TaskDto getTaskByTaskId(String taskid);
	List<TaskDto> getAllTask();
	List<TaskDto> getAllTaskBySupportId(String userid);
	List<TaskDto> getAllTaskByTicketId(String ticketid);
}
