package com.app.sys.jimtracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.sys.jimtracker.entity.AddressEntity;
import com.app.sys.jimtracker.entity.UserEntity;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
	List<AddressEntity> findAllByUserDetails(UserEntity entity);
	AddressEntity findByAddressId(String addressId);
}
