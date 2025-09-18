package pl.coderslab.dish.dishDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import pl.coderslab.dish.enums.Country;
import pl.coderslab.dish.enums.FoodType;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishWizardStep1DTO {
    @NotBlank
    private String name;
    @NotNull
    private Country country;
    @NotNull
    private FoodType foodType;
    private String imageUrl;
}
