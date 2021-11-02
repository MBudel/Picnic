package com.example.Picnic.repository.external;

import com.example.Picnic.model.enums.RestMethod;
import com.example.Picnic.model.rest.external.Cart;
import com.example.Picnic.model.rest.post.ProductPost;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class RepositoryExternal_Cart extends RepositoryExternal{

    public Cart getCart(String picnicAuth){
        RestMethod restMethod = RestMethod.getCart;
        HttpEntity<String> entity = new HttpEntity<>(null,getHeader(picnicAuth));
        ResponseEntity<Cart> responseEntity =   getRestTemplate().exchange(
                                                restMethod.getUrl(),
                                                restMethod.getHttpMethod(),
                                                entity,
                                                new ParameterizedTypeReference<>(){}
                                                );
        validateResponse(responseEntity.getStatusCode().value());
        return responseEntity.getBody();
    }

    public Cart clearCart(String picnicAuth){
        RestMethod restMethod = RestMethod.clearCart;
        HttpEntity<String> entity = new HttpEntity<>(null,getHeader(picnicAuth));
        ResponseEntity<Cart> responseEntity =   getRestTemplate().exchange(
                                                restMethod.getUrl(),
                                                restMethod.getHttpMethod(),
                                                entity,
                                                new ParameterizedTypeReference<>(){}
                                                );
        validateResponse(responseEntity.getStatusCode().value());
        return responseEntity.getBody();
    }

    public Cart addProductToCart(ProductPost productPost, String picnicAuth){
        RestMethod restMethod = RestMethod.addProductToCart;
        HttpEntity<String> entity = new HttpEntity<>(productPost.toString(), getHeader(picnicAuth));
        ResponseEntity<Cart> responseEntity =   getRestTemplate().exchange(
                                                restMethod.getUrl(),
                                                restMethod.getHttpMethod(),
                                                entity,
                                                new ParameterizedTypeReference<>(){}
                                                );
        validateResponse(responseEntity.getStatusCode().value());
        return responseEntity.getBody();
    }

    public Cart removeProductFromCart(ProductPost productPost, String picnicAuth){
        RestMethod restMethod = RestMethod.removeProductFromCart;
        HttpEntity<String> entity = new HttpEntity<>(productPost.toString(), getHeader(picnicAuth));
        ResponseEntity<Cart> responseEntity =   getRestTemplate().exchange(
                                                restMethod.getUrl(),
                                                restMethod.getHttpMethod(),
                                                entity,
                                                new ParameterizedTypeReference<>(){}
                                                );
        validateResponse(responseEntity.getStatusCode().value());
        return responseEntity.getBody();
    }


}
