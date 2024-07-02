package com.LuuQu.medicalclinicproxy.exception;

public class ServerErrorException extends MedicalClinicProxyException {
    public ServerErrorException(String message) {
        super(message);
    }

    public ServerErrorException() {
        this("Unhandled server error");
    }
}
