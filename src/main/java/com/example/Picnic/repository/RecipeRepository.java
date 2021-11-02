package com.example.Picnic.repository;

import com.example.Picnic.model.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository  extends JpaRepository<Recipe, Long> {
    @Query("SELECT R FROM Recipe R WHERE R.picnicUser.username = :username ORDER BY R.name")
    List<Recipe> findbyUsername(@Param("username") String username);

}
