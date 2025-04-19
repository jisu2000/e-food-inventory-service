package com.subhadeep.e_food_inventory_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subhadeep.e_food_inventory_service.model.CategoryEO;

public interface CategoryRepo extends JpaRepository<CategoryEO,Integer>{

}
