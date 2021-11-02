package com.example.Picnic.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Recipe")
public class Recipe {
    @Id
    @SequenceGenerator(
            name = "recipe_sequence",
            sequenceName = "recipe_sequence",
            allocationSize =  1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "recipe_sequence"
    )
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    @ManyToOne()
    @JoinColumn(name = "PicnicUserId", nullable = false)
    private PicnicUser picnicUser;

    @OneToMany(mappedBy =  "recipe")
    private List<Ingredient> ingredients = new ArrayList<>();

    public Recipe() {
    }

    public Recipe(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PicnicUser getPicnicUser() {
        return picnicUser;
    }

    public void setPicnicUser(PicnicUser picnicUser) {
        this.picnicUser = picnicUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Entity(name = "Ingredient")
    @Table(indexes = @Index(name = "uniqueIndexIngredient", columnList = "RecipeId, ProductId", unique = true))
    public static class Ingredient {

        @Id
        @SequenceGenerator(
                name = "recipe_sequence",
                sequenceName = "recipe_sequence",
                allocationSize =  1
        )
        @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "recipe_sequence"
        )
        @Column(name = "Id", nullable = false)
        @JsonIgnore
        private Long id;

        @ManyToOne
        @JoinColumn(name = "RecipeId", nullable = false)
        private Recipe recipe;

        @Column(name = "ProductId", nullable = false)
        private String productId;

        @Column(name = "Count", nullable = false)
        private int count;

        public Ingredient() {
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public void setRecipe(Recipe recipe) {
            this.recipe = recipe;
        }
    }





}


