package com.app.sys.jimtracker.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.sys.jimtracker.dto.CategoryDto;
import com.app.sys.jimtracker.service.CategoryService;
import com.app.sys.jimtracker.tool.Utils;
import com.app.sys.jimtracker.ui.model.request.CategoryRequestModel;
import com.app.sys.jimtracker.ui.model.response.CategoryResponseModel;

@RestController
@RequestMapping({ "/api/category" })
public class CategoryController {
	@Autowired
	CategoryService categoryService;

	@Autowired
	Utils utils;

	@GetMapping(path = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<CategoryResponseModel> getAllCategory() {
		List<CategoryResponseModel> returnList = new ArrayList<CategoryResponseModel>();
		List<CategoryDto> listdto = categoryService.allCategory();
		ModelMapper mapper = new ModelMapper();
		for (CategoryDto dto : listdto) {
			returnList.add(mapper.map(dto, CategoryResponseModel.class));
		}
		return returnList;
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CategoryResponseModel createCategory(@RequestBody CategoryRequestModel catDetails) {
		String name = catDetails.getName().toUpperCase();
		catDetails.setName(name);
		CategoryDto dto = new ModelMapper().map(catDetails, CategoryDto.class);
		CategoryDto saveDto = categoryService.saveCategory(dto);		
		return new ModelMapper().map(saveDto, CategoryResponseModel.class);
	}

	@PutMapping(path = "/{categoryId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CategoryResponseModel updateCategory(@PathVariable String categoryId,@RequestBody CategoryRequestModel catDetails) {
		CategoryDto dto = new ModelMapper().map(catDetails, CategoryDto.class);
		CategoryDto updateDto = categoryService.updateCategory(categoryId,dto);	
		return new ModelMapper().map(updateDto, CategoryResponseModel.class);
	}
}
