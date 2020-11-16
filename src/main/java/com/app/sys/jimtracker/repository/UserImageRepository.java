package com.app.sys.jimtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.sys.jimtracker.entity.UserEntity;
import com.app.sys.jimtracker.entity.UserImageEntity;

@Repository
public interface UserImageRepository extends JpaRepository<UserImageEntity, Long> {
	UserImageEntity findByImageId(String imageId);
	UserImageEntity findByUserImageDetails(UserEntity userEntity);
}
