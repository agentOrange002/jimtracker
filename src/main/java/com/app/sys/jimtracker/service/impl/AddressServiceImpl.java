package com.app.sys.jimtracker.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.sys.jimtracker.dto.AddressDto;
import com.app.sys.jimtracker.entity.AddressEntity;
import com.app.sys.jimtracker.entity.UserEntity;
import com.app.sys.jimtracker.repository.AddressRepository;
import com.app.sys.jimtracker.repository.UserRepository;
import com.app.sys.jimtracker.service.AddressService;
import com.app.sys.jimtracker.tool.Utils;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class AddressServiceImpl implements AddressService {
	@Autowired
	AddressRepository addressRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	Utils utils;

	@Override
	public AddressDto saveAddress(AddressDto addressDto, String userId) {	
		UserEntity userIdEntity = userRepository.findByUserId(userId);
		AddressEntity addressEntity = new ModelMapper().map(addressDto, AddressEntity.class);
		addressEntity.setAddressId(utils.generateAddressId(10));
		addressEntity.setUserDetails(userIdEntity);
		AddressEntity saveaddressEntity = addressRepository.save(addressEntity);
		return new ModelMapper().map(saveaddressEntity, AddressDto.class);
	}

	@Override
	public AddressDto updateAddress(AddressDto addressDto, String addressid) {
		String city = addressDto.getCity();
		String country = addressDto.getCountry();
		String streetName = addressDto.getStreetName();
		String postalCode = addressDto.getPostalCode();
		String type = addressDto.getType().toUpperCase();
		AddressEntity entity = addressRepository.findByAddressId(addressid);
		log.info(" Update Address:" + entity);
		entity.setCity(city);		
		entity.setCountry(country);
		entity.setStreetName(streetName);
		entity.setPostalCode(postalCode);
		entity.setType(type);
		AddressEntity updatedentity = addressRepository.save(entity);		
		return new ModelMapper().map(updatedentity, AddressDto.class);
	}

}
