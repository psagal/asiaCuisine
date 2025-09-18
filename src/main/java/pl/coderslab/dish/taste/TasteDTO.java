package pl.coderslab.dish.taste;

import jakarta.validation.constraints.Pattern;
import lombok.*;
import pl.coderslab.dish.enums.Spiciness;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TasteDTO {
    private List<@Pattern(regexp = ".*\\S.*", message = "field can not be whitespace only")String> dominantTastes;
    private Spiciness spiciness;
}
