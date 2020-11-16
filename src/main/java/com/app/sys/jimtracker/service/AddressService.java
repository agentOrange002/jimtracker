package com.app.sys.jimtracker.service;

import com.app.sys.jimtracker.dto.AddressDto;

public interface AddressService {
	AddressDto saveAddress(AddressDto addressDto , String userid);
	AddressDto updateAddress(AddressDto addressDto , String addressid);
}
