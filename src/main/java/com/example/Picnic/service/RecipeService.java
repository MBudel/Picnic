package com.example.Picnic.service;

import com.example.Picnic.exception.RecipeNotFoundException;
import com.example.Picnic.exception.UserNotFoundException;
import com.example.Picnic.model.entities.Recipe;
import com.example.Picnic.model.entities.Recipe.Ingredient;
import com.example.Picnic.model.rest.external.UserDetails;
import com.example.Picnic.model.rest.post.IngredientPost;
import com.example.Picnic.repository.IngredientRepository;
import com.example.Picnic.repository.PicnicUserRepository;
import com.example.Picnic.repository.RecipeRepository;
import com.example.Picnic.repository.external.RepositoryExternal_User;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final PicnicUserRepository picnicUserRepository;
    private final RepositoryExternal_User repositoryExternalUser;

    public RecipeService(RecipeRepository recipeRepository, PicnicUserRepository picnicUserRepository, IngredientRepository ingredientRepository, RepositoryExternal_User repositoryExternalUser) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.picnicUserRepository = picnicUserRepository;
        this.repositoryExternalUser = repositoryExternalUser;
    }

    public Recipe addRecipe(Recipe recipe, String token){
        UserDetails userDetails = repositoryExternalUser.validateToken(token);
        recipe.setPicnicUser(picnicUserRepository.findUserByusername(userDetails.getContact_email())
                .orElseThrow(() -> new UserNotFoundException("User with name " + userDetails.getContact_email() + "not found")));
        return recipeRepository.save(recipe);
    }

    public List<Recipe> findAllRecipesForUser(String token){
        UserDetails userDetails = repositoryExternalUser.validateToken(token);
        return recipeRepository.findbyUsername(userDetails.getContact_email());
    }

    public Recipe findRecipeById(Long id, String token){
        repositoryExternalUser.validateToken(token);
        return recipeRepository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe with id" + id + "not found"));
    }

    public List<Recipe> deleteRecipe(Long id, String token){
        UserDetails userDetails = repositoryExternalUser.validateToken(token);
        ingredientRepository.deleteIngredientsFromRecipe(id);
        recipeRepository.deleteById(id);
        return recipeRepository.findbyUsername(userDetails.getContact_email());
    }

    public Recipe addIngredientToRecipe(IngredientPost ingredientPost, String token){
        repositoryExternalUser.validateToken(token);
        Ingredient ingredient = ingredientRepository.findByProductIdAndRecipeId(ingredientPost.getRecipeId(), ingredientPost.getProductid())
                .orElse(new Ingredient());
        long recipeId = ingredientPost.getRecipeId();
        ingredient.setProductId(ingredientPost.getProductid());
        ingredient.setCount(ingredient.getCount() + ingredientPost.getCount());
        ingredient.setRecipe(new Recipe(ingredientPost.getRecipeId()));
        ingredientRepository.save(ingredient);
        return recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe with id" + recipeId + "not found"));
    }

    public Recipe removeIngredientFromRecipe(IngredientPost ingredientPost, String token){
        repositoryExternalUser.validateToken(token);
        Ingredient ingredient = ingredientRepository.findByProductIdAndRecipeId(ingredientPost.getRecipeId(), ingredientPost.getProductid())
                .orElse(new Ingredient());
        long recipeId = ingredientPost.getRecipeId();
        if(ingredient.getId() != null){
            if(ingredient.getCount() <= ingredientPost.getCount()){
                ingredientRepository.deleteById(ingredient.getId());
            }
            else{
                ingredient.setCount(ingredient.getCount() - ingredientPost.getCount());
            }
        }
        return recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe with id" + recipeId + "not found"));
    }


}
