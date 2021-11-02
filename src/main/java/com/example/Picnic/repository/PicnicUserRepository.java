package com.example.Picnic.repository;

import com.example.Picnic.model.entities.PicnicUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PicnicUserRepository extends JpaRepository<PicnicUser, Long> {
    Optional<PicnicUser> findUserByusername(String username);
}
