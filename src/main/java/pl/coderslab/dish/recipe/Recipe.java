package pl.coderslab.dish.recipe;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pl.coderslab.dish.recipeIngredient.RecipeIngredient;

import java.util.List;

@Entity
@Getter
@Setter
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int difficulty;
    private String description;
    private String videoUrl;
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeIngredient> recipeIngredients;

}
