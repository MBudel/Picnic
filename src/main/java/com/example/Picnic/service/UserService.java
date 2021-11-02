package com.example.Picnic.service;

import com.example.Picnic.model.entities.PicnicUser;
import com.example.Picnic.model.rest.external.UserDetails;
import com.example.Picnic.model.rest.post.LoginPost;
import com.example.Picnic.repository.PicnicUserRepository;
import com.example.Picnic.repository.external.RepositoryExternal_User;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;

@Service
@Transactional
public class UserService {

    private final RepositoryExternal_User repositoryExternal_user;
    private final PicnicUserRepository picnicUserRepository;

    public UserService(RepositoryExternal_User repositoryExternal_user, PicnicUserRepository picnicUserRepository) {
        this.repositoryExternal_user = repositoryExternal_user;
        this.picnicUserRepository = picnicUserRepository;
    }

    public String loginUser(LoginPost loginPost) {
        return repositoryExternal_user.loginUser(loginPost);
    }

    public UserDetails validateToken(String token){
        return repositoryExternal_user.validateToken(token);
    }

    public PicnicUser findUserByName(String username){
        return picnicUserRepository.findUserByusername(username)
                .orElse(new PicnicUser());
    }

    public PicnicUser addUser(PicnicUser user){
        return picnicUserRepository.save(user);
    }
}
