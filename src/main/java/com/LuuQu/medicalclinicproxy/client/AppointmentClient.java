package com.LuuQu.medicalclinicproxy.client;

import com.LuuQu.medicalclinicproxy.configure.FeignConfig;
import com.LuuQu.medicalclinicproxy.model.AppointmentDto;
import com.LuuQu.medicalclinicproxy.model.SpecializationRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "appointmentClient", url = "http://localhost:8080/appointments", configuration = FeignConfig.class)
public interface AppointmentClient {
    @GetMapping("/patient/{patientId}")
    List<AppointmentDto> getPatientAppointments(@PathVariable Long patientId);

    @GetMapping("/doctor/{doctorId}")
    List<AppointmentDto> getDoctorAppointments(@PathVariable Long doctorId);

    @PutMapping("/{appointmentId}/patients/{patientId}")
    AppointmentDto addPatientToAppointment(@PathVariable Long patientId, @PathVariable Long appointmentId);

    @GetMapping("/specialization/day")
    List<AppointmentDto> getAvailableAppointments(@RequestBody SpecializationRequestDto data);
}
