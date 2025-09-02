package pl.coderslab.dish.dishDTO;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.dish.enums.Country;
import pl.coderslab.dish.enums.FoodType;
import pl.coderslab.dish.enums.Spiciness;

@Getter
@Setter
public class DishListDTO {
    private String name;
    private Country country;
    private FoodType foodType;
    private String imageUrl;
    private Spiciness spiciness;

}
