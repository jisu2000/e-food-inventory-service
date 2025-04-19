package com.subhadeep.e_food_inventory_service.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    String uploadFile(MultipartFile file, String folderName);
}
