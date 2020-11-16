package com.app.sys.jimtracker.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.sys.jimtracker.dto.IssueDto;
import com.app.sys.jimtracker.dto.IssueLogDto;
import com.app.sys.jimtracker.dto.UserDto;
import com.app.sys.jimtracker.service.IssueLogService;
import com.app.sys.jimtracker.ui.model.request.IssueLogDetailsRequestModel;
import com.app.sys.jimtracker.ui.model.response.IssueLogResponseModel;
import com.app.sys.jimtracker.ui.model.response.IssueResponseModel;
import com.app.sys.jimtracker.ui.model.response.ShortUserResponseModel;

@RestController
@RequestMapping({ "/api/issuelog" })
public class IssueLogController {
	@Autowired
	IssueLogService issueLogService;

	@PostMapping(path = "/{issueid}/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public IssueLogResponseModel postIssueLog(@PathVariable String issueid, @PathVariable String userId, @RequestBody IssueLogDetailsRequestModel requestdetails) {
		IssueLogResponseModel returnValue = new IssueLogResponseModel();
		IssueLogDto dto = new ModelMapper().map(requestdetails, IssueLogDto.class);
		IssueLogDto saveDto = issueLogService.saveIssueLog(dto, issueid, userId);
		UserDto userDto = saveDto.getUserDto();
		IssueDto issueDto = saveDto.getIssueDto();
		IssueResponseModel irm = new ModelMapper().map(issueDto, IssueResponseModel.class);
		ShortUserResponseModel urm = new ModelMapper().map(userDto, ShortUserResponseModel.class);
		returnValue = new ModelMapper().map(saveDto, IssueLogResponseModel.class);
		returnValue.setIssueResponseModel(irm);
		returnValue.setUserResponseModel(urm);
		return returnValue;
	}

	@GetMapping(path = "/{issueId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<IssueLogResponseModel> getIssueLogByIssueID(@PathVariable String issueId) {
		List<IssueLogResponseModel> returnValue = new ArrayList<IssueLogResponseModel>();
		List<IssueLogDto> listdto = issueLogService.getIssueLogById(issueId);
		ModelMapper modelMapper = new ModelMapper();
		for (IssueLogDto ild : listdto) {
			IssueLogResponseModel ilrm = modelMapper.map(ild, IssueLogResponseModel.class);

			ModelMapper mm = new ModelMapper();
			UserDto udto = ild.getUserDto();
			IssueDto idto = ild.getIssueDto();

			ShortUserResponseModel surm = mm.map(udto, ShortUserResponseModel.class);
			IssueResponseModel irm = mm.map(idto, IssueResponseModel.class);

			ilrm.setUserResponseModel(surm);
			ilrm.setIssueResponseModel(irm);
			returnValue.add(ilrm);
		}
		return returnValue;
	}
}
