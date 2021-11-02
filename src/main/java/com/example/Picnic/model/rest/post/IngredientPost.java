package com.example.Picnic.model.rest.post;

public class IngredientPost {
    private String productid;
    private int count;
    private long recipeId;

    public IngredientPost() {
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }
}
