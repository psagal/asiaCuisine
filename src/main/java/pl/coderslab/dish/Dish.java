package pl.coderslab.dish;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import pl.coderslab.dish.enums.Country;
import pl.coderslab.dish.enums.FoodType;
import pl.coderslab.dish.recipe.Recipe;
import pl.coderslab.dish.taste.Taste;
import pl.coderslab.users.User;

@Entity
@Getter
@Setter
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Recipe recipe;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Country country;
    @NotNull
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Taste taste;
    @NotNull
    @Enumerated(EnumType.STRING)
    private FoodType foodType;
    private String imageUrl;
    private boolean isUserCreated;
    @ManyToOne
    private User user;




}
