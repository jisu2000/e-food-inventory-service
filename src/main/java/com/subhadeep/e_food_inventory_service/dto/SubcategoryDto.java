package com.subhadeep.e_food_inventory_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubcategoryDto {
    private Integer id;
    private String createdAt;
    private String updatedAt;
    private String subCategorySku;
    private String imageUrl;
    private String subCategoryName;
    private CategoryDto categoryEO;
}
