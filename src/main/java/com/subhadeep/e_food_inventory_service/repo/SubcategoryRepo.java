package com.subhadeep.e_food_inventory_service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subhadeep.e_food_inventory_service.model.CategoryEO;
import com.subhadeep.e_food_inventory_service.model.SubcategoryEO;

public interface SubcategoryRepo extends JpaRepository<SubcategoryEO, Integer> {
    List<SubcategoryEO> findByCategoryEO(CategoryEO categoryEO);
}
