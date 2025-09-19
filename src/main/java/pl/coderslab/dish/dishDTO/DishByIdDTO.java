package pl.coderslab.dish.dishDTO;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import pl.coderslab.dish.enums.Country;
import pl.coderslab.dish.enums.FoodType;
import pl.coderslab.dish.recipe.RecipeDTO;
import pl.coderslab.dish.taste.TasteDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DishByIdDTO {
    @Pattern(regexp = ".*\\S.*", message = "field can not be whitespace only")
    private String name;
    private Country country;
    private FoodType foodType;
    private String imageUrl;
    @Valid
    private RecipeDTO recipe;
    @Valid
    private TasteDTO taste;

}
