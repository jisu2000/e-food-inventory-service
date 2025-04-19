package com.subhadeep.e_food_inventory_service.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.subhadeep.e_food_inventory_service.dto.SubcategoryDto;

public interface SubCategoryService {

    List<SubcategoryDto> getSubCategoriesFromCategories(Integer categoryId);

    List<SubcategoryDto> getAllSubCategories();

    SubcategoryDto addSubCategory(String data,MultipartFile file);

    SubcategoryDto getSubCategoryById(Integer subcatgoryId);

    Map<?, ?> deleteSubcategory(Integer subcategoryId);

    SubcategoryDto updateSubcategory(Integer subcategoryId, SubcategoryDto updateSubcategoryDto);
}
