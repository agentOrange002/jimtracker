package com.app.sys.jimtracker.controller;

import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.sys.jimtracker.dto.UserImageDto;
import com.app.sys.jimtracker.entity.UserEntity;
import com.app.sys.jimtracker.entity.UserImageEntity;
import com.app.sys.jimtracker.repository.UserRepository;
import com.app.sys.jimtracker.service.UserImageService;
import com.app.sys.jimtracker.tool.Utils;
import com.app.sys.jimtracker.ui.model.request.UserImageRequestModel;
import com.app.sys.jimtracker.ui.model.response.UserImageResponseModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "UserImages", description = "UserImages REST API Services.")
@RestController
@RequestMapping({ "/api/userimages" })
public class UserImageController {

	@Autowired
	UserImageService userImageService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	Utils utils;

//	@PostMapping(path = "/image/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//	public UserImageResponseModel singleImageUpload(@PathVariable String id,
//			@RequestParam(value = "file", required = false) MultipartFile file) {
//		UserImageResponseModel returnValue = new UserImageResponseModel();
//		byte[] bytes = null;
//		try {
//			bytes = file.getBytes();
//			UserImageDto userImageDto = new UserImageDto();
//			userImageDto.setUserId(id);
//			userImageDto.setImage(bytes);
//			UserImageDto uploadedDto = userImageService.uploadUserImage(userImageDto);
//			returnValue = new ModelMapper().map(uploadedDto, UserImageResponseModel.class);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return returnValue;
//	}
	@Operation(summary = "Post UserImage by UserId", description = "Save UserImage on a Specific User", tags = "UserImages")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfull Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserImageEntity.class)))) })
	@PostMapping(path = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserImageResponseModel postUserImage(@PathVariable String userId,
			@RequestBody UserImageRequestModel userImageDetails) throws SerialException, SQLException {

		String[] file = userImageDetails.getImage();
		String newFile = file[0];
		String base64Image = newFile.split(",")[1];
		byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);

		// System.out.println("New File :"+base64Image);
		/* Blob b = new SerialBlob(imageBytes); */

		// String userId = userImageDetails.getUserId();

		// System.out.println("FILE id :" +userId);
		// System.out.println("FILE BASE64 String :" + userImageDetails);

		UserEntity userEntity = new UserEntity();
		userEntity = userRepository.findByUserId(userId);
		UserImageDto details = new UserImageDto();
		details.setImageId(utils.generateImageId(10));
		details.setUserImageDetails(userEntity);
		details.setImage(imageBytes);
		/*
		 * // byte[] imageByte=Base64.decodeBase64(file); UserImageDto userImageDto =
		 * new UserImageDto(); userImageDto.setUserId(userDetails.getUserId());
		 * userImageDto.setImageName(userDetails.getImageName());
		 * userImageDto.setImage(imageByte);
		 */

		UserImageDto userImageDto = new ModelMapper().map(details, UserImageDto.class);
		UserImageDto uploadedDto = userImageService.uploadUserImage(userImageDto);
		UserImageResponseModel response = new ModelMapper().map(uploadedDto, UserImageResponseModel.class);
		return response;
	}

	@Operation(summary = "Get UserImage by UserId", description = "Get UserImage of Specific User", tags = "UserImages")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfull Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserImageEntity.class)))) })
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserImageResponseModel getUserImage(@PathVariable String id) {
		UserImageDto userImageDto = userImageService.getUserImageByUserId(id);
		UserImageResponseModel response = new ModelMapper().map(userImageDto, UserImageResponseModel.class);
		return response;
	}

	@Operation(summary = "Put UserImage by UserId", description = "Update UserImage of Specific User", tags = "UserImages")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfull Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserImageEntity.class)))) })
	@PutMapping(path = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserImageResponseModel updateUserImage(@PathVariable String userId,
			@RequestBody UserImageRequestModel requestbody) {
		String[] file = requestbody.getImage();
		String newFile = file[0];
		String base64Image = newFile.split(",")[1];
		byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
		UserImageDto transferDto = new UserImageDto();
		transferDto.setImage(imageBytes);
		UserImageDto dto = userImageService.updateUserImage(transferDto, userId);
		UserImageResponseModel response = new ModelMapper().map(dto, UserImageResponseModel.class);
		return response;
	}
	
}
