package pl.coderslab.dish.taste;

import lombok.*;
import pl.coderslab.dish.enums.Spiciness;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TasteDTO {
    private List<String> dominantTastes;
    private Spiciness spiciness;
}
