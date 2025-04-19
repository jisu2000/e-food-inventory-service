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

import com.subhadeep.e_food_inventory_service.dto.CategoryDto;
import com.subhadeep.e_food_inventory_service.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping(consumes = { "multipart/form-data" })
    public ResponseEntity<?> addCategory(
            @RequestPart(name = "FILE", required = false) MultipartFile file,
            @RequestPart("DATA") String data) {
        return new ResponseEntity<>(categoryService.addCategory(data, file), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllCategory() {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
        return new ResponseEntity<>(categoryService.deleteCategory(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSpecificCategoryById(
            @PathVariable Integer id) {
        return new ResponseEntity<>(categoryService.fetchCategoryById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(
            @PathVariable Integer id,
            @RequestBody CategoryDto uCategoryDto

    ) {
        return new ResponseEntity<>(categoryService.updateCategory(id, uCategoryDto), HttpStatus.OK);
    }
}
