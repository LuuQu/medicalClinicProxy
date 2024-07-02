package com.LuuQu.medicalclinicproxy.client;

import com.LuuQu.medicalclinicproxy.configure.AppointmentFallbackFactory;
import com.LuuQu.medicalclinicproxy.configure.FeignConfig;
import com.LuuQu.medicalclinicproxy.model.AppointmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@FeignClient(name = "appointmentClient",
        configuration = FeignConfig.class, fallbackFactory = AppointmentFallbackFactory.class)
public interface AppointmentClient {
    @GetMapping("/patient/{patientId}")
    List<AppointmentDto> getPatientAppointments(@PathVariable Long patientId);

    @GetMapping("/doctor/{doctorId}")
    List<AppointmentDto> getDoctorAppointments(@PathVariable Long doctorId);

    @PutMapping("/{appointmentId}/patients/{patientId}")
    AppointmentDto addPatientToAppointment(@PathVariable Long patientId, @PathVariable Long appointmentId);

    @GetMapping("/doctor")
    List<AppointmentDto> getAvailableAppointments(@RequestParam LocalDate date, @RequestParam String specialization, @RequestParam Long doctorId);
}
