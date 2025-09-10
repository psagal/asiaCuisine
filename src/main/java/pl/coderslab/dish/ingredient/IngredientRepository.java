package pl.coderslab.dish.ingredient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.users.User;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findAllByIsUserCreatedFalse();
    List<Ingredient> findAllByUser(User user);

    Ingredient findByNameIgnoreCase(String name);
}
