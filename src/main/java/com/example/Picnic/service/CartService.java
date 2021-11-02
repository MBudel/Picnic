package com.example.Picnic.service;

import com.example.Picnic.model.rest.external.Cart;
import com.example.Picnic.model.rest.post.ProductPost;
import com.example.Picnic.repository.external.RepositoryExternal_Cart;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class CartService {

    private final RepositoryExternal_Cart repositoryExternalCart;

    public CartService(RepositoryExternal_Cart repositoryExternalCart) {
        this.repositoryExternalCart = repositoryExternalCart;
    }

    public Cart getCart(String token){
        return repositoryExternalCart.getCart(token);
    }

    public Cart clearCart(String token){
        return repositoryExternalCart.clearCart(token);
    }

    public Cart addProductToCart(ProductPost productPost, String token){
        return repositoryExternalCart.addProductToCart(productPost, token);
    }

    public Cart removeProductFromCart(ProductPost productPost, String token){
        return repositoryExternalCart.removeProductFromCart(productPost, token);
    }
}
