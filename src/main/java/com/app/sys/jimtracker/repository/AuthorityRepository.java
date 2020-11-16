package com.app.sys.jimtracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.app.sys.jimtracker.entity.AuthorityEntity;

@Repository
public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long> {
	AuthorityEntity findByName(String name);
	
	@Query(value = "CALL show_authorizations_by_user_id(:userid);", nativeQuery = true)
	List<AuthorityEntity> findAuthorizationsByUserId(@Param("userid") String userid);
}
