package com.LuuQu.medicalclinicproxy.service;

import com.LuuQu.medicalclinicproxy.client.AppointmentClient;
import com.LuuQu.medicalclinicproxy.mapper.AppointmentMapper;
import com.LuuQu.medicalclinicproxy.model.AppointmentDateDto;
import com.LuuQu.medicalclinicproxy.model.AppointmentSimpleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentClient appointmentClient;
    private final AppointmentMapper appointmentMapper;

    public List<AppointmentSimpleDto> getPatientAppointments(Long patientId) {
        return appointmentClient.getPatientAppointments(patientId).stream()
                .map(appointmentMapper::toSimpleDto)
                .toList();
    }

    public List<AppointmentSimpleDto> getDoctorAppointments(Long doctorId) {
        return appointmentClient.getDoctorAppointments(doctorId).stream()
                .map(appointmentMapper::toSimpleDto)
                .toList();
    }

    public List<AppointmentDateDto> getDoctorAvailableHours(LocalDate date, String specialization, Long doctorId) {
        return appointmentClient.getAvailableAppointments(date, specialization, doctorId).stream()
                .map(appointmentMapper::toDateDto)
                .toList();
    }

    public AppointmentSimpleDto addPatientToAppointment(Long patientId, Long appointmentId) {
        return appointmentMapper.toSimpleDto(appointmentClient.addPatientToAppointment(patientId, appointmentId));
    }
}
