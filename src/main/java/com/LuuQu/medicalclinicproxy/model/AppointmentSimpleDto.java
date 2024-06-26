package com.LuuQu.medicalclinicproxy.model;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class AppointmentSimpleDto {
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long patientId;
    private Long doctorId;
}
