package com.subhadeep.e_food_inventory_service.model;

import jakarta.persistence.Entity;
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
}
