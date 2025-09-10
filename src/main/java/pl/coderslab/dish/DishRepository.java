package pl.coderslab.dish;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.coderslab.dish.enums.Spiciness;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long>, JpaSpecificationExecutor<Dish> {

    // BY USER
    List<Dish> findAllByIsUserCreatedFalse();

    List<Dish> findAllByUser_Id(Long id);

    // ### BY TASTE ###
        // filtering by chosen spiciness levels
        // TODO: metoda w serwisie
    @Query("SELECT d FROM Dish d WHERE d.taste.spiciness IN :spiciness")
    List<Dish> findAllByTaste_Spiciness(@Param("spiciness") List<Spiciness> spiciness);

        /*
    // filtering by one chosen dominant Taste
        // TODO: metoda w serwisie z ignoreCase i co jeśli brak takiego dania
    @Query("SELECT d FROM Dish d WHERE :dominantTaste MEMBER OF d.taste.dominantTastes")
    List<Dish> findAllByTaste_DominantTaste(String dominantTaste);


         */

}
