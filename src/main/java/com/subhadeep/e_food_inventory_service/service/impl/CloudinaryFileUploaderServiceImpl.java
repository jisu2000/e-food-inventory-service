package com.subhadeep.e_food_inventory_service.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.subhadeep.e_food_inventory_service.service.FileUploadService;
import com.subhadeep.e_food_inventory_service.utils.CloudinaryFileUplaoder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CloudinaryFileUploaderServiceImpl implements FileUploadService {

    private final CloudinaryFileUplaoder cloudinaryFileUplaoder;

    @Override
    public String uploadFile(MultipartFile file, String folderName) {
        Map<?, ?> uploadedImage = cloudinaryFileUplaoder.uploadImage(file, folderName);
        Object url = uploadedImage.getOrDefault("url", null);
        return url != null ? url.toString() : "";
    }

}
