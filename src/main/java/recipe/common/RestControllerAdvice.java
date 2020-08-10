package recipe.common;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import recipe.recipe.RecipeNotFoundException;

@Slf4j
@ControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler(RecipeNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleRecipeNotFoundException(RecipeNotFoundException ex) {
        return new ResponseEntity<>(new ExceptionResponse(ex), HttpStatus.NOT_FOUND);
    }

    @Getter
    private static class ExceptionResponse {

        private final String className;

        private final String message;

        private ExceptionResponse(RuntimeException ex) {
            this.className = ex.getClass().getName();
            this.message = ex.getMessage();
        }

    }

}
