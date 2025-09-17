package pl.coderslab.dish.ingredient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.dish.enums.IngredientCategory;
import pl.coderslab.users.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findAllByIsUserCreatedFalse();
    List<Ingredient> findAllByUser(User user);

    @Query("SELECT  i FROM  Ingredient i WHERE i.category = :category AND (i.isUserCreated = FALSE OR i.user.id = :userId )")
    List<Ingredient> findAllByCategory(IngredientCategory category, Long userId);

    @Query("SELECT i FROM Ingredient i WHERE LOWER( i.name) = LOWER( :name ) AND (i.isUserCreated = FALSE OR i.user.id = :userId )")
    Optional <Ingredient> findByNameIgnoreCase(String name, Long userId);
}
