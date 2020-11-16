package com.app.sys.jimtracker.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.app.sys.jimtracker.dto.UserDto;


public interface UserService extends UserDetailsService
{
	UserDto createUser(UserDto user);
	UserDto getUser(String email);
	UserDto getUserByUserId(String userId);
	UserDto updateUser(String userId, UserDto user);
	void deleteUser(String userId);
	boolean verifyEmailToken(String token);
	boolean requestPasswordReset(String email);
	boolean resetPassword(String token, String password);
	
	boolean authResetPassword(String id, String password);
	
	List<UserDto> getAllUsers();
	List<UserDto> getAllUsersByRole(String roleName);
}
