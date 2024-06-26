package com.LuuQu.medicalclinicproxy.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentDateDto {
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
