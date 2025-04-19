package com.subhadeep.e_food_inventory_service.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.subhadeep.e_food_inventory_service.dto.CategoryDto;

public interface CategoryService {
    CategoryDto addCategory(String data, MultipartFile file);
    List<CategoryDto> getAllCategories();
    CategoryDto updateCategory(Integer catId,CategoryDto updateDto);
    Map<?,?> deleteCategory(Integer categoryId);
    CategoryDto fetchCategoryById(Integer cartegoryId);
}
