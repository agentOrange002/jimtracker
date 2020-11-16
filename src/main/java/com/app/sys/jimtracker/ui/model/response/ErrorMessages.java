package com.app.sys.jimtracker.ui.model.response;

public enum ErrorMessages {
	MISSING_REQUIRED_FIELD("Missing required field. Please check documentation for required fields"),
	LOGIN_FAILED("Failed to Login"), RECORD_ALREADY_EXISTS("Record already exists"),
	CATEGORY_NAME_ALREADY_EXISTS("Record already exists! Please try different name."),
	INTERNAL_SERVER_ERROR("Internal server error"), NO_RECORD_FOUND("Record with provided id is not found"),
	AUTHENTICATION_FAILED("Authentication failed"), COULD_NOT_UPDATE_RECORD("Could not update record"),
	COULD_NOT_DELETE_RECORD("Could not delete record"),
	EMAIL_ADDRESS_NOT_VERIFIED("Email address could not be verified"),
	ISSUE_NOT_FOUND("Issue record with provide id is not found."),
	ISSUE_HAS_ALREADY_OWNDED("The issue has already owned by other user."),
	TICKETID_NOT_FOUND("Ticket record with provided id is not found."),
	TASKID_NOT_FOUND("Task record with provided id is not found."),
	ISSUE_HAS_NO_SUPPORT("Please support first before creating a ticket"),
	USER_NOT_FOUND("User ID not exist.");

	private String errorMessage;

	ErrorMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
