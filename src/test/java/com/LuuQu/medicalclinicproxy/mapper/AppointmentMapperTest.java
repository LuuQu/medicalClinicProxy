package com.LuuQu.medicalclinicproxy.mapper;

import com.LuuQu.medicalclinicproxy.model.*;
import com.LuuQu.medicalclinicproxy.testHelper.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class AppointmentMapperTest {
    private AppointmentMapper appointmentMapper;

    @BeforeEach
    void setUp() {
        appointmentMapper = new AppointmentMapperImpl();
    }

    @ParameterizedTest
    @MethodSource
    void toDateDtoTest(AppointmentDto input, AppointmentDateDto expectedResult) {
        AppointmentDateDto result = appointmentMapper.toDateDto(input);
        if (input == null) {
            Assertions.assertNull(result);
            return;
        }
        Assertions.assertEquals(expectedResult.getId(), result.getId());
        Assertions.assertEquals(expectedResult.getStartDate(), result.getStartDate());
        Assertions.assertEquals(expectedResult.getEndDate(), result.getEndDate());
    }

    private static Stream<Arguments> toDateDtoTest() {
        return Stream.of(
                Arguments.of(null, null),
                Arguments.of(new AppointmentDto(), new AppointmentDateDto()),
                Arguments.of(TestData.AppointmentDtoFactory.get(), TestData.AppointmentDateDtoFactory.get()),
                Arguments.of(TestData.AppointmentDtoFactory.get(1L), TestData.AppointmentDateDtoFactory.get(1L))
        );
    }

    @ParameterizedTest
    @MethodSource
    void toSimpleDtoTest(AppointmentDto input, AppointmentSimpleDto expectedResult) {
        AppointmentSimpleDto result = appointmentMapper.toSimpleDto(input);
        if (input == null) {
            Assertions.assertNull(result);
            return;
        }
        Assertions.assertEquals(expectedResult.getId(), result.getId());
        Assertions.assertEquals(expectedResult.getStartDate(), result.getStartDate());
        Assertions.assertEquals(expectedResult.getEndDate(), result.getEndDate());
        Assertions.assertEquals(expectedResult.getPatientId(), result.getPatientId());
        Assertions.assertEquals(expectedResult.getDoctorId(), result.getDoctorId());
    }

    private static Stream<Arguments> toSimpleDtoTest() {
        AppointmentDto fullAppointmentDto = TestData.AppointmentDtoFactory.get(1L);
        fullAppointmentDto.setPatient(new PatientDto());
        fullAppointmentDto.getPatient().setId(1L);
        fullAppointmentDto.setDoctor(new DoctorDto());
        fullAppointmentDto.getDoctor().setId(1L);

        AppointmentSimpleDto fullAppointmentSimpleDto = TestData.AppointmentSimpleDtoFactory.get(1L);
        fullAppointmentSimpleDto.setPatientId(1L);
        fullAppointmentSimpleDto.setDoctorId(1L);

        return Stream.of(
                Arguments.of(null, null),
                Arguments.of(new AppointmentDto(), new AppointmentSimpleDto()),
                Arguments.of(TestData.AppointmentDtoFactory.get(), TestData.AppointmentSimpleDtoFactory.get()),
                Arguments.of(TestData.AppointmentDtoFactory.get(1L), TestData.AppointmentSimpleDtoFactory.get(1L)),
                Arguments.of(fullAppointmentDto, fullAppointmentSimpleDto)
        );
    }
}
