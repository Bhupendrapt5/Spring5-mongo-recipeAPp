package io.bhupendra.repositories;


import io.bhupendra.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, String> {


}
