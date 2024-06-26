package com.LuuQu.medicalclinicproxy.service;

import com.LuuQu.medicalclinicproxy.client.AppointmentClient;
import com.LuuQu.medicalclinicproxy.mapper.AppointmentMapper;
import com.LuuQu.medicalclinicproxy.model.AppointmentDateDto;
import com.LuuQu.medicalclinicproxy.model.AppointmentSimpleDto;
import com.LuuQu.medicalclinicproxy.model.SpecializationRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public List<AppointmentDateDto> getDoctorAvailableHours(SpecializationRequestDto data) {
        return appointmentClient.getAvailableAppointments(data).stream()
                .map(appointmentMapper::toDateDto)
                .toList();
    }

    public AppointmentSimpleDto addPatientToAppointment(Long patientId, Long appointmentId) {
        return appointmentMapper.toSimpleDto(appointmentClient.addPatientToAppointment(patientId,appointmentId));
    }
}
