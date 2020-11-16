package com.app.sys.jimtracker.service;

import java.util.List;

import com.app.sys.jimtracker.dto.CategoryDto;

public interface CategoryService 
{
	List<CategoryDto> allCategory();
	CategoryDto saveCategory(CategoryDto dto);
	CategoryDto updateCategory(String categoryId,CategoryDto dto);
}
