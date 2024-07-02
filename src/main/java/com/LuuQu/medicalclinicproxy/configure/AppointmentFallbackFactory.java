package com.LuuQu.medicalclinicproxy.configure;

import com.LuuQu.medicalclinicproxy.client.AppointmentClient;
import com.LuuQu.medicalclinicproxy.exception.BadRequestException;
import com.LuuQu.medicalclinicproxy.exception.NotFoundException;
import com.LuuQu.medicalclinicproxy.model.AppointmentDto;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class AppointmentFallbackFactory implements FallbackFactory<AppointmentClient> {
    public AppointmentClient create(Throwable cause) {
        return new AppointmentClient() {
            @Override
            public List<AppointmentDto> getPatientAppointments(Long patientId) {
                return new ArrayList<>();
            }

            @Override
            public List<AppointmentDto> getDoctorAppointments(Long doctorId) {
                return new ArrayList<>();
            }

            @Override
            public AppointmentDto addPatientToAppointment(Long patientId, Long appointmentId) {
                if (cause instanceof NotFoundException) {
                    throw (NotFoundException) cause;
                }
                if (cause instanceof BadRequestException) {
                    throw (BadRequestException) cause;
                }
                return null;
            }

            @Override
            public List<AppointmentDto> getAvailableAppointments(LocalDate date, String specialization, Long doctorId) {
                return new ArrayList<>();
            }
        };
    }
}
