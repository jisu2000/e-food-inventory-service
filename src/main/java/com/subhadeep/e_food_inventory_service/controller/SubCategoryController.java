package com.subhadeep.e_food_inventory_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.subhadeep.e_food_inventory_service.dto.SubcategoryDto;
import com.subhadeep.e_food_inventory_service.service.SubCategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/subcategory")
@RequiredArgsConstructor
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    @PostMapping(consumes = { "multipart/form-data" })
    public ResponseEntity<?> addSubcategory(
            @RequestPart(name = "FILE", required = false) MultipartFile file,
            @RequestPart("DATA") String data) {

        return new ResponseEntity<>(subCategoryService.addSubCategory(data, file), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> loadAllSubcategories() {
        return new ResponseEntity<>(subCategoryService.getAllSubCategories(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubCategory(
            @PathVariable Integer id) {
        return new ResponseEntity<>(subCategoryService.deleteSubcategory(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSingleSubcategory(
            @PathVariable Integer id) {
        return new ResponseEntity<>(subCategoryService.getSubCategoryById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubCategory(
            @PathVariable Integer id,
            @RequestBody SubcategoryDto updateDto) {
        return new ResponseEntity<>(subCategoryService.updateSubcategory(id, updateDto), HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getSubCategoryFromCategory(
            @PathVariable Integer id) {
        return new ResponseEntity<>(subCategoryService.getSubCategoriesFromCategories(id),
                HttpStatus.OK);
    }
}
