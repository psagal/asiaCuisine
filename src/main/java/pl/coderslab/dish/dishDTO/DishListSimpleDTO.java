package pl.coderslab.dish.dishDTO;

import lombok.*;
import pl.coderslab.dish.enums.Country;
import pl.coderslab.dish.enums.FoodType;
import pl.coderslab.dish.enums.Spiciness;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishListSimpleDTO {
    private String name;
    private Country country;
    private FoodType foodType;
    private String imageUrl;
    private Spiciness spiciness;

}
