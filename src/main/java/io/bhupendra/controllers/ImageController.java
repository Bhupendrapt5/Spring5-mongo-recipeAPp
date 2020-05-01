package io.bhupendra.controllers;

import io.bhupendra.commands.RecipeCommand;
import io.bhupendra.services.ImageService;
import io.bhupendra.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Controller
public class ImageController {

    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{recipeId}/image")
    public String showUploadImage(@PathVariable String recipeId, Model model){

        model.addAttribute("recipe", recipeService.findCommandById(recipeId));

        log.debug("Displaying Image ofr recipe id "+ recipeId);

        return "recipe/imageform";
    }

    @PostMapping("/recipe/{id}/image")
    public String handleImagePost(@PathVariable String id,
                                  @RequestParam("imagefile") MultipartFile file){

        imageService.saveImage(id, file);

        return "redirect:/recipe/"+id+"/show";
    }

    @GetMapping("/recipe/{recipeId}/recipeimage")
    public void renderImageFormDb(@PathVariable String recipeId, HttpServletResponse response) throws IOException {

        RecipeCommand recipeCommand = recipeService.findCommandById(recipeId);

        if(recipeCommand.getImage()!=null){
            log.debug("rendering Image from Db");

            byte[] bytes = new byte[recipeCommand.getImage().length];
            int i = 0;

            for (byte wrapByte : recipeCommand.getImage()){

                bytes[i++] = wrapByte; //Auto Unboxing
            }

            response.setContentType("image/jpeg");
            InputStream stream = new ByteArrayInputStream(bytes);
            IOUtils.copy(stream, response.getOutputStream());
        }
    }
}
