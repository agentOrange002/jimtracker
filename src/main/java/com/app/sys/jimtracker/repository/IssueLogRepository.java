package com.app.sys.jimtracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.sys.jimtracker.entity.IssueEntity;
import com.app.sys.jimtracker.entity.IssueLogEntity;

@Repository
public interface IssueLogRepository extends JpaRepository<IssueLogEntity, Long> {
	List<IssueLogEntity> findAllByIssueDetails(IssueEntity entity);
}
