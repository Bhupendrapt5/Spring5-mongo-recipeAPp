package io.bhupendra.controllers;

import io.bhupendra.commands.RecipeCommand;
import io.bhupendra.services.ImageService;
import io.bhupendra.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ImageControllerTest {

    MockMvc mockMvc;

    @Mock
    ImageService imageService;
    @Mock
    RecipeService recipeService;
    @InjectMocks
    ImageController controller;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    void showUploadImage() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId("1");

        when(recipeService.findCommandById(any())).thenReturn(command);

        mockMvc.perform(get("/recipe/1/image"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/imageform"));
    }

//    @Test
//    void testGetImageNumberFormatException() throws Exception {
//
//        mockMvc.perform(get("/recipe/kj/image"))
//                .andExpect(status().isBadRequest())
//                .andExpect(view().name("400error"));
//
//    }

    @Test
    public void handleImagePost() throws Exception {
        MockMultipartFile multipartFile =
                new MockMultipartFile("imagefile", "testing.txt", "text/plain",
                        "Spring Framework Guru".getBytes());

        mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/recipe/1/show"));

        verify(imageService, times(1)).saveImage(any(), any());
    }

    @Test
    void testRenderImageFromDb() throws Exception {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId("1");

        String imgString = "Fake Image String";

        Byte[] bytesArray = new Byte[imgString.getBytes().length];
        int i = 0;

        for(Byte aByte : imgString.getBytes()){
            bytesArray[i++] = aByte;
        }
         recipeCommand.setImage(bytesArray);

        when(recipeService.findCommandById(any())).thenReturn(recipeCommand);

        //when
        MockHttpServletResponse mockResponse = mockMvc.perform(get("/recipe/1/recipeimage"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        //then
        byte[] responseArray = mockResponse.getContentAsByteArray();

        assertEquals(imgString.getBytes().length, responseArray.length);

    }
}