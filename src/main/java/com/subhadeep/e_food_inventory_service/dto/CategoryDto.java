package com.subhadeep.e_food_inventory_service.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDto {

    private Integer id;
    private String createdAt;
    private String updatedAt;
    private String categorySku;
    private String imageUrl;
    private String categoryName;

}
