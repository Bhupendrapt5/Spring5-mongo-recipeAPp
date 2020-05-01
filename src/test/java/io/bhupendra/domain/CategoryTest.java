package io.bhupendra.domain;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CategoryTest {


    Category category;

    @BeforeEach
    public void setup(){
        category=new Category();
    }

    @Test
    public void getId() {
        String idVal = "4L";

        category.setId(idVal);
        assertEquals(idVal, category.getId() );
    }

    @Test
    public void getDescription() {
    }

    @Test
    public void getRecipes() {
    }
}