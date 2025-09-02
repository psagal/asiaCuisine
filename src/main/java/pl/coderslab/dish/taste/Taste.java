package pl.coderslab.dish.taste;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pl.coderslab.dish.enums.Spiciness;

import java.util.List;

@Entity
@Getter
@Setter
public class Taste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private List<String> dominantTastes;
    @Enumerated(EnumType.STRING)
    private Spiciness spiciness;

}
