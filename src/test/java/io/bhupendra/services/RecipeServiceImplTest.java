package io.bhupendra.services;

import io.bhupendra.domain.Recipe;
import io.bhupendra.exceptions.NotFoundException;
import io.bhupendra.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    @InjectMocks
    RecipeServiceImpl recipeService;


    Recipe recipe = new Recipe();

    @BeforeEach
    public void setUp() throws Exception {

    }

    @Test
    public void getRecipes() {

        Set<Recipe> recipeData = new HashSet<>();
        recipeData.add(recipe);

        when(recipeService.getRecipes()).thenReturn(recipeData);

        Set<Recipe> recipes = recipeService.getRecipes();

        //Testing if recipe set count is equal to actual count
        assertEquals(recipes.size(), 1);

        //verifying if recipeRepository is called only once
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    void getRecipeById() {
        recipe.setId("1l");
        Optional<Recipe> recipeOptional = Optional.of(recipe);
        when(recipeRepository.findById(any())).thenReturn(recipeOptional);

        Recipe returnRecipe = recipeService.findById("1l");
        assertNotNull(returnRecipe);
        verify(recipeRepository, times(1)).findById(any());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    void getRecipeByIdNotFound() throws Exception {

        Optional<Recipe> recipeOptional = Optional.empty();

        when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);

        assertThrows(NotFoundException.class, () -> recipeService.findById("1L"));

    }


    @Test
    void testDeleteByID() {
        String idToDelete = "2l";
        recipeService.deleteById(idToDelete);

        //no "when", since method has void return type

        verify(recipeRepository,times(1)).deleteById(anyString());
    }
}