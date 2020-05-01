package io.bhupendra.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void saveImage(String recipeId, MultipartFile multipartFile);
}
