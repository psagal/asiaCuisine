package pl.coderslab.dish;

import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import pl.coderslab.dish.enums.Spiciness;
import pl.coderslab.dish.ingredient.Ingredient;
import pl.coderslab.dish.recipe.Recipe;
import pl.coderslab.dish.recipeIngredient.RecipeIngredient;
import pl.coderslab.dish.taste.Taste;

import java.util.List;

public class DishSpecification {

    public static Specification<Dish> dishName(String name) {
        return (root, query, builder) -> builder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Dish> dishSpiciness(List<Spiciness> spiciness) {
        return (root, query, builder ) -> {
                Join<Dish, Taste> tasteJoin = root.join("taste");
                return tasteJoin.get("spiciness").in(spiciness);
        };
    }

    public static Specification<Dish> dishDominantTaste(String dominantTaste) {
        return (root, query, builder) -> builder.isMember(dominantTaste, root.get("dominantTaste").toString().split(","));
    }

    public static Specification<Dish> hasIngredient(List<Ingredient> ingredients) {
        return (root, query, builder) ->
        {
            query.distinct(true);
            Join<Dish, Recipe> recipeJoin = root.join("recipe");
            Join<Recipe, RecipeIngredient> recipeIngredientJoin = recipeJoin.join("recipeIngredients");
            Join<RecipeIngredient, Ingredient> ingredientJoin = recipeIngredientJoin.join("ingredient");
            return ingredientJoin.get("name").in(ingredients);
        };
    }
}
