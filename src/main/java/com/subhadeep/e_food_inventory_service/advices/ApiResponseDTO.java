package com.subhadeep.e_food_inventory_service.advices;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ApiResponseDTO<T> {

    private T data;
    private Integer status;
    private boolean success;
    private ErrorResponse errorResponse;
    private String timeStamp;


    public ApiResponseDTO(){
        this.timeStamp = LocalDateTime.now().toString();
    }
    

    public ApiResponseDTO(T data){
        this();
        this.data = data;
        this.success = true;
        status = 200;
    }

    public ApiResponseDTO (ErrorResponse errorResponse){
        this();
        this.errorResponse = errorResponse;
        this.status=errorResponse.getStatus();
        this.success=false;
    }
}
