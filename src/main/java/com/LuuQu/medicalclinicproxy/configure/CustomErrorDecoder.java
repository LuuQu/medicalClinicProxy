package com.LuuQu.medicalclinicproxy.configure;

import com.LuuQu.medicalclinicproxy.exception.BadRequestException;
import com.LuuQu.medicalclinicproxy.exception.NotFoundException;
import com.LuuQu.medicalclinicproxy.exception.ServerErrorException;
import feign.FeignException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        FeignException exception = feign.FeignException.errorStatus(methodKey, response);
        return switch (response.status()) {
            case 400 -> new BadRequestException();
            case 404 -> new NotFoundException();
            case 500 -> new ServerErrorException();
            case 503 -> new RetryableException(
                    response.status(),
                    exception.getMessage(),
                    response.request().httpMethod(),
                    exception,
                    50L, // The retry interval
                    response.request());
            default -> defaultErrorDecoder.decode(methodKey, response);
        };
    }
}
