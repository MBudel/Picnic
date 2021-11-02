package com.example.Picnic.repository.external;

import com.example.Picnic.exception.BadRequestException;
import com.example.Picnic.exception.RestTemplateResponseErrorHandler;
import com.example.Picnic.exception.UnauthorizedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class RepositoryExternal {
    private final RestTemplate restTemplate = new RestTemplate();

    public RepositoryExternal() {
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
    }

    /**
     * Returns the header where the x-picnic-auth is filled with the token parameter.
     * Mediatype is set to Application_Json
     *
     * @param  token  Token as needed by Picnic
     * @return  The header with x-picnic-auth filled
     */
    public HttpHeaders getHeader(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-picnic-auth", token);
        return headers;
    }

    /**
     * Returns the default header without token.
     * Mediatype is set to Application_Json
     *
     * @return  Default header
     */
    public HttpHeaders getHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    //ToDo: Extend with more errorcodes
    public void validateResponse(int responseCode){
        if(responseCode == 401){
            throw new UnauthorizedException("Invalid credentials");
        }
        else if(responseCode == 422){
            throw new UnauthorizedException("Invalid usertoken");
        }
        else if(responseCode != 200){
            throw new BadRequestException("Something went wrong, please try again later: " + responseCode);
        }
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
}
