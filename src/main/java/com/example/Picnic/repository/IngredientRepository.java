package com.example.Picnic.repository;

import com.example.Picnic.model.entities.Recipe.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Ingredient I WHERE I.recipe.id = :recipeId")
    void deleteIngredientsFromRecipe(@Param("recipeId") Long recipeId);

    @Query("SELECT I FROM Ingredient I WHERE I.recipe.id = :recipeId AND I.productId = :productId")
    Optional<Ingredient> findByProductIdAndRecipeId(@Param("recipeId") Long recipeId, @Param("productId") String productId);

}
