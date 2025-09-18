package pl.coderslab.dish.dishDTO;

import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishWizardStep4DTO {
    private String description;
    private int difficulty;
    private String videoUrl;
}
