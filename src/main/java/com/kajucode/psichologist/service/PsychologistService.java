package com.kajucode.psichologist.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kajucode.psichologist.repository.dao.PsychologistDao;
import com.kajucode.psichologist.repository.entity.PsychologistEntity;
import com.kajucode.psichologist.service.convert.ServiceConverter;
import com.kajucode.psichologist.service.dto.PsychologistDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PsychologistService {
	private final PsychologistDao psychologistDao;
	
	public PsychologistDto addPsychologist (PsychologistDto psychologistDto) {
        PsychologistEntity psychologistResult = psychologistDao.save(ServiceConverter.convertPsychologistDtoToPsychologistEntity(psychologistDto));
        return ServiceConverter.convertPsychologistEntityToPsychologistDto(psychologistResult);
    }
	
	public List<PsychologistDto> getAll() {
        List<PsychologistEntity> psychologistEntities = psychologistDao.findAll();
        return psychologistEntities.stream()
                .map(ServiceConverter::convertPsychologistEntityToPsychologistDto)
                .collect(Collectors.toList());
    }
	
	public PsychologistDto getPsychologistById(int pychologistId) {
		PsychologistEntity existingPsychologist = psychologistDao.findById(pychologistId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Psicologo no encontrado"));
		return ServiceConverter.convertPsychologistEntityToPsychologistDto(existingPsychologist); 
    }
	public PsychologistDto updatePsychologist(int pychologistId, PsychologistDto psychologistDto) {
		PsychologistEntity existingPsychologist = psychologistDao.findById(pychologistId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Psicologo no encontrado"));
        
        existingPsychologist.setFullName(psychologistDto.getFullName());
        existingPsychologist.setDni(psychologistDto.getDni());
        existingPsychologist.setAge(psychologistDto.getAge()); 
        existingPsychologist.setContactNumber(psychologistDto.getContactNumber());
        existingPsychologist.setAddress(psychologistDto.getAddress());
        existingPsychologist.setEmail(psychologistDto.getEmail());
        existingPsychologist.setContractDate(psychologistDto.getContractDate());
        existingPsychologist.setSpecialty(psychologistDto.getSpecialty());
     
        return ServiceConverter.convertPsychologistEntityToPsychologistDto(psychologistDao.save(existingPsychologist));
    }
	public void deletePsychologist (int idPsychologist) {
		psychologistDao.deleteById(idPsychologist);
    }
}
