package pl.coderslab.dish.taste;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pl.coderslab.dish.enums.Spiciness;


@Entity
@Getter
@Setter
public class Taste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 500)
    private String dominantTastes;  // lista oddzielana przecinkami
    @Enumerated(EnumType.STRING)
    private Spiciness spiciness;

}
