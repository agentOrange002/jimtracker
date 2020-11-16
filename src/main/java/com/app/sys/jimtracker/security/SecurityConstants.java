package com.app.sys.jimtracker.security;

import com.app.sys.jimtracker.SpringApplicationContext;

public class SecurityConstants {
	public static final long EXPIRATION_TIME = 86400000; // 1 day
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";	
	
	/* PUBLIC */
	public static final String PUBLIC_REPORT_ISSUE = "/api/public/post-issue";
	public static final String USER_RESET_PASSWORD = "/api/users/*/reset-password";
	public static final String POST_USER_IMAGE = "/api/test";
	public static final String GET_USER_IMAGE = "/api/test/*";
	public static final String SIGN_UP_URL = "/api/users";
	
	/* DASHBOARD */
	public static final String DASHBOARD = "/api/dashboard";
	
	/* LOGIN PROFILE */
	public static final String LOGIN_PROFILE = "/api/users/*";
	
	/* USERS */
	public static final String DELETE_USER = "/api/users/*";
	public static final String GET_USER_BY_ID = "/api/users/**";
	
	/* USERIMAGES */
	
	/* ADDRESS */	
	public static final String POST_USER_ADDRESS = "/api/addresses";
	
	/* ISSUES */
	public static final String GET_ALL_ISSUES = "/api/issues/all";
	public static final String ISSUE = "/api/issues/**";
	public static final String ASSIGNED_SUPPORT = "/api/issues/assignedsupport";
	
	/* CATEGORY */
	public static final String CATEGORY = "/api/category/**";
	
	/* ISSUELOGS */
	public static final String ISSUELOG = "/api/issuelog/*/*";
	public static final String SISSUELOG = "/api/issuelog/**";

	/* TICKETS */
	public static final String GET_ALL_TICKETS = "/api/tickets/all";	
	
	/* REPORTS */
	public static final String REPORTS_DOWNLOAD = "/api/reports/download";
	public static final String REPORTS_PDF = "/api/reports/pdf/*";
	public static final String TEST_UPLOAD = "/api/reports/upload";
	
	
	/* public static final String TOKEN_SECRET = "dimple"; */

	public static String getTokenSecret() {
		AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
		return appProperties.getTokenSecret();
	}
}
