package com.example.Picnic.model.rest.external;

import com.example.Picnic.model.enums.ItemType;
import java.util.ArrayList;
import java.util.List;

public class ProductSearch {
    private String type;
    private String id;
    private String name;
    private List<Product> items;

    public ProductSearch() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getItems() {
        List<Product> products = new ArrayList<>();
        for(Product product: items){
            if(product.getType().equals(ItemType.SINGLE_ARTICLE.toString()))
                products.add(product);
        }
        return products;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }

}
