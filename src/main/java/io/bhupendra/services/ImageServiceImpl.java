package io.bhupendra.services;

import io.bhupendra.domain.Recipe;
import io.bhupendra.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService{

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void saveImage(String recipeId, MultipartFile file) {

        log.debug("Saving Image on Recipe id :" +recipeId );        try {

            Recipe recipe = recipeRepository.findById(recipeId).get();

            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;
            for (byte b : file.getBytes()) {
                byteObjects[i++] = b;
            }
            recipe.setImage(byteObjects);
            recipeRepository.save(recipe);

            log.debug("Saving Image on Recipe id :" + recipeId);

        } catch (IOException ex) {
            //todo handle better
            log.error("error occurred");
            ex.printStackTrace();
        }

    }
}
