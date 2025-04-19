package com.subhadeep.e_food_inventory_service.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Setter
@ToString
public class CategoryEO extends BaseEO{
    private String categorySku;
    private String imageUrl;
    private String categoryName;
    @OneToMany(mappedBy = "categoryEO",cascade = CascadeType.ALL)
    private List<SubcategoryEO> subCategoryEOs;
}
