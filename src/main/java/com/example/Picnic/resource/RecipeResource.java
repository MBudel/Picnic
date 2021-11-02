package com.example.Picnic.resource;

import com.example.Picnic.model.entities.Recipe;
import com.example.Picnic.model.rest.post.IngredientPost;
import com.example.Picnic.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeResource {

    private final RecipeService recipeService;


    public RecipeResource(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping()
    public ResponseEntity<List<Recipe>> getRecipesForUser(@RequestHeader String token){
        List<Recipe> recipes = recipeService.findAllRecipesForUser(token);
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@RequestHeader String token, @PathVariable("id") Long id){
        Recipe recipe = recipeService.findRecipeById(id, token);
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Recipe> addRecipe(@RequestHeader String token, @RequestBody Recipe recipe){
        Recipe newRecipe = recipeService.addRecipe(recipe, token);
        return new ResponseEntity<>(newRecipe, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecipe(@RequestHeader String token, @PathVariable("id") Long id){
        List<Recipe> recipes = recipeService.deleteRecipe(id,token);
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @PostMapping("add_ingredient")
    public ResponseEntity<Recipe> addIngredientToRecipe(@RequestHeader String token, @RequestBody IngredientPost ingredientPost){
        Recipe updatedRecipe = recipeService.addIngredientToRecipe(ingredientPost, token);
        return new ResponseEntity<>(updatedRecipe, HttpStatus.OK);
    }

    @PostMapping("remove_ingredient")
    public ResponseEntity<Recipe> removeIngredientFromRecipe(@RequestBody IngredientPost ingredientPost, @RequestHeader String token){
        Recipe updatedRecipe = recipeService.removeIngredientFromRecipe(ingredientPost, token);
        return new ResponseEntity<>(updatedRecipe, HttpStatus.OK);
    }

}
