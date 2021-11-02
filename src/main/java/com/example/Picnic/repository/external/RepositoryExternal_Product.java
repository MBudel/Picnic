package com.example.Picnic.repository.external;

import com.example.Picnic.model.enums.RestMethod;
import com.example.Picnic.model.rest.external.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class RepositoryExternal_Product extends RepositoryExternal{

    public List<Category> getCategories(String token) {
        RestMethod restMethod = RestMethod.getCategories;
        HttpEntity<String> entity = new HttpEntity<>(null, getHeader(token));
        ResponseEntity<MyStore> responseEntity =    getRestTemplate().exchange(
                                                    restMethod.getUrl(),
                                                    restMethod.getHttpMethod(),
                                                    entity,
                                                    new ParameterizedTypeReference<>() {}
                                                    );
        validateResponse(responseEntity.getStatusCode().value());
        return Objects.requireNonNull(responseEntity.getBody()).getCatalog();
    }

    public List<Category> getSubCategories(String categoryId, String token) {
        RestMethod restMethod = RestMethod.getList;
        List<String> argumentList = Collections.singletonList(categoryId);
        HttpEntity<String> entity = new HttpEntity<>(null, getHeader(token));
        //A status 404 is thrown when list with id is not found. These don't have a sublist so just returning empty list is fine
        try {
            ResponseEntity<List<Category>> responseEntity = getRestTemplate().exchange(
                                                            restMethod.getUrl(argumentList),
                                                            restMethod.getHttpMethod(),
                                                            entity,
                                                            new ParameterizedTypeReference<>(){}
                                                            );
        validateResponse(responseEntity.getStatusCode().value());
        return responseEntity.getBody();
        }
        catch (Exception e){
            return new ArrayList<>();
        }
    }

    public List<Product> getProductsForCategory(String categoryId, String subCategoryId, boolean isNested, String token){
        RestMethod restMethod = !subCategoryId.isEmpty()? RestMethod.getSubList: RestMethod.getList;
        List<String> argumentList = new ArrayList<>();
        argumentList.add(categoryId);
        HttpEntity<String> entity = new HttpEntity<>(null, getHeader(token));
        if(!subCategoryId.isEmpty()){
            argumentList.add(subCategoryId);
        }
        if(isNested){
            ResponseEntity<List<ProductSearch>> responseEntity =    getRestTemplate().exchange(
                                                                    restMethod.getUrl(argumentList),
                                                                    restMethod.getHttpMethod(),
                                                                    entity,
                                                                    new ParameterizedTypeReference<>(){}
                                                                    );
            validateResponse(responseEntity.getStatusCode().value());
            if(Objects.requireNonNull(responseEntity.getBody()).size() > 0){
                return Objects.requireNonNull(responseEntity.getBody()).get(0).getItems();
            }
            else{
                return new ArrayList<>();
            }

        }
        else{
            ResponseEntity<List<Product>> responseEntity =  getRestTemplate().exchange(
                                                            restMethod.getUrl(argumentList),
                                                            restMethod.getHttpMethod(),
                                                            entity,
                                                            new ParameterizedTypeReference<>() {}
                                                            );
            validateResponse(responseEntity.getStatusCode().value());
            return responseEntity.getBody();
        }
    }

    public List<Product> getProductsBySearchTerm(String searchTerm, String token){
        RestMethod restMethod = RestMethod.getProductsBySearchTerm;
        List<String> argumentList = Collections.singletonList(searchTerm);
        HttpEntity<String> entity = new HttpEntity<>(null, getHeader(token));
        ResponseEntity<List<ProductSearch>> responseEntity =    getRestTemplate().exchange(
                                                                restMethod.getUrl(argumentList),
                                                                restMethod.getHttpMethod(),
                                                                entity,
                                                                new ParameterizedTypeReference<>() {}
                                                                );
        validateResponse(responseEntity.getStatusCode().value());
        return Objects.requireNonNull(responseEntity.getBody()).get(0).getItems();
    }

    public Product getProduct(String productId, String token){
        RestMethod restMethod = RestMethod.getProduct;
        List<String> argumentList = Collections.singletonList(productId);
        HttpEntity<String> entity = new HttpEntity<>(null, getHeader(token));
        ResponseEntity<ProductDetails> responseEntity = getRestTemplate().exchange(
                                                        restMethod.getUrl(argumentList),
                                                        restMethod.getHttpMethod(),
                                                        entity,
                                                        new ParameterizedTypeReference<>() {}
                                                        );
        validateResponse(responseEntity.getStatusCode().value());
        return Objects.requireNonNull(responseEntity.getBody()).getProduct_details();
    }

}
