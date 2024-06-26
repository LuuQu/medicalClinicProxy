package com.LuuQu.medicalclinicproxy.model;

import lombok.Data;

@Data
public class DoctorDto {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String specialization;
}
