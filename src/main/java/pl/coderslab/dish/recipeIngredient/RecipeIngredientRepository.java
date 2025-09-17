package pl.coderslab.dish.recipeIngredient;

import org.springframework.data.repository.CrudRepository;
import pl.coderslab.dish.recipe.Recipe;

import java.util.List;

public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredient, Long> {
    List<RecipeIngredient> findAllByRecipe(Recipe recipe);
}
