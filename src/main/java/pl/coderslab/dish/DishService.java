package pl.coderslab.dish;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {
    private final DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    // ### MAPOWANIE DTO ###
    // Lista podstawowa

    // WYŚWIETLANIE
    public List<Dish> findAll() {
        return dishRepository.findAllByIsUserCreatedFalse();
    }

}
