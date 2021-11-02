package com.example.Picnic.service;

import com.example.Picnic.exception.UserNotFoundException;
import com.example.Picnic.model.entities.WeeklyProduct;
import com.example.Picnic.model.rest.external.UserDetails;
import com.example.Picnic.model.rest.post.ProductPost;
import com.example.Picnic.repository.PicnicUserRepository;
import com.example.Picnic.repository.WeeklyProductRepository;
import com.example.Picnic.repository.external.RepositoryExternal_User;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class WeeklyProductService {
    private final WeeklyProductRepository weeklyProductRepository;
    private final RepositoryExternal_User repositoryExternalUser;
    private final PicnicUserRepository picnicUserRepository;

    public WeeklyProductService(WeeklyProductRepository weeklyProductRepository, RepositoryExternal_User repositoryExternalUser, PicnicUserRepository picnicUserRepository) {
        this.weeklyProductRepository = weeklyProductRepository;
        this.repositoryExternalUser = repositoryExternalUser;
        this.picnicUserRepository = picnicUserRepository;
    }

    public List<WeeklyProduct> findAllWeeklyProductsForUser(String token){
        UserDetails userDetails = repositoryExternalUser.validateToken(token);
        return weeklyProductRepository.findbyUsername(userDetails.getContact_email());
    }

    public List<WeeklyProduct> addWeeklyProduct(ProductPost product, String token){
        UserDetails userDetails = repositoryExternalUser.validateToken(token);
        String username = userDetails.getContact_email();
        WeeklyProduct weeklyProduct = weeklyProductRepository.findByProductIdAndUsername(product.getProduct_id(), username)
                .orElse(new WeeklyProduct());
        weeklyProduct.setPicnicUser(picnicUserRepository.findUserByusername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username " + username + " not found")));
        weeklyProduct.setCount(weeklyProduct.getCount() + product.getCount());
        weeklyProduct.setProductId(product.getProduct_id());
        weeklyProductRepository.save(weeklyProduct);
        return weeklyProductRepository.findbyUsername(username);
    }

    public List<WeeklyProduct> removeWeeklyProduct(ProductPost product, String token){
        UserDetails userDetails = repositoryExternalUser.validateToken(token);
        String username = userDetails.getContact_email();
        WeeklyProduct weeklyProduct = weeklyProductRepository.findByProductIdAndUsername(product.getProduct_id(), username)
                .orElse(new WeeklyProduct());
        if(weeklyProduct.getId() != null){
            if(weeklyProduct.getCount() <= product.getCount()){
                weeklyProductRepository.deleteById(weeklyProduct.getId());
            }
            else{
                weeklyProduct.setCount(weeklyProduct.getCount() - product.getCount());
                weeklyProductRepository.save(weeklyProduct);
            }
        }
        return weeklyProductRepository.findbyUsername(username);
    }
}
