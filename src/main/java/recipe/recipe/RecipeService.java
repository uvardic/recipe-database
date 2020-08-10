package recipe.recipe;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import recipe.common.CycleAvoidingMappingContext;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository repository;

    private final RecipeMapper mapper;

    private final CycleAvoidingMappingContext mappingContext;

    @Transactional
    public void deleteById(Long id) {
        boolean notFound = repository.findById(id).isEmpty();
        if (notFound)
            throw new RecipeNotFoundException(String.format("Recipe with id: %d wasn't found!", id));
        repository.deleteById(id);
    }

    @Transactional
    public RecipeDTO save(RecipeDTO request) {
        Recipe savedEntity = repository.save(mapToEntity(request));
        return mapper.toDTO(savedEntity, mappingContext);
    }

    private Recipe mapToEntity(RecipeDTO dto) {
        return mapper.toEntity(dto, mappingContext);
    }

    private RecipeDTO mapToDTO(Recipe entity) {
        return mapper.toDTO(entity, mappingContext);
    }

    @Transactional
    public RecipeDTO update(Long existingId, RecipeDTO request) {
        RecipeDTO existingRecipe = findById(existingId);
        existingRecipe.setName(request.getName());
        existingRecipe.setDescription(request.getDescription());
        return save(existingRecipe);
    }

    public RecipeDTO findById(Long id) {
        return repository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RecipeNotFoundException(
                        String.format("Recipe with id: %d wasn't found!", id)
                ));
    }

    public List<RecipeDTO> findAll() {
        return mapToDTOList(repository.findAll());
    }

    private List<RecipeDTO> mapToDTOList(List<Recipe> entities) {
        return mapper.toDTOList(entities, mappingContext);
    }

}
