package pl.coderslab.dish;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import pl.coderslab.dish.recipe.Recipe;
import pl.coderslab.dish.taste.Taste;

@Entity
@Getter
@Setter
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne
    private Recipe recipe;
    private String countryOfOrigin;
    @OneToOne
    private Taste taste;
    private boolean isVegetarian;
    private boolean isUserCreated;
    private String imageUrl;





}
