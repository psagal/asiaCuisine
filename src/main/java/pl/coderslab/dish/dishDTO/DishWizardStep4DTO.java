package pl.coderslab.dish.dishDTO;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishWizardStep4DTO {
    @NotBlank
    private String description;
    @NotNull
    @Range(min = 1, max = 4, message = "value must be between 1 and 4")
    private int difficulty;
    private String videoUrl;
}
