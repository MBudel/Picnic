package com.example.Picnic.resource;


import com.example.Picnic.model.rest.external.Category;
import com.example.Picnic.model.rest.external.Product;
import com.example.Picnic.service.ProductService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductResource {

    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@RequestHeader String token, @PathVariable("id") String id){
        Product product = productService.getProduct(id, token);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/search/category/{id}")
    public ResponseEntity<List<Product>> getProductsByCategoryId(@RequestHeader String token, @PathVariable("id") String id){
        List<Product> products = productService.getProductsForCategory(id, token);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/search/product/{searchterm}")
    public ResponseEntity<List<Product>> getProductsBySearchTerm(@RequestHeader String token, @PathVariable("searchterm") String searchTerm){
        List<Product> products = productService.getProductsBySearchTerm(searchTerm, token);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/category/refresh")
    public ResponseEntity<?> refreshCategories(@RequestHeader String token){
       productService.storeCategoriesToDatabse(token);
       return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getMainCategories(@RequestHeader String token){
        List<Category> categories = productService.getAllMainCategories(token);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryid}/subcategories")
    public ResponseEntity<List<Category>> getSubcategoriesByCategoryId(@RequestHeader String token, @PathVariable("categoryid") String categoryid){
        List<Category> categories = productService.getSubCategories(categoryid, token);
        return new ResponseEntity<>(categories, HttpStatus.OK);

    }
}



