package com.LuuQu.medicalclinicproxy.exception;

public class BadRequestException extends MedicalClinicProxyExcepotion {
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException() {
        this("Bad Request");
    }
}
