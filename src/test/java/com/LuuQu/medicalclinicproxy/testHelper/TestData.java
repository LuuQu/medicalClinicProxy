package com.LuuQu.medicalclinicproxy.testHelper;

import com.LuuQu.medicalclinicproxy.model.AppointmentDateDto;
import com.LuuQu.medicalclinicproxy.model.AppointmentDto;
import com.LuuQu.medicalclinicproxy.model.AppointmentSimpleDto;
import com.LuuQu.medicalclinicproxy.model.SpecializationRequestDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestData {
    public static class AppointmentDtoFactory {
        public static List<AppointmentDto> getList(int amount) {
            List<AppointmentDto> list = new ArrayList<>();
            for (int i = 0; i < amount; i++) {
                list.add(get((long) i));
            }
            return list;
        }
        public static AppointmentDto get(Long id) {
            AppointmentDto appointmentDto = get();
            appointmentDto.setId(id);
            return appointmentDto;
        }
        public static AppointmentDto get() {
            AppointmentDto appointmentDto = new AppointmentDto();
            appointmentDto.setStartDate(LocalDateTime.now().plusDays(1).withMinute(0).withSecond(0).withNano(0));
            appointmentDto.setEndDate(LocalDateTime.now().plusDays(1).plusHours(1).withMinute(0).withSecond(0).withNano(0));
            return appointmentDto;
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

    public static class SpecializationRequestDtoFactory {
        public static SpecializationRequestDto get(Long id) {
            SpecializationRequestDto specializationRequestDto = get();
            specializationRequestDto.setDoctorId(id);
            return specializationRequestDto;
        }
        public static SpecializationRequestDto get() {
            SpecializationRequestDto specializationRequestDto = new SpecializationRequestDto();
            specializationRequestDto.setSpecialization("specialization");
            specializationRequestDto.setDate(LocalDate.now());
            return specializationRequestDto;
        }
    }
}
