package pl.coderslab.dish.recipeIngredient;

import jakarta.persistence.*;
import lombok.*;
import pl.coderslab.dish.ingredient.Ingredient;
import pl.coderslab.dish.recipe.Recipe;

@Entity
@Getter
@Setter
public class RecipeIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    private Double amount;
    private String unit;
}
