package com.app.sys.jimtracker.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import com.app.sys.jimtracker.dto.IssueLogDto;

import lombok.extern.log4j.Log4j2;

@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass=true)
@Log4j2
public class IssueLogAspect {	
	
	@Before("execution(public * com.app.sys.jimtracker.service.impl.IssueLogServiceImpl.saveIssueLog(..)) && args(issueLog,issueId,userId,..)")
    public void checkGetSaveIssueLog(JoinPoint pjp,IssueLogDto issueLog, String issueId, String userId) throws Throwable {
		//log.info(" Check Saved Issue Log:" + issueLog +" ---- "+ issueId +" ---- "+ userId +" ---- "+pjp);
        log.info(" Check Saved Issue Log...... " );
    }
}
