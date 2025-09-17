package pl.coderslab;


import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import pl.coderslab.dish.DishService;
import pl.coderslab.dish.dishDTO.DishByIdDTO;
import pl.coderslab.dish.dishDTO.DishWizardDto;
import pl.coderslab.dish.dishDTO.DishWizardStep3DTO;
import pl.coderslab.dish.ingredient.IngredientService;
import pl.coderslab.dish.recipe.RecipeDTO;
import pl.coderslab.dish.taste.TasteDTO;
import pl.coderslab.exceptions.IngredientNotFoundException;

@RestController
@RequestMapping("/create-dish")
@SessionAttributes("dishDraft")
public class WizardController {

    private static final Long USER_ID = 1L;
    private final DishService dishService;
    private final IngredientService ingredientService;

    public WizardController(DishService dishService, IngredientService ingredientService) {
        this.dishService = dishService;
        this.ingredientService = ingredientService;
    }

    @ModelAttribute("dishDraft")
    public DishByIdDTO getDishDraft() {
        return new DishByIdDTO();
    }

    // Step 1 -  basic dish information
    @PostMapping("/step1")
    public ResponseEntity<DishByIdDTO> step1(
            @RequestBody DishWizardDto inputDto,
            @ModelAttribute("dishDraft") DishByIdDTO dishDraft) {

        //System.out.println(dishDraft);
        dishDraft.setName(inputDto.getName());
        dishDraft.setCountry(inputDto.getCountry());
        dishDraft.setFoodType(inputDto.getFoodType());
        dishDraft.setImageUrl(inputDto.getImageUrl());
        //System.out.println(dishDraft);

        return ResponseEntity.ok(dishDraft);
    }

    // Step 2 - Taste of the dish information
    @PostMapping("/step2")
    public ResponseEntity<?> step2(
            @RequestBody DishWizardDto inputDto,
            @ModelAttribute("dishDraft") DishByIdDTO dishDraft) {
        //System.out.println(dishDraft);
        if (dishDraft.getName() == null) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Step 1 is not done, please start again");
        }
        TasteDTO tasteDTO = new TasteDTO();
        tasteDTO.setSpiciness(inputDto.getSpiciness());
        tasteDTO.setDominantTastes(inputDto.getDominantTastes());
        dishDraft.setTaste(tasteDTO);
        return ResponseEntity.ok(dishDraft);
    }

    // Step 3 - Recipe Ingredients with unit and amount
    @PostMapping("/step3")
    public ResponseEntity<?> step3(
            @RequestBody DishWizardStep3DTO inputDto,
            @ModelAttribute("dishDraft") DishByIdDTO dishDraft
            ) {
        if (dishDraft.getTaste() == null) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Step 2 is not done, please start again");
        }
        inputDto.getIngredients().stream()
                .forEach(ingredient ->
                        ingredientService.findIngredientByName(ingredient.getName(), USER_ID)
                                .orElseThrow(() -> new IngredientNotFoundException("ingredient: " + ingredient.getName() + " not found")));
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setRecipeIngredients(inputDto.getIngredients());

        dishDraft.setRecipe(recipeDTO);
        return ResponseEntity.ok(dishDraft);
    }

    // Step 4 - recipe instructions and saving the dish
    @PostMapping("/step4")
    public ResponseEntity<?> step4(
            @RequestBody DishWizardDto inputDto,
            @ModelAttribute("dishDraft") DishByIdDTO dishDraft,
            SessionStatus status
    ){
        if (dishDraft.getRecipe() == null) {
            return ResponseEntity.status(HttpStatus.GONE).body("Step 3 is not done, please start again");
        }

        dishDraft.getRecipe().setDescription(inputDto.getDescription());
        dishDraft.getRecipe().setDifficulty(inputDto.getDifficulty());
        dishDraft.getRecipe().setVideoUrl(inputDto.getVideoUrl());

        dishService.addNewDish(USER_ID, dishDraft);
        status.setComplete();
        return ResponseEntity.ok(dishDraft);
    }




}
