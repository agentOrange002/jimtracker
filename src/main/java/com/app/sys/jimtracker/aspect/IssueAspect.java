package com.app.sys.jimtracker.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import com.app.sys.jimtracker.dto.IssueDto;

import lombok.extern.log4j.Log4j2;

@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass=true)
@Log4j2
public class IssueAspect {
	
	@Before("execution(public * com.app.sys.jimtracker.service.impl.IssueServiceImpl.getIssueByIssueId(..)) && args(issueId,..)")
    public void checkGetIssueByIssueId(JoinPoint pjp,String issueId) throws Throwable {
       // log.info(" Check Get Issue By IssueId :" + issueId +" ---- "+pjp);
		log.info(" Check Get Issue By IssueId....... ");
    }
	
	@AfterReturning(pointcut="execution(public * com.app.sys.jimtracker.service.impl.IssueServiceImpl.saveIssue(..))",returning="retVal")
    public void saveIssueLogAfterSaving(JoinPoint pjp,Object retVal) throws Throwable {
       // log.info(" Check Get Issue By IssueId :" + issueId +" ---- "+pjp);
		log.info(" Log Save Issue....... "+((IssueDto)retVal).toString());
    }
	
	
}
