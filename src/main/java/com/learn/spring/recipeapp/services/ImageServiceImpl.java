package com.learn.spring.recipeapp.services;

import com.learn.spring.recipeapp.models.Recipe;
import com.learn.spring.recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImageFile(Long recipeId, MultipartFile file) {

        try {
            Recipe recipe = recipeRepository.findById(recipeId).get();

            Byte[] bytes = new Byte[file.getBytes().length];

            int i=0;

            for (byte b:file.getBytes()) {
                bytes[i++] = b;
            }

            recipe.setImage(bytes);
            recipeRepository.save(recipe);

        }catch(Exception e) {
            log.error("Error while saving image....");
            e.printStackTrace();
        }

    }
}
