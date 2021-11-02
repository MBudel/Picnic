package com.example.Picnic.resource;

import com.example.Picnic.model.entities.WeeklyProduct;
import com.example.Picnic.model.rest.post.ProductPost;
import com.example.Picnic.service.WeeklyProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weekly-product")
public class WeeklyProductResource {
    private final WeeklyProductService weeklyProductService;


    public WeeklyProductResource(WeeklyProductService weeklyProductService) {
        this.weeklyProductService = weeklyProductService;
    }

    @GetMapping()
    public ResponseEntity<List<WeeklyProduct>> getWeeklyProductsUser(@RequestHeader String token){
        List<WeeklyProduct> weeklyProducts = weeklyProductService.findAllWeeklyProductsForUser(token);
        return new ResponseEntity<>(weeklyProducts, HttpStatus.OK);
    }

    @PostMapping("/add_product")
    public ResponseEntity<List<WeeklyProduct>> addWeeklyProduct(@RequestHeader String token, @RequestBody ProductPost productPost){
        List<WeeklyProduct> weeklyProducts = weeklyProductService.addWeeklyProduct(productPost, token);
        return new ResponseEntity<>(weeklyProducts, HttpStatus.CREATED);
    }


    @PostMapping("/remove_product")
    public ResponseEntity<List<WeeklyProduct>> removeWeeklyProduct(@RequestHeader String token, @RequestBody ProductPost productPost){
        List<WeeklyProduct> weeklyProducts = weeklyProductService.removeWeeklyProduct(productPost, token);
        return new ResponseEntity<>(weeklyProducts, HttpStatus.OK);
    }

}