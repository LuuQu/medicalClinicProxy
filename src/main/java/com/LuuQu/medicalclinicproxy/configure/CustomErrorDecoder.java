package com.LuuQu.medicalclinicproxy.configure;

import com.LuuQu.medicalclinicproxy.exception.BadRequestException;
import com.LuuQu.medicalclinicproxy.exception.NotFoundException;
import feign.FeignException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();
    @Override
    public Exception decode(String methodKey, Response response) {
        FeignException exception = feign.FeignException.errorStatus(methodKey, response);
        switch(response.status()) {
            case 400:
                return new BadRequestException();
            case 404:
                return new NotFoundException();
            case 500:
            case 503:
                return new RetryableException(
                        response.status(),
                        exception.getMessage(),
                        response.request().httpMethod(),
                        exception,
                        50L, // The retry interval
                        response.request());
            default:
                return defaultErrorDecoder.decode(methodKey,response);
        }
    }
}
