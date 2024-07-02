package com.LuuQu.medicalclinicproxy.mapper;

import com.LuuQu.medicalclinicproxy.model.AppointmentDateDto;
import com.LuuQu.medicalclinicproxy.model.AppointmentDto;
import com.LuuQu.medicalclinicproxy.model.AppointmentSimpleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    AppointmentDateDto toDateDto(AppointmentDto appointmentDto);

    @Mapping(target = "patientId", source = "patient.id")
    @Mapping(target = "doctorId", source = "doctor.id")
    AppointmentSimpleDto toSimpleDto(AppointmentDto appointmentDto);
}
