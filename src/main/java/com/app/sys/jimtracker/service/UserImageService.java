package com.app.sys.jimtracker.service;

import com.app.sys.jimtracker.dto.UserImageDto;

public interface UserImageService {
	UserImageDto uploadUserImage(UserImageDto userImageDto);

	UserImageDto getUserImageByUserId(String userId);

	UserImageDto updateUserImage(UserImageDto transferDto, String userId);
}
