package pl.coderslab.dish.ingredient;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Ingredient {
    private String name;
    private int amount;
    private String unit;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
