package pl.coderslab.dish.dishDTO;

import lombok.*;
import pl.coderslab.dish.enums.Country;
import pl.coderslab.dish.enums.FoodType;
import pl.coderslab.dish.enums.Spiciness;
import pl.coderslab.dish.ingredient.IngredientDTO;
import pl.coderslab.dish.recipe.RecipeDTO;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishListDetailedDTO {

    private String name;
    private Country country;
    private FoodType foodType;
    private String imageUrl;

    private Spiciness spiciness;
    private List<IngredientDTO> ingredients;
    private RecipeDTO recipe;

}
