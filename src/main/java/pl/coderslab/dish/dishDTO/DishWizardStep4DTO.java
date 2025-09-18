package pl.coderslab.dish.dishDTO;

import jakarta.validation.constraints.*;
import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishWizardStep4DTO {
    @NotBlank
    private String description;
    @NotNull
    @Min(value = 1, message = "value must be between 1 and 4")
    @Max(value = 4, message = "value must be between 1 and 4")
    private int difficulty;
    private String videoUrl;
}
