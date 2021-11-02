package com.example.Picnic.repository;

import com.example.Picnic.model.rest.external.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, String> {
    @Query("SELECT C FROM Category C WHERE C.is_included_in_category_tree = 1 AND C.parentCategoryId is null")
    List<Category> findAllMainCategories();

    List<Category> findByparentCategoryId(String parentCategoryId);

}
