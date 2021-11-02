package com.example.Picnic.model.rest.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

@Entity(name = "Category")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Category {

    @JsonInclude()
    @Transient
    private String type;

    @Id
    @Column(name = "Id")
    private String id;

    @Column(name = "Name")
    private String name;

    @Transient
    @JsonInclude
    private List<Category> items;

    @Column(name = "IsIncludedInCategoryTree")
    private boolean is_included_in_category_tree;

    @Column(name = "IsHidden")
    private boolean hidden;

    @Column(name = "ImageId")
    private String image_id;

    @Column(name = "ParentCategoryId")
    private String parentCategoryId;


    public Category() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getItems() {
        return items;
    }

    public void setItems(List<Category> items) {
        this.items = items;
    }

    public boolean isIs_included_in_category_tree() {
        return is_included_in_category_tree;
    }

    public void setIs_included_in_category_tree(boolean is_included_in_category_tree) {
        this.is_included_in_category_tree = is_included_in_category_tree;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getParentCategoryId() {
        return parentCategoryId;
    }


    public void setParentCategoryId(String parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

}
