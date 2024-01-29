package com.kajucode.psichologist.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kajucode.psichologist.repository.dao.PsychologistDao;
import com.kajucode.psichologist.repository.dto.PsychologistDto;
import com.kajucode.psichologist.repository.entity.PsychologistEntity;
import com.kajucode.psichologist.service.convert.ServiceConverter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PsychologistService {
	private final PsychologistDao psychologistDao;
	
	public PsychologistDto addPatient (PsychologistDto psychologistDto) {
        PsychologistEntity patientResult = psychologistDao.save(ServiceConverter.convertPsychologistDtoToPsychologistEnity(psychologistDto));
        return ServiceConverter.convertPsychologistEntityToPsychologistDto(patientResult);
    }
	
	public List<PsychologistDto> getAll() {
        List<PsychologistEntity> patientEntities = psychologistDao.findAll();
        return patientEntities.stream()
                .map(ServiceConverter::convertPsychologistEntityToPsychologistDto)
                .collect(Collectors.toList());
    }
}
