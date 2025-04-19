package com.subhadeep.e_food_inventory_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SubcategoryEO extends BaseEO{
    private String subCategorySku;
    private String imageUrl;
    private String subCategoryName;
    @ManyToOne
    private CategoryEO categoryEO;
}
