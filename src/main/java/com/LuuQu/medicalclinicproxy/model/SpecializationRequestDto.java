package com.LuuQu.medicalclinicproxy.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SpecializationRequestDto {
    private LocalDate date;
    private Long doctorId;
    private String specialization;
}
