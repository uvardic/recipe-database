package recipe.recipe;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recipe/")
public class RecipeRestController {

    private final RecipeService service;

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteById(@RequestParam Long id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<RecipeDTO> save(@Valid @RequestBody RecipeDTO request) {
        return new ResponseEntity<>(service.save(request), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<RecipeDTO> update(@RequestParam Long id, @Valid @RequestBody RecipeDTO request) {
        return new ResponseEntity<>(service.update(id, request), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<RecipeDTO> findById(@RequestParam Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RecipeDTO>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

}
