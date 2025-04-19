package com.subhadeep.e_food_inventory_service.exception;

public class ResourceNotFoundException extends RuntimeException{
    
    public ResourceNotFoundException(){
        super("Resource not found");
    }

    public ResourceNotFoundException(String entity,String field, String value){
        super(entity+" not found with "+field+" : "+value);
    }

    public ResourceNotFoundException(String msg){
        super(msg);
    }
}
