package recipe.recipe;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import recipe.common.CycleAvoidingMappingContext;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecipeMapper {

    RecipeDTO toDTO(Recipe entity, @Context CycleAvoidingMappingContext context);

    Recipe toEntity(RecipeDTO dto, @Context CycleAvoidingMappingContext context);

    List<RecipeDTO> toDTOList(List<Recipe> entities, @Context CycleAvoidingMappingContext context);

}
