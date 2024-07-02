package com.LuuQu.medicalclinicproxy.exception;

public class BadRequestException extends MedicalClinicProxyException {
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException() {
        this("Bad Request");
    }
}
