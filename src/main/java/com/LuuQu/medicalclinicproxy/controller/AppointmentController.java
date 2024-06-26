package com.LuuQu.medicalclinicproxy.controller;

import com.LuuQu.medicalclinicproxy.model.AppointmentDateDto;
import com.LuuQu.medicalclinicproxy.model.AppointmentSimpleDto;
import com.LuuQu.medicalclinicproxy.model.SpecializationRequestDto;
import com.LuuQu.medicalclinicproxy.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/appointments", produces = "application/json")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @Operation(summary = "Get all patient appointments by patient ID.")
    @GetMapping("/patient/{patientId}")
    public List<AppointmentSimpleDto> getPatientAppointments(@PathVariable Long patientId) {
        return appointmentService.getPatientAppointments(patientId);
    }

    @Operation(summary = "Get all doctor appointments by doctor ID. ")
    @GetMapping("/doctor/{doctorId}")
    public List<AppointmentSimpleDto> getDoctorAppointments(@PathVariable Long doctorId) {
        return appointmentService.getDoctorAppointments(doctorId);
    }

    @Operation(summary = "Get all doctor appointments by specialization and day." +
            " Information about specialization, day and doctorId are stored in RequestBody")
    @GetMapping("/specialization/day") /// <- ogarnąć tutaj to coś
    public List<AppointmentDateDto> getDoctorAvailableHours(@RequestBody SpecializationRequestDto data) {
        return appointmentService.getDoctorAvailableHours(data);
    }

    @Operation(summary = "Connect appointment with patient by patient id and appointment id.")
    @PutMapping("/{appointmentId}/patient/{patientId}")
    public AppointmentSimpleDto addPatientToAppointment(@PathVariable Long patientId, @PathVariable Long appointmentId) {
        return appointmentService.addPatientToAppointment(patientId, appointmentId);
    }
}
