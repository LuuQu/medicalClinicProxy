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
        String responseMessage = extractMessage(exception.getMessage());
        return switch (response.status()) {
            case 400 -> new BadRequestException(responseMessage);
            case 404 -> new NotFoundException(responseMessage);
            case 500 -> new ServerErrorException(responseMessage);
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
    public String extractMessage(String inputString) {
        String searchString = "\"message\":\"";
        int startIndex = inputString.indexOf(searchString);
        if (startIndex != -1) {
            startIndex += searchString.length();
            int endIndex = inputString.indexOf("\"", startIndex);
            if (endIndex != -1) {
                return inputString.substring(startIndex, endIndex);
            }
        }
        return "Message not found";
    }
}
