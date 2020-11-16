package com.app.sys.jimtracker.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.sys.jimtracker.entity.RoleEntity;
import com.app.sys.jimtracker.entity.UserEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
	RoleEntity findByName(String name);		
	List<RoleEntity> findAllByUsers(UserEntity userEntity);
}
