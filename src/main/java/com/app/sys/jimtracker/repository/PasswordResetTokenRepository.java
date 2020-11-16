package com.app.sys.jimtracker.repository;

import org.springframework.data.repository.CrudRepository;

import com.app.sys.jimtracker.entity.PasswordResetTokenEntity;

public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetTokenEntity, Long> {
	PasswordResetTokenEntity findByToken(String token);
}
