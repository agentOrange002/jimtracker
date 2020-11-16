package com.app.sys.jimtracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.sys.jimtracker.entity.TaskEntity;
import com.app.sys.jimtracker.entity.TicketEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
	
	/* Using Stored Procedure
	 * @Query(value = "CALL FIND_CARS_AFTER_YEAR(:year_in);", nativeQuery = true)
	 * List<Car> findCarsAfterYear(@Param("year_in") Integer year_in);
	 */
	
	@Query(value = "CALL show_tasks_by_support_user(:userid);", nativeQuery = true)
	List<TaskEntity> findTasksUsingSupportId(@Param("userid") String userid);
	
	List<TaskEntity> findTasksByTicketDetails(TicketEntity entity);
	
	TaskEntity findByTaskId(String taskid);
	
	long countByDateOpenedNotNull();
	long countByDateClosedNotNull();
}
