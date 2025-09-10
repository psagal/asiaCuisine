package pl.coderslab.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IngredientNotFoundException extends RuntimeException {
    public IngredientNotFoundException(String message) {

        super(message);
    }
}
