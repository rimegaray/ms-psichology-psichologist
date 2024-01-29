package com.kajucode.psichologist.service.convert;


import com.kajucode.psichologist.repository.dto.PsychologistDto;
import com.kajucode.psichologist.repository.entity.PsychologistEntity;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ServiceConverter {

	public PsychologistDto convertPsychologistEntityToPsychologistDto(PsychologistEntity psychologistEntity) {
		return PsychologistDto.builder().fullName(psychologistEntity.getFullName())
				.dni(psychologistEntity.getDni())
				.age(psychologistEntity.getAge())
				.contactNumber(psychologistEntity.getContactNumber())
				.address(psychologistEntity.getAddress())
				.email(psychologistEntity.getEmail())
				.contractDate(psychologistEntity.getContractDate())
				.specialty(psychologistEntity.getSpecialty())
				.build();
	}
	public PsychologistEntity convertPsychologistDtoToPsychologistEnity (PsychologistDto psychologistDto) {
		PsychologistEntity psychologistEntity = new PsychologistEntity();
        psychologistEntity.setFullName(psychologistDto.getFullName());
        psychologistEntity.setDni(psychologistDto.getDni());
        psychologistEntity.setAge(psychologistDto.getAge());
        psychologistEntity.setContactNumber(psychologistDto.getContactNumber());
        psychologistEntity.setAddress(psychologistDto.getAddress());
        psychologistEntity.setEmail(psychologistDto.getEmail());
        psychologistEntity.setContractDate(psychologistDto.getContractDate());
        psychologistEntity.setSpecialty(psychologistDto.getSpecialty());
        return psychologistEntity;
    }

}
