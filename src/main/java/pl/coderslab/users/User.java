package pl.coderslab.users;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pl.coderslab.dish.Dish;

import java.util.List;
@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Dish> favouriteDishes;

}
