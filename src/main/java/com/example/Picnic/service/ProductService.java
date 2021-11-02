package com.example.Picnic.service;

import com.example.Picnic.exception.CategoryNotFoundException;
import com.example.Picnic.model.enums.ItemType;
import com.example.Picnic.model.rest.external.Category;
import com.example.Picnic.model.rest.external.Product;
import com.example.Picnic.repository.CategoryRepository;
import com.example.Picnic.repository.external.RepositoryExternal_Product;
import com.example.Picnic.repository.external.RepositoryExternal_User;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductService {

    private final RepositoryExternal_Product repositoryExternalProduct;
    private final CategoryRepository categoryRepository;
    private final RepositoryExternal_User repositoryExternalUser;

    public ProductService(RepositoryExternal_Product repositoryExternalProduct, CategoryRepository categoryRepository, RepositoryExternal_User repositoryExternalUser) {
        this.repositoryExternalProduct = repositoryExternalProduct;
        this.categoryRepository = categoryRepository;
        this.repositoryExternalUser = repositoryExternalUser;
    }

    public void storeCategoriesToDatabse(String token) {
        List<Category> Categories = repositoryExternalProduct.getCategories(token);
        if (!Categories.isEmpty()) {
            categoryRepository.deleteAll();
            for (Category category : Categories) {
                category = categoryRepository.save(category);
                for (Category subcategory : repositoryExternalProduct.getSubCategories(category.getId(), token)) {
                    if (subcategory.getType().equals(ItemType.CATEGORY.toString())) {
                        subcategory.setParentCategoryId(category.getId());
                        Category savedSubcategory = categoryRepository.save(subcategory);
                        for(Category subsubcategory: repositoryExternalProduct.getSubCategories(savedSubcategory.getId(), token)){
                            if(subsubcategory.getType().equals(ItemType.CATEGORY.toString())){
                                subsubcategory.setParentCategoryId(subcategory.getId());
                                categoryRepository.save(subsubcategory);
                            }

                        }

                    }
                }

            }
        }
    }

    public List<Category> getAllMainCategories(String token){
        repositoryExternalUser.validateToken(token);
        return categoryRepository.findAllMainCategories();
    }

    public List<Category> getSubCategories(String parentCategoryId, String token){
        repositoryExternalUser.validateToken(token);
        return categoryRepository.findByparentCategoryId(parentCategoryId);
    }

    public List<Product> getProductsForCategory(String id, String token){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id" + id + "not found"));
        if(category.getId().equals("purchases")) {
            return repositoryExternalProduct.getProductsForCategory(category.getId(), "", true, token);
        }
        List<Product> productList = new ArrayList<>();
        boolean isNested = category.getId().equals("promotions");
        for(Category subcategory: categoryRepository.findByparentCategoryId(category.getId())){
            for(Product product: repositoryExternalProduct.getProductsForCategory(category.getId(), subcategory.getId(),isNested, token)){
                if(product.getType().equals(ItemType.SINGLE_ARTICLE.toString())){
                    productList.add(product);
                }
            }
        }
        return productList;

    }

    public List<Product> getProductsBySearchTerm(String searchTerm, String token){
        return repositoryExternalProduct.getProductsBySearchTerm(searchTerm, token);
    }

    public Product getProduct(String productId, String token){
        return repositoryExternalProduct.getProduct(productId, token);
    }

}
