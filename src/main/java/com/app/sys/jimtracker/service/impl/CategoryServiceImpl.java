package com.app.sys.jimtracker.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.sys.jimtracker.dto.CategoryDto;
import com.app.sys.jimtracker.entity.CategoryEntity;
import com.app.sys.jimtracker.exceptions.AppServiceException;
import com.app.sys.jimtracker.repository.CategoryRepository;
import com.app.sys.jimtracker.service.CategoryService;
import com.app.sys.jimtracker.tool.Utils;
import com.app.sys.jimtracker.ui.model.response.ErrorMessages;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	Utils utils;

	@Override
	public List<CategoryDto> allCategory() {
		List<CategoryDto> returnList = new ArrayList<CategoryDto>();
		List<CategoryEntity> entityList = categoryRepository.findAll();
		ModelMapper mapper = new ModelMapper();
		for (CategoryEntity entity : entityList) {
			returnList.add(mapper.map(entity, CategoryDto.class));
		}
		return returnList;
	}

	@Override
	public CategoryDto saveCategory(CategoryDto dto) {		
		if(categoryRepository.findByName(dto.getName().toUpperCase()) != null) throw new  AppServiceException(ErrorMessages.CATEGORY_NAME_ALREADY_EXISTS.getErrorMessage());
		CategoryEntity entity = new ModelMapper().map(dto, CategoryEntity.class);
		entity.setCategoryId(utils.generateCategoryId(10));
		CategoryEntity saveEntity = categoryRepository.save(entity);	
		return new ModelMapper().map(saveEntity, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(String categoryId,CategoryDto dto) {
		if(categoryRepository.findByName(dto.getName().toUpperCase()) != null) throw new  AppServiceException(ErrorMessages.CATEGORY_NAME_ALREADY_EXISTS.getErrorMessage());
		CategoryEntity entity = categoryRepository.findByCategoryId(categoryId);
		entity.setName(dto.getName().toString().toUpperCase());
		CategoryEntity updateEntity = categoryRepository.save(entity);	
		return new ModelMapper().map(updateEntity, CategoryDto.class);
	}

}
