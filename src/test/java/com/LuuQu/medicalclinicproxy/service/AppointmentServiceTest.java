package com.LuuQu.medicalclinicproxy.service;

import com.LuuQu.medicalclinicproxy.client.AppointmentClient;
import com.LuuQu.medicalclinicproxy.mapper.AppointmentMapper;
import com.LuuQu.medicalclinicproxy.model.*;
import com.LuuQu.medicalclinicproxy.testHelper.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;

public class AppointmentServiceTest {
    private AppointmentService appointmentService;
    private AppointmentClient appointmentClient;
    private AppointmentMapper appointmentMapper;

    @BeforeEach
    void setUp() {
        appointmentClient = Mockito.mock(AppointmentClient.class);
        appointmentMapper = Mockito.mock(AppointmentMapper.class);
        appointmentService = new AppointmentService(appointmentClient, appointmentMapper);
    }

    @Test
    void getPatientAppointments_DataCorrect_SimpleDtoListReturned() {
        Long patientId = 1L;
        int listAmount = 2;
        List<AppointmentDto> appointmentDtoList = TestData.AppointmentDtoFactory.getList(listAmount);
        for (AppointmentDto appointmentDto : appointmentDtoList) {
            appointmentDto.setPatient(new PatientDto());
            appointmentDto.getPatient().setId(patientId);
        }
        List<AppointmentSimpleDto> appointmentSimpleDtoList = TestData.AppointmentSimpleDtoFactory.getList(listAmount);
        for (AppointmentSimpleDto appointmentDto : appointmentSimpleDtoList) {
            appointmentDto.setPatientId(patientId);
        }
        when(appointmentClient.getPatientAppointments(patientId)).thenReturn(appointmentDtoList);
        for (int i = 0; i < appointmentDtoList.size(); i++) {
            when(appointmentMapper.toSimpleDto(appointmentDtoList.get(i))).thenReturn(appointmentSimpleDtoList.get(i));
        }

        List<AppointmentSimpleDto> result = appointmentService.getPatientAppointments(patientId);

        Assertions.assertEquals(appointmentSimpleDtoList, result);
    }

    @Test
    void getDoctorAppointments_DataCorrect_SimpleDtoListReturned() {
        Long doctorId = 1L;
        int listAmount = 2;
        List<AppointmentDto> appointmentDtoList = TestData.AppointmentDtoFactory.getList(listAmount);
        for (AppointmentDto appointmentDto : appointmentDtoList) {
            appointmentDto.setDoctor(new DoctorDto());
            appointmentDto.getDoctor().setId(doctorId);
        }
        List<AppointmentSimpleDto> appointmentSimpleDtoList = TestData.AppointmentSimpleDtoFactory.getList(listAmount);
        for (AppointmentSimpleDto appointmentDto : appointmentSimpleDtoList) {
            appointmentDto.setDoctorId(doctorId);
        }
        when(appointmentClient.getDoctorAppointments(doctorId)).thenReturn(appointmentDtoList);
        for (int i = 0; i < appointmentDtoList.size(); i++) {
            when(appointmentMapper.toSimpleDto(appointmentDtoList.get(i))).thenReturn(appointmentSimpleDtoList.get(i));
        }

        List<AppointmentSimpleDto> result = appointmentService.getDoctorAppointments(doctorId);

        Assertions.assertEquals(appointmentSimpleDtoList, result);
    }

    @Test
    void getDoctorAvailableHours_DataCorrect_DateDtoListReturned() {
        int listAmount = 2;
        Long doctorId = 1L;
        String specialization = "specialization";
        LocalDate date = LocalDate.now();
        List<AppointmentDto> appointmentDtoList = TestData.AppointmentDtoFactory.getList(listAmount);
        List<AppointmentDateDto> appointmentSimpleDtoList = TestData.AppointmentDateDtoFactory.getList(listAmount);
        when(appointmentClient.getAvailableAppointments(date, specialization, doctorId)).thenReturn(appointmentDtoList);
        for (int i = 0; i < appointmentDtoList.size(); i++) {
            when(appointmentMapper.toDateDto(appointmentDtoList.get(i))).thenReturn(appointmentSimpleDtoList.get(i));
        }

        List<AppointmentDateDto> result = appointmentService.getDoctorAvailableHours(date, specialization, doctorId);

        Assertions.assertEquals(appointmentSimpleDtoList, result);
    }

    @Test
    void addPatientToAppointment_DataCorrect_SimpleDtoReturned() {
        Long patientId = 1L;
        Long appointmentId = 1L;
        AppointmentDto appointmentDto = TestData.AppointmentDtoFactory.get(appointmentId);
        appointmentDto.setPatient(new PatientDto());
        appointmentDto.getPatient().setId(patientId);
        when(appointmentClient.addPatientToAppointment(patientId, appointmentId)).thenReturn(appointmentDto);
        AppointmentSimpleDto expectedResult = TestData.AppointmentSimpleDtoFactory.get(appointmentId);
        expectedResult.setPatientId(patientId);
        when(appointmentMapper.toSimpleDto(appointmentDto)).thenReturn(expectedResult);

        AppointmentSimpleDto result = appointmentService.addPatientToAppointment(patientId, appointmentId);

        Assertions.assertEquals(expectedResult, result);
    }
}
