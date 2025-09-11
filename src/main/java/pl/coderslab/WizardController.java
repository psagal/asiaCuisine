package pl.coderslab;


import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import pl.coderslab.dish.Dish;
import pl.coderslab.dish.DishService;
import pl.coderslab.dish.dishDTO.DishByIdDTO;
import pl.coderslab.dish.dishDTO.DishWizardDto;
import pl.coderslab.dish.ingredient.IngredientService;
import pl.coderslab.dish.recipe.RecipeDTO;
import pl.coderslab.dish.recipeIngredient.RecipeIngredientDTO;
import pl.coderslab.dish.taste.TasteDTO;

import java.util.List;

@RestController
@RequestMapping("/create-dish")
@SessionAttributes("dishDraft")
public class WizardController {

    private static final Long USER_ID = 1L;
    private final IngredientService ingredientService;
    private final DishService dishService;

    public WizardController(IngredientService ingredientService, DishService dishService) {
        this.ingredientService = ingredientService;
        this.dishService = dishService;
    }

    @ModelAttribute("dishDraft")
    public DishByIdDTO getDishDraft() {
        DishByIdDTO dto = new DishByIdDTO();
        return dto;
    }

    // Step 1 -  basic dish information
    @PostMapping("/step1")
    public ResponseEntity<DishByIdDTO> step1(
            @RequestBody DishWizardDto inputDto,
            @ModelAttribute("dishDraft") DishByIdDTO dto,
            HttpSession session) {

        dto.setName(inputDto.getName());
        dto.setCountry(inputDto.getCountry());
        dto.setFoodType(inputDto.getFoodType());
        dto.setImageUrl(inputDto.getImageUrl());

        session.setAttribute("step1Done", true);
        return ResponseEntity.ok(dto);
    }

    // Step 2 - Taste of the dish information
    @PostMapping("/step2")
    public ResponseEntity<?> step2(
            @RequestBody DishWizardDto inputDto,
            @ModelAttribute DishByIdDTO dto,
            HttpSession session) {
        if (session.getAttribute("step1Done") == null) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Step 1 is not done, please start again");
        }
        TasteDTO tasteDTO = new TasteDTO();
        tasteDTO.setSpiciness(inputDto.getSpiciness());
        tasteDTO.setDominantTastes(inputDto.getDominantTastes());
        dto.setTaste(tasteDTO);
        session.setAttribute("step2Done", true);
        return ResponseEntity.ok(dto);
    }

    // Step 3 - Recipe Ingredients with unit and amount
    @PostMapping("/step3")
    public ResponseEntity<?> step3(
            @RequestBody List<RecipeIngredientDTO> inputDto,
            @ModelAttribute DishByIdDTO dto,
            HttpSession session) {
        if (session.getAttribute("step2Done") == null) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Step 2 is not done, please start again");
        }
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setRecipeIngredients(inputDto);

        dto.setRecipe(recipeDTO);
        session.setAttribute("step3Done", true);
        return ResponseEntity.ok(dto);
    }

    // Step 4 - recipe instructions and saving the dish
    @PostMapping("/step4")
    public ResponseEntity<?> step4(
            @RequestBody DishWizardDto inputDto,
            @ModelAttribute DishByIdDTO dto,
            HttpSession session,
            SessionStatus status
    ){
        if (session.getAttribute("step3Done") == null) {
            return ResponseEntity.status(HttpStatus.GONE).body("Step 3 is not done, please start again");
        }

        dto.getRecipe().setDescription(inputDto.getDescription());
        dto.getRecipe().setDifficulty(inputDto.getDifficulty());
        dto.getRecipe().setVideoUrl(inputDto.getVideoUrl());

        Dish dish = dishService.addNewDish(USER_ID, dto);
        status.setComplete();
        session.removeAttribute("step3Done");
        session.removeAttribute("step2Done");
        session.removeAttribute("step1Done");
        return ResponseEntity.ok(dish);
    }




}
