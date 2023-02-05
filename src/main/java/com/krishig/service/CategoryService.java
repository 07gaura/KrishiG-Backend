package com.krishig.service;

import com.krishig.dto.res.CategoryResDto;
import com.krishig.entity.Category;

import java.util.List;

public interface CategoryService {
    public List<CategoryResDto> getAllCategory();
    public Category getCategoryById(Long catId);
}
