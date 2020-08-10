package recipe.recipe;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RecipeDTO {

    private Long id;

    @NotNull
    @NotBlank
    private String name;

    private String description;

}
