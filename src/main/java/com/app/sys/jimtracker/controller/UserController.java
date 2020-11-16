package com.app.sys.jimtracker.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.sys.jimtracker.dto.UserDto;
//import com.app.sys.jimtracker.dto.UserImageDto;
import com.app.sys.jimtracker.entity.UserEntity;
import com.app.sys.jimtracker.service.UserImageService;
import com.app.sys.jimtracker.service.UserService;
import com.app.sys.jimtracker.ui.model.request.ResetPassModel;
import com.app.sys.jimtracker.ui.model.request.UserDetailsRequestModel;
import com.app.sys.jimtracker.ui.model.response.OperationStatusModel;
import com.app.sys.jimtracker.ui.model.response.RequestOperationStatus;
import com.app.sys.jimtracker.ui.model.response.ShortUserResponseModel;
//import com.app.sys.jimtracker.ui.model.response.UserImageResponseModel;
import com.app.sys.jimtracker.ui.model.response.UserResponseModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "Users", description = "Users REST API Services.")
@RestController
@RequestMapping({ "/api/users" })
@AllArgsConstructor
public class UserController {
	@Autowired
	UserService userService;

	@Autowired
	UserImageService userImageService;

	@Operation(summary = "Get User By UserID", description = "Get Specific User By UserID", tags = "Users")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfull Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserEntity.class)))) })
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public UserResponseModel getUser(@PathVariable String id) {
		UserDto userDto = userService.getUserByUserId(id);		
		return new ModelMapper().map(userDto, UserResponseModel.class);
	}

	@Operation(summary = "Post User", description = "Save New User", tags = "Users")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfull Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserEntity.class)))) })
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public UserResponseModel saveUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception {
		UserDto userDto = new ModelMapper().map(userDetails, UserDto.class);
		UserDto createdUser = userService.createUser(userDto);	
		return new ModelMapper().map(createdUser, UserResponseModel.class);
	}

	@Operation(summary = "Put User", description = "Update User Information (Firstname and Lastname Only)", tags = "Users")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfull Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserEntity.class)))) })
	@PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE })
	public UserResponseModel updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) {
		UserDto userDto = new ModelMapper().map(userDetails, UserDto.class);
		UserDto updatedUser = userService.updateUser(id, userDto);		
		return new ModelMapper().map(updatedUser, UserResponseModel.class);
	}

	@Operation(summary = "Delete User", description = "Delete User and its Information", tags = "Users")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfull Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserEntity.class)))) })
	@DeleteMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public OperationStatusModel deleteUser(@PathVariable String id) {
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.DELETED.name());
		userService.deleteUser(id);
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		return returnValue;
	}

	@Operation(summary = "Post Users Password", description = "Change User Password", tags = "Users")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfull Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserEntity.class)))) })
	@PostMapping(path = "/{id}/reset-password", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public OperationStatusModel resetPassword(@PathVariable String id, @RequestBody ResetPassModel resetPassModel) {
		String password = resetPassModel.getConfirmedPassword();
		OperationStatusModel returnValue = new OperationStatusModel();
		boolean result = userService.authResetPassword(id, password);
		returnValue.setOperationName(RequestOperationName.PASSWORD_RESET.name());
		returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
		if (result) {
			returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		}
		return returnValue;
	}

	@Operation(summary = "Get All Users", description = "List of All Users", tags = "Users")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfull Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserEntity.class)))) })
	@GetMapping(path = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<ShortUserResponseModel> getUsers() {
		List<ShortUserResponseModel> returnList = new ArrayList<ShortUserResponseModel>();
		List<UserDto> dtoList = userService.getAllUsers();
		ModelMapper mapper = new ModelMapper();
		for (UserDto dto : dtoList) {
			ShortUserResponseModel responseModel = mapper.map(dto, ShortUserResponseModel.class);
			returnList.add(responseModel);
		}
		return returnList;
	}	
	
	@GetMapping(path = "/role/{roleName}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<ShortUserResponseModel> getAllUsersByRole(@PathVariable String roleName) {
		List<ShortUserResponseModel> returnList = new ArrayList<ShortUserResponseModel>();
		List<UserDto> dtoList = userService.getAllUsersByRole(roleName);
		ModelMapper mapper = new ModelMapper();
		for (UserDto dto : dtoList) {
			ShortUserResponseModel responseModel = mapper.map(dto, ShortUserResponseModel.class);
			returnList.add(responseModel);
		}
		return returnList;
	}

}
