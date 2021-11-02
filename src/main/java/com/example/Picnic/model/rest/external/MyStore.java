package com.example.Picnic.model.rest.external;

import java.util.List;

public class MyStore {
    private String type;
    private List<Category> catalog;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Category> getCatalog() {
        return catalog;
    }

    public void setCatalog(List<Category> catalog) {
        this.catalog = catalog;
    }




}
