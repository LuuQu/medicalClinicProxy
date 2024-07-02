package com.LuuQu.medicalclinicproxy.testHelper;

import com.LuuQu.medicalclinicproxy.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public final class TestData {
    private TestData() {
    }

    public static class AppointmentDtoFactory {
        public static List<AppointmentDto> getList(int amount, Long patientId, Long doctorId) {
            List<AppointmentDto> list = new ArrayList<>();
            for (int i = 0; i < amount; i++) {
                list.add(get((long) i, patientId, doctorId));
            }
            return list;
        }

        public static AppointmentDto get(Long id, Long patientId, Long doctorId) {
            PatientDto patientDto = new PatientDto();
            patientDto.setId(patientId);
            DoctorDto doctorDto = new DoctorDto();
            doctorDto.setId(doctorId);
            return AppointmentDto.builder()
                    .id(id)
                    .startDate(LocalDateTime.now().plusDays(1).withMinute(0).withSecond(0).withNano(0))
                    .endDate(LocalDateTime.now().plusDays(1).plusHours(1).withMinute(0).withSecond(0).withNano(0))
                    .patient(patientDto)
                    .doctor(doctorDto)
                    .build();
        }
    }

    public static class AppointmentSimpleDtoFactory {

        public static List<AppointmentSimpleDto> getList(int amount) {
            List<AppointmentSimpleDto> list = new ArrayList<>();
            for (int i = 0; i < amount; i++) {
                list.add(get((long) i));
            }
            return list;
        }

        public static AppointmentSimpleDto get(Long id) {
            AppointmentSimpleDto appointmentDto = get();
            appointmentDto.setId(id);
            return appointmentDto;
        }

        public static AppointmentSimpleDto get() {
            AppointmentSimpleDto appointmentDto = new AppointmentSimpleDto();
            appointmentDto.setStartDate(LocalDateTime.now().plusDays(1).withMinute(0).withSecond(0).withNano(0));
            appointmentDto.setEndDate(LocalDateTime.now().plusDays(1).plusHours(1).withMinute(0).withSecond(0).withNano(0));
            return appointmentDto;
        }
    }

    public static class AppointmentDateDtoFactory {

        public static List<AppointmentDateDto> getList(int amount) {
            List<AppointmentDateDto> list = new ArrayList<>();
            for (int i = 0; i < amount; i++) {
                list.add(get((long) i));
            }
            return list;
        }

        public static AppointmentDateDto get(Long id) {
            AppointmentDateDto appointmentDto = get();
            appointmentDto.setId(id);
            return appointmentDto;
        }

        public static AppointmentDateDto get() {
            AppointmentDateDto appointmentDto = new AppointmentDateDto();
            appointmentDto.setStartDate(LocalDateTime.now().plusDays(1).withMinute(0).withSecond(0).withNano(0));
            appointmentDto.setEndDate(LocalDateTime.now().plusDays(1).plusHours(1).withMinute(0).withSecond(0).withNano(0));
            return appointmentDto;
        }
    }
}
