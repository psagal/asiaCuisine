package pl.coderslab.dish.recipe;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import pl.coderslab.dish.recipeIngredient.RecipeIngredient;

import java.util.List;

@Entity
@Getter
@Setter
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Min(value = 1, message = "value must be between 1 and 4")
    @Max(value = 4, message = "value must be between 1 and 4")
    private int difficulty;
    @Size(min = 20)
    private String description;
    private String videoUrl;
    @JsonManagedReference
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeIngredient> recipeIngredients;

}
