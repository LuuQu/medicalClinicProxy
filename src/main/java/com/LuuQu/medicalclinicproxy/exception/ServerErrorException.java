package com.LuuQu.medicalclinicproxy.exception;

public class ServerErrorException extends MedicalClinicProxyExcepotion {
    public ServerErrorException(String message) {
        super(message);
    }

    public ServerErrorException() {
        this("Unhandled server error");
    }
}
