package com.example.Picnic.repository.external;

import com.example.Picnic.model.enums.RestMethod;
import com.example.Picnic.model.rest.external.UserDetails;
import com.example.Picnic.model.rest.post.LoginPost;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Objects;

@Repository
public class RepositoryExternal_User extends RepositoryExternal{

    public String loginUser(LoginPost loginPost) {
        RestMethod restMethod = RestMethod.loginUser;
        HttpEntity<String> entity = new HttpEntity<>(loginPost.toString(), getHeader());
        ResponseEntity<String> result = getRestTemplate().exchange(
                                        restMethod.getUrl(),
                                        restMethod.getHttpMethod(),
                                        entity,
                                        new ParameterizedTypeReference<>() {}
                                        );
        validateResponse(result.getStatusCode().value());
        return Objects.requireNonNull(result.getHeaders().get("x-picnic-auth")).get(0);
    }

    public UserDetails validateToken(String token) {
        RestMethod restMethod = RestMethod.getUser;
        HttpEntity<String> entity = new HttpEntity<>(null, getHeader(token));
        ResponseEntity<UserDetails> responseEntity =    getRestTemplate().exchange(
                                                        restMethod.getUrl(),
                                                        restMethod.getHttpMethod(),
                                                        entity,
                                                        new ParameterizedTypeReference<>() {}
                                                        );
        validateResponse(responseEntity.getStatusCode().value());
        return responseEntity.getBody();
    }

}
