package com.LuuQu.medicalclinicproxy.model;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AppointmentDto(Long id, LocalDateTime startDate, LocalDateTime endDate, PatientDto patient, DoctorDto doctor) {}
