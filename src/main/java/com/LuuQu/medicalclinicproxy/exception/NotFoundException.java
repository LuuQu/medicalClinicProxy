package com.LuuQu.medicalclinicproxy.exception;

public class NotFoundException extends MedicalClinicProxyException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException() {
        this("Data not found");
    }
}
