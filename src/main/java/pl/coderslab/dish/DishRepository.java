package pl.coderslab.dish;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long>, JpaSpecificationExecutor<Dish> {

    // BY USER
    List<Dish> findAllByIsUserCreatedFalse();

    List<Dish> findAllByUser_Id(Long id);

    @Query(" SELECT d FROM Dish d WHERE LOWER( d.name) = LOWER( :name ) AND ((d.isUserCreated = FALSE OR d.user.id = :userId ))")
    Optional <Dish> findDishByNameIgnoreCase(String name, Long userId);

    @Query(" SELECT d FROM Dish d WHERE d.id = :id AND ((d.isUserCreated = FALSE OR d.user.id = :userId ))")
    Optional <Dish> findDishById(Long id, Long userId);

    @Query("SELECT d FROM Dish d WHERE d.id = :dishId AND d.isUserCreated = true AND d.user.id = :userId")
    Optional <Dish> findDishToDeleteOrUpdate(Long dishId, Long userId);


}
