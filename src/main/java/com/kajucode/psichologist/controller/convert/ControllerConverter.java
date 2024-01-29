package com.kajucode.psichologist.controller.convert;

import com.kajucode.psichologist.controller.dto.PsychologistResponse;
import com.kajucode.psichologist.repository.dto.PsychologistDto;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ControllerConverter {
	
	public PsychologistResponse convertPsychologistDtoToPsychologistResponse (PsychologistDto psychologistDto) {
		return PsychologistResponse.builder().fullName(psychologistDto.getFullName())
									.dni(psychologistDto.getDni())
									.age(psychologistDto.getAge())
									.contactNumber(psychologistDto.getContactNumber())
									.address(psychologistDto.getAddress())
									.email(psychologistDto.getEmail())
									.contractDate(psychologistDto.getContractDate())
									.specialty(psychologistDto.getSpecialty())
									.build();

	}
}
