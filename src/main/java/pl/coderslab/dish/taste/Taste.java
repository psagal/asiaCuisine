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
    @ElementCollection
    @CollectionTable(name = "taste_dominant_tastes", joinColumns = @JoinColumn(name = "taste_id"))
    @Column(name = "dominant_tastes")
    private List<String> dominantTastes;
    @Enumerated(EnumType.STRING)
    private Spiciness spiciness;

}
