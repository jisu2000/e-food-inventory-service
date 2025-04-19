package com.subhadeep.e_food_inventory_service.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.subhadeep.e_food_inventory_service.dto.CategoryDto;
import com.subhadeep.e_food_inventory_service.exception.ResourceNotFoundException;
import com.subhadeep.e_food_inventory_service.model.CategoryEO;
import com.subhadeep.e_food_inventory_service.repo.CategoryRepo;
import com.subhadeep.e_food_inventory_service.service.CategoryService;
import com.subhadeep.e_food_inventory_service.service.FileUploadService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;
    private final FileUploadService fileUploadService;

    @Value("${folder.name.CATEGORY_IMAGE_FOLDER}")
    private String categoryImageFolder;

    @Override
    public CategoryDto addCategory(String data, MultipartFile file) {

        Map<String, Object> dataMap = null;

        try {
            dataMap = objectMapper.readValue(data, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
        }

        String imageUrl = null;

        if (file != null) {

            imageUrl = fileUploadService.uploadFile(file, categoryImageFolder);

        }

        CategoryDto dto = new CategoryDto();
        dto.setCategoryName(dataMap.get("categoryName").toString());

        CategoryEO goingTosave = modelMapper.map(dto, CategoryEO.class);

        if (imageUrl != null) {
            goingTosave.setImageUrl(imageUrl);
        }

        goingTosave.setCategorySku("CAT" + UUID.randomUUID().toString());
        CategoryEO saved = categoryRepo.save(goingTosave);
        return modelMapper.map(saved, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<CategoryEO> fetched = categoryRepo.findAll();
        return fetched.stream()
                .map(e -> modelMapper.map(e, CategoryDto.class))
                .toList();
    }

    @Override
    public CategoryDto updateCategory(Integer catId, CategoryDto updateDto) {
        CategoryEO fetched = categoryRepo.findById(catId)
                .orElseThrow(getExceptionSupplierCategory("ID", catId.toString()));

        if (updateDto.getCategoryName() != null) {
            fetched.setCategoryName(updateDto.getCategoryName());
        }
        CategoryEO updated = categoryRepo.save(fetched);
        return modelMapper.map(updated, CategoryDto.class);

    }

    @Override
    public Map<?,?> deleteCategory(Integer cartegoryId) {
        CategoryEO fetched = categoryRepo.findById(cartegoryId)
                .orElseThrow(getExceptionSupplierCategory("ID", cartegoryId.toString()));

        categoryRepo.delete(fetched);
        return Map.of("msg", "Category Deleted");

    }

    @Override
    public CategoryDto fetchCategoryById(Integer cartegoryId) {
        CategoryEO fetched = categoryRepo.findById(cartegoryId)
                .orElseThrow(getExceptionSupplierCategory("ID", cartegoryId.toString()));

        return modelMapper.map(fetched, CategoryDto.class);
    }

    private Supplier<ResourceNotFoundException> getExceptionSupplierCategory(String field, String val) {
        return () -> new ResourceNotFoundException("Category not found with " + field + " : " + val);
    }
}
