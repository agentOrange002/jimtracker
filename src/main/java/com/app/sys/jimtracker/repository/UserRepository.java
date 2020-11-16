package com.app.sys.jimtracker.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.sys.jimtracker.entity.RoleEntity;
import com.app.sys.jimtracker.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);
	UserEntity deleteByUserId(String userId);
	UserEntity findById(String id);
	UserEntity findUserByEmailVerificationToken(String token);
	List<UserEntity> findByRoles(RoleEntity roleEntity);
	
}
