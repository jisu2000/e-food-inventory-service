package com.subhadeep.e_food_inventory_service.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.subhadeep.e_food_inventory_service.dto.SubcategoryDto;
import com.subhadeep.e_food_inventory_service.exception.ResourceNotFoundException;
import com.subhadeep.e_food_inventory_service.model.CategoryEO;
import com.subhadeep.e_food_inventory_service.model.SubcategoryEO;
import com.subhadeep.e_food_inventory_service.repo.CategoryRepo;
import com.subhadeep.e_food_inventory_service.repo.SubcategoryRepo;
import com.subhadeep.e_food_inventory_service.service.FileUploadService;
import com.subhadeep.e_food_inventory_service.service.SubCategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubcategoryServiceImpl implements SubCategoryService {

    private final CategoryRepo categoryRepo;
    private final SubcategoryRepo subcategoryRepo;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;
    private final FileUploadService fileUploadService;

    @Value("${folder.name.SUBCATEGORY_IMAGE_FOLDER}")
    private String subcategoryImageFolder;

    @Override
    public List<SubcategoryDto> getSubCategoriesFromCategories(Integer categoryId) {
        CategoryEO fetchedCategotyFromId = categoryRepo.findById(categoryId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("category", "ID", categoryId.toString()));

        List<SubcategoryEO> subcategoiesFromCategories = subcategoryRepo.findByCategoryEO(fetchedCategotyFromId);

        return subcategoiesFromCategories.stream()
                .map(e -> modelMapper.map(e, SubcategoryDto.class))
                .toList();

    }

    @Override
    public List<SubcategoryDto> getAllSubCategories() {
        List<SubcategoryEO> subcategoryEOs = subcategoryRepo.findAll();
        return subcategoryEOs
                .stream()
                .map(e -> modelMapper.map(e, SubcategoryDto.class))
                .toList();
    }

    @Override
    public SubcategoryDto addSubCategory(String data, MultipartFile file) {
        Map<String, Object> dataMap = null;
        try {
            dataMap = objectMapper.readValue(data, new TypeReference<Map<String, Object>>() {
            });

        } catch (Exception e) {
        }

        String subcategoryName = dataMap.get("subcategoryName").toString();
        Integer categoryId = Integer.parseInt(dataMap.get("category").toString());

        CategoryEO fetchedCategotyFromId = categoryRepo.findById(categoryId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("category", "ID", categoryId.toString()));

        SubcategoryEO subcategoryEO = new SubcategoryEO();
        subcategoryEO.setCategoryEO(fetchedCategotyFromId);
        subcategoryEO.setSubCategoryName(subcategoryName);
        subcategoryEO.setSubCategorySku("SUBCAT" + UUID.randomUUID().toString());

        String imageUrl = null;

        if (file != null) {
            imageUrl = fileUploadService.uploadFile(file, subcategoryImageFolder);
        }

        if (imageUrl != null) {
            subcategoryEO.setImageUrl(imageUrl);
        }

        SubcategoryEO saved = subcategoryRepo.save(subcategoryEO);

        return modelMapper.map(saved, SubcategoryDto.class);

    }

    @Override
    public SubcategoryDto getSubCategoryById(Integer subcatgoryId) {

        SubcategoryEO subcategoryEO = subcategoryRepo.findById(subcatgoryId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "subcategory",
                                "ID",
                                subcatgoryId.toString()));

        SubcategoryDto subcategoryDto = modelMapper.map(subcategoryEO, SubcategoryDto.class);
        return subcategoryDto;
    }

    @Override
    public Map<?, ?> deleteSubcategory(Integer subcategoryId) {
        SubcategoryEO subcategoryEO = subcategoryRepo.findById(subcategoryId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "subcategory",
                                "ID",
                                subcategoryId.toString()));

        subcategoryRepo.delete(subcategoryEO);

        return Map.of("msg", "subcategory deleted");
    }

    @Override
    public SubcategoryDto updateSubcategory(Integer subcategoryId, SubcategoryDto updateSubcategoryDto) {
        SubcategoryEO fetched = subcategoryRepo.findById(subcategoryId)
                .orElseThrow(() -> new ResourceNotFoundException("subcategory", "ID", subcategoryId.toString()));

        if (updateSubcategoryDto.getSubCategoryName() != null) {
            fetched.setSubCategoryName(updateSubcategoryDto.getSubCategoryName());
        }

        SubcategoryEO saved = subcategoryRepo.save(fetched);

        SubcategoryDto updated = modelMapper.map(saved, SubcategoryDto.class);

        return updated;

    }

}
