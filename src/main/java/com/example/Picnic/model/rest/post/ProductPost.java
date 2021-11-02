package com.example.Picnic.model.rest.post;


public class ProductPost {
    private String product_id;
    private int count;

    public ProductPost() {
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String productId) {
        this.product_id = productId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return  "{" +
                "\"product_id\":" + "\"" + product_id + "\"" + "," +
                "\"count\":" + count +
                "}";
    }
}

