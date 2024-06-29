package com.LuuQu.medicalclinicproxy.controller;

import com.LuuQu.medicalclinicproxy.model.AppointmentDateDto;
import com.LuuQu.medicalclinicproxy.model.AppointmentSimpleDto;
import com.LuuQu.medicalclinicproxy.service.AppointmentService;
import com.LuuQu.medicalclinicproxy.testHelper.TestControllerHelper;
import com.LuuQu.medicalclinicproxy.testHelper.TestData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AppointmentControllerTest {
    @MockBean
    private AppointmentService appointmentService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getPatientAppointments_DataCorrect_SimpleDtoListReturned() throws Exception {
        Long patientId = 1L;
        List<AppointmentSimpleDto> appointmentSimpleDtoList = TestData.AppointmentSimpleDtoFactory.getList(2);
        for (AppointmentSimpleDto appointmentSimpleDto : appointmentSimpleDtoList) {
            appointmentSimpleDto.setPatientId(patientId);
        }
        when(appointmentService.getPatientAppointments(patientId)).thenReturn(appointmentSimpleDtoList);
        String expectedResult = TestControllerHelper.getObjectAsString(appointmentSimpleDtoList, objectMapper);

        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.get("/appointments/patient/{patientId}", patientId))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(expectedResult, result.getResponse().getContentAsString());
    }

    @Test
    void getDoctorAppointments_DataCorrect_SimpleDtoListReturned() throws Exception {
        Long doctorId = 1L;
        List<AppointmentSimpleDto> appointmentSimpleDtoList = TestData.AppointmentSimpleDtoFactory.getList(2);
        for (AppointmentSimpleDto appointmentSimpleDto : appointmentSimpleDtoList) {
            appointmentSimpleDto.setDoctorId(doctorId);
        }
        when(appointmentService.getDoctorAppointments(doctorId)).thenReturn(appointmentSimpleDtoList);
        String expectedResult = TestControllerHelper.getObjectAsString(appointmentSimpleDtoList, objectMapper);

        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.get("/appointments/doctor/{doctorId}", doctorId))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(expectedResult, result.getResponse().getContentAsString());
    }

    @Test
    void getDoctorAvailableHours_DataCorrect_DateDtoListReturned() throws Exception {
        Long doctorId = 1L;
        String specialization = "specialization";
        LocalDate date = LocalDate.now();
        List<AppointmentDateDto> appointmentDateDtoList = TestData.AppointmentDateDtoFactory.getList(2);
        when(appointmentService.getDoctorAvailableAppointments(date, specialization, doctorId)).thenReturn(appointmentDateDtoList);
        String expectedResult = TestControllerHelper.getObjectAsString(appointmentDateDtoList, objectMapper);

        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.get("/appointments/doctor")
                        .param("date", date.toString())
                        .param("specialization", specialization)
                        .param("doctorId", doctorId.toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(expectedResult, result.getResponse().getContentAsString());
    }

    @Test
    void addPatientToAppointment_DataCorrect_SimpleDtoReturned() throws Exception {
        Long patientId = 1L;
        Long appointmentId = 1L;
        AppointmentSimpleDto appointmentSimpleDto = TestData.AppointmentSimpleDtoFactory.get(appointmentId);
        appointmentSimpleDto.setPatientId(patientId);
        when(appointmentService.addPatientToAppointment(patientId, appointmentId)).thenReturn(appointmentSimpleDto);
        String expectedResult = TestControllerHelper.getObjectAsString(appointmentSimpleDto, objectMapper);

        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.put("/appointments/{appointmentId}/patient/{patientId}", appointmentId, patientId))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(expectedResult, result.getResponse().getContentAsString());
    }
}
