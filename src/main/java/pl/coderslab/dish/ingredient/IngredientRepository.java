package pl.coderslab.dish.ingredient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.users.User;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findAllByIsUserCreatedFalse();
    List<Ingredient> findAllByUser(User user);

    @Query("SELECT i FROM Ingredient i WHERE i.name = :name AND (i.isUserCreated = FALSE OR i.user.id = :userId )")
    Ingredient findByNameIgnoreCase(String name, Long userId);
}
