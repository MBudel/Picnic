package com.example.Picnic.repository;

import com.example.Picnic.model.entities.WeeklyProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface WeeklyProductRepository extends JpaRepository<WeeklyProduct, Long> {
    @Query("SELECT W FROM WeeklyProduct W WHERE W.picnicUser.username = :username")
    List<WeeklyProduct> findbyUsername(@Param("username") String username);

    @Query("SELECT W FROM WeeklyProduct W WHERE W.productId = :productId AND W.picnicUser.username = :username")
    Optional<WeeklyProduct> findByProductIdAndUsername(@Param("productId")String productId, @Param("username") String username);
}
