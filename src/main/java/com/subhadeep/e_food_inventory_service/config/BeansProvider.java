package com.subhadeep.e_food_inventory_service.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansProvider {
    
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mapper = new ModelMapper();
        return mapper;
    }
}
