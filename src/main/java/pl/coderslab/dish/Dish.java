package pl.coderslab.dish;

import jakarta.persistence.*;
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
    private String name;
    @OneToOne
    private Recipe recipe;
    @Enumerated(EnumType.STRING)
    private Country country;
    @OneToOne
    private Taste taste;
    @Enumerated(EnumType.STRING)
    private FoodType foodType;
    private String imageUrl;
    private boolean isUserCreated;
    @ManyToOne
    private User user;




}
