package pl.coderslab.dish.ingredient;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pl.coderslab.dish.enums.IngredientCategory;

@Entity
@Getter
@Setter
public class Ingredient {
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private IngredientCategory category;


}
