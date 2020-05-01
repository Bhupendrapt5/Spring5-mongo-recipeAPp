package io.bhupendra.controllers;

import io.bhupendra.domain.Recipe;
import io.bhupendra.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith({MockitoExtension.class})
public class IndexControllerTest {
    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    @InjectMocks
    IndexController indexController;


    @BeforeEach
    public void setUp() throws Exception {

    }

    @Test
    public void mockMVcTest() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void getIndex() {
        //---given---
        Set<Recipe> recipeSet = new HashSet<>();

        Recipe r1 = new Recipe();
        r1.setId("1");
        recipeSet.add(r1);

        Recipe r2 = new Recipe();
        r1.setId("2");
        recipeSet.add(r2);

        when(recipeService.getRecipes()).thenReturn(recipeSet);

        ArgumentCaptor<Set<Recipe>> setArgumentCaptor = ArgumentCaptor.forClass(Set.class);

        //---when---
        String viewname =indexController.getIndexPage(model);

        //---then---

        assertEquals("index", viewname );

        //verifying how many times recipeService.getRecipe() is called
        verify(recipeService, times(1)).getRecipes();

        //verifying how many times model.addAttribute() is called
        verify(model, times(1))
                .addAttribute(eq("recipes"),setArgumentCaptor.capture());

        Set<Recipe> recipes = setArgumentCaptor.getValue();
        assertEquals(2, recipes.size());
    }
}