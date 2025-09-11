package pl.coderslab.dish.dishDTO;

import lombok.*;
import pl.coderslab.dish.enums.Country;
import pl.coderslab.dish.enums.FoodType;
import pl.coderslab.dish.enums.Spiciness;
import pl.coderslab.dish.recipeIngredient.RecipeIngredientDTO;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishWizardDto {
    //Step1
    private String name;
    private Country country;
    private FoodType foodType;
    private String imageUrl;

    //Step2 - Taste
    private Spiciness spiciness;
    private List<String> dominantTastes;

    //Step3 - Recipe Ingredients
    private List<RecipeIngredientDTO> ingredients;

    //Step4 - Recipe
    private String description;
    private int difficulty;
    private String videoUrl;

}
