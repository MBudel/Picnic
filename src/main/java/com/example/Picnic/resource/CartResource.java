package com.example.Picnic.resource;


import com.example.Picnic.model.rest.post.ProductPost;
import com.example.Picnic.model.rest.external.Cart;
import com.example.Picnic.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")

public class CartResource {

    private final CartService cartService;

    public CartResource(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping()
    public ResponseEntity<Cart> getCart(@RequestHeader String token){
        Cart cart = cartService.getCart(token);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping(value = "/clear")
    public ResponseEntity<Cart> clearCart(@RequestHeader String token){
        Cart cart =cartService.clearCart(token);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping(value = "/add_product")
    public ResponseEntity<Cart> addProductToCart(@RequestHeader String token, @RequestBody ProductPost productPost){
        Cart updatedCart = cartService.addProductToCart(productPost, token);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }

    @PostMapping(value = "/remove_product")
    public ResponseEntity<Cart> removeProductFromCart(@RequestHeader String token, @RequestBody ProductPost productPost){
        Cart updatedCart = cartService.removeProductFromCart(productPost, token);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }

}
