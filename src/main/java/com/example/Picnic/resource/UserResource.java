package com.example.Picnic.resource;

import com.example.Picnic.model.entities.PicnicUser;
import com.example.Picnic.model.rest.external.UserDetails;
import com.example.Picnic.model.rest.post.LoginPost;
import com.example.Picnic.service.UserService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.NoSuchAlgorithmException;


@RestController
@RequestMapping("/user")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<PicnicUser>loginUser(@RequestParam String username, @RequestParam String password) {
        HttpHeaders responseHeaders = new HttpHeaders();
        LoginPost loginPost = new LoginPost(username, password);
        responseHeaders.set("x-picnic-auth", userService.loginUser(loginPost));

        PicnicUser picnicUser = userService.findUserByName(username);
        if(picnicUser.getPicnicUserId() != null){
            return new ResponseEntity<>(picnicUser, responseHeaders, HttpStatus.OK);
        }
        else{
            picnicUser.setUsername(username);
            return new ResponseEntity<>(userService.addUser(picnicUser), responseHeaders, HttpStatus.CREATED);
        }
    }

}
