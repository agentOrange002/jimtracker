package com.app.sys.jimtracker.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import com.app.sys.jimtracker.dto.CategoryDto;
import com.app.sys.jimtracker.entity.CategoryEntity;
import com.app.sys.jimtracker.repository.CategoryRepository;

import lombok.extern.log4j.Log4j2;

@Component
@EnableAspectJAutoProxy(proxyTargetClass=true)
@Aspect
@Log4j2
public class CategoryAspect {
	
	@Autowired
	CategoryRepository repository;
	
	@Before("execution(public * com.app.sys.jimtracker.service.impl.CategoryServiceImpl.saveCategory(..)) && args(dto,..)")
    public void beforeSavingCategory(JoinPoint pjp,CategoryDto dto) throws Throwable {		
       // log.info(" Check Save Category :" + dto +" ---- "+pjp);
		//String name = dto.getName();
        log.info(" Check Save Category ...");
    }
	
	@AfterReturning(pointcut="execution(public * com.app.sys.jimtracker.service.impl.CategoryServiceImpl.saveCategory(..))",returning="retVal")
    public void afterSavingCategory(JoinPoint pjp,Object retVal) throws Throwable {
       // log.info(" Check Get Issue By IssueId :" + issueId +" ---- "+pjp);
		CategoryDto dto = (CategoryDto) retVal;
		CategoryEntity entity = repository.findByName(dto.getName());
		/* log.info(" Log Save Category....... "+((CategoryDto)retVal).toString()); */
		log.info(" Log Save Category....... "+entity.getName().toString());
    }
	
}
