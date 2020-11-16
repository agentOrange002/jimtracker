package com.app.sys.jimtracker.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.sys.jimtracker.dto.AddressDto;
import com.app.sys.jimtracker.entity.AddressEntity;
import com.app.sys.jimtracker.service.AddressService;
import com.app.sys.jimtracker.tool.Utils;
import com.app.sys.jimtracker.ui.model.request.AddressRequestModel;
import com.app.sys.jimtracker.ui.model.response.AddressResponseModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Addresses", description = "Addresses REST API Services.")
@RestController
@RequestMapping({ "/api/addresses" })
public class AddressController {
	@Autowired
	AddressService addressService;

	@Autowired
	Utils utils;

	@Operation(summary = "Post Address", description = "Save Address on a Specific User", tags = "Addresses")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfull Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = AddressEntity.class)))) })
	@PostMapping(path="/{userId}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public AddressResponseModel postAddress(@PathVariable String userId,@RequestBody AddressRequestModel addressDetails) {
		
		/*
		 * String city = addressDetails.getCity(); String country =
		 * addressDetails.getCountry(); String postal = addressDetails.getPostalCode();
		 * String streetname = addressDetails.getStreetName(); String type =
		 * addressDetails.getType(); String userId = addressDetails.getUserId();
		 * 
		 * UserEntity userEntity = userRepository.findByUserId(userId); AddressDto
		 * addressDto = new AddressDto();
		 * addressDto.setAddressId(utils.generateAddressId(10));
		 * addressDto.setCity(city); addressDto.setCountry(country);
		 * addressDto.setPostalCode(postal); addressDto.setStreetName(streetname);
		 * addressDto.setType(type); addressDto.setUserDetails(userEntity);
		 */		
		AddressDto addressDto = new ModelMapper().map(addressDetails,AddressDto.class);	
		AddressDto saveDto = addressService.saveAddress(addressDto, userId);		
		AddressResponseModel returnValue = new ModelMapper().map(saveDto, AddressResponseModel.class);
		return returnValue;
	}
	
	@Operation(summary = "Put Address", description = "Update Address on a Specific User", tags = "Addresses")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfull Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = AddressEntity.class)))) })
	@PutMapping(path="/{addressId}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public AddressResponseModel updateAddress(@PathVariable String addressId,@RequestBody AddressRequestModel addressDetails) {
		AddressDto dto = new ModelMapper().map(addressDetails, AddressDto.class);
		AddressDto updateddto = addressService.updateAddress(dto,addressId);
		AddressResponseModel response = new ModelMapper().map(updateddto, AddressResponseModel.class);		
		return response;
	}

}
