package com.example.Picnic.exception;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        HttpStatus statusCode = response.getStatusCode();
        return statusCode.series() == HttpStatus.Series.SERVER_ERROR || statusCode.series() == HttpStatus.Series.CLIENT_ERROR;
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) {
    }
}
