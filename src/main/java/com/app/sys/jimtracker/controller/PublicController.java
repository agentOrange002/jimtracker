package com.app.sys.jimtracker.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.sys.jimtracker.dto.IssueDto;
import com.app.sys.jimtracker.service.IssueService;
import com.app.sys.jimtracker.service.UserService;
import com.app.sys.jimtracker.ui.model.request.PasswordResetModel;
import com.app.sys.jimtracker.ui.model.request.PasswordResetRequestModel;
import com.app.sys.jimtracker.ui.model.request.PublicIssueDetailsRequestModel;
import com.app.sys.jimtracker.ui.model.response.OperationStatusModel;
import com.app.sys.jimtracker.ui.model.response.RequestOperationStatus;

@RestController
@RequestMapping({ "/api/public" })
public class PublicController {
	@Autowired
	UserService userService;

	@Autowired
	IssueService issueService;

	@GetMapping(path = "/email-verification", produces = { MediaType.APPLICATION_JSON_VALUE })
	public OperationStatusModel publicverifyEmailToken(@RequestParam(value = "token") String token) {
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.VERIFY_EMAIL.name());
		boolean isVerified = userService.verifyEmailToken(token);
		if (isVerified) {
			returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		} else {
			returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
		}
		return returnValue;
	}

	@PostMapping(path = "/password-request-reset", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public OperationStatusModel publicrequestReset(@RequestBody PasswordResetRequestModel passwordResetRequestModel) {
		OperationStatusModel returnValue = new OperationStatusModel();
		boolean operationResult = userService.requestPasswordReset(passwordResetRequestModel.getEmail());

		returnValue.setOperationName(RequestOperationName.REQUEST_PASSWORD_RESET.name());
		returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
		if (operationResult) {
			returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		}

		return returnValue;
	}

	@PostMapping(path = "/password-reset", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public OperationStatusModel publicresetPassword(@RequestBody PasswordResetModel passwordResetModel) {
		OperationStatusModel returnValue = new OperationStatusModel();
		boolean operationResult = userService.resetPassword(passwordResetModel.getToken(),
				passwordResetModel.getPassword());

		returnValue.setOperationName(RequestOperationName.PASSWORD_RESET.name());
		returnValue.setOperationResult(RequestOperationStatus.ERROR.name());

		if (operationResult) {
			returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		}

		return returnValue;
	}

	@PostMapping(path = "/post-issue", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public OperationStatusModel publicpostIssue(@RequestBody PublicIssueDetailsRequestModel issueDetails) {
		OperationStatusModel returnValue = new OperationStatusModel();
		IssueDto issuedto = new ModelMapper().map(issueDetails, IssueDto.class);
		boolean operationResult = issueService.publicSaveIssue(issuedto);
		returnValue.setOperationName(RequestOperationName.REPORT_ISSUE.name());
		returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
		if (operationResult) {
			returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		}
		return returnValue;
	}
}
