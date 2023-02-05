package com.krishig.service.impl;

import com.krishig.dto.res.CategoryResDto;
import com.krishig.entity.Category;
import com.krishig.repository.CategoryRepository;
import com.krishig.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryResDto> getAllCategory() {
        List<Category> lstCategory = categoryRepository.findAll();
        List<CategoryResDto> lstCategoryRes = convertEntityToDto(lstCategory);
        return lstCategoryRes;
    }

    @Override
    public Category getCategoryById(Long catId) {
        return categoryRepository.findById(catId).get();
    }

    private List<CategoryResDto> convertEntityToDto(List<Category> lstCategories) {
        List<CategoryResDto> lstCategoryResDto = new ArrayList<>();
        for(Category category : lstCategories) {
            CategoryResDto categoryResDto = new CategoryResDto();
            categoryResDto.setId(category.getId());
            categoryResDto.setName(category.getCategoryName());
            lstCategoryResDto.add(categoryResDto);
        }
        return lstCategoryResDto;
    }
}
