package com.LuuQu.medicalclinicproxy.handler;

import com.LuuQu.medicalclinicproxy.exception.BadRequestException;
import com.LuuQu.medicalclinicproxy.exception.MedicalClinicProxyExcepotion;
import com.LuuQu.medicalclinicproxy.exception.NotFoundException;
import com.LuuQu.medicalclinicproxy.model.ErrorMessageDto;
import feign.RetryableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MedicalClinicProxyExcepotion.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ErrorMessageDto> medicalClinicExceptionErrorResponse(MedicalClinicProxyExcepotion exception) {
        return ResponseEntity.badRequest().body(new ErrorMessageDto(exception.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ErrorMessageDto> badRequestExceptionErrorResponse(BadRequestException exception) {
        return ResponseEntity.badRequest().body(new ErrorMessageDto(exception.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ErrorMessageDto> notFoundExceptionErrorResponse(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessageDto(exception.getMessage()));
    }

    @ExceptionHandler(RetryableException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ErrorMessageDto> retryAbleExceptionErrorResponse(RetryableException exception) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ErrorMessageDto("Connection refused"));
    }
}
