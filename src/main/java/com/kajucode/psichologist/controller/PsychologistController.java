package com.kajucode.psichologist.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kajucode.psichologist.controller.convert.ControllerConverter;
import com.kajucode.psichologist.controller.dto.PsychologistCreationRequest;
import com.kajucode.psichologist.controller.dto.PsychologistResponse;
import com.kajucode.psichologist.controller.dto.PsychologistUpdateRequest;
import com.kajucode.psichologist.repository.dto.PsychologistDto;
import com.kajucode.psichologist.service.PsychologistService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/psychologist")
public class PsychologistController {
	private final PsychologistService psychologistService;

	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PsychologistResponse addPsychologist (@RequestBody PsychologistCreationRequest patientRequest) {
    	PsychologistDto newPsychologistDto = PsychologistDto.builder().fullName(patientRequest.getFullName())
													.dni(patientRequest.getDni())
													.age(patientRequest.getAge())
													.contactNumber(patientRequest.getContactNumber())
													.address(patientRequest.getAddress())
													.email(patientRequest.getEmail())
													.contractDate(patientRequest.getContractDate())
													.specialty(patientRequest.getSpecialty())
													.build();
    	return ControllerConverter.convertPsychologistDtoToPsychologistResponse(psychologistService.addPsychologist(newPsychologistDto));
    } 
	@GetMapping
    public List<PsychologistResponse> getAll() {
        List<PsychologistDto> patients = psychologistService.getAll();
        return patients.stream()
                .map(ControllerConverter::convertPsychologistDtoToPsychologistResponse)
                .collect(Collectors.toList());
    } 
	
	@GetMapping("/{id}")
    public PsychologistResponse getPsychologistById(@PathVariable int id) {
        return ControllerConverter.convertPsychologistDtoToPsychologistResponse(psychologistService.getPsychologisttById(id));
    }
	
	@PutMapping("/{id}")
    public PsychologistResponse updatePsychologist(@PathVariable int id, @RequestBody PsychologistUpdateRequest psychologistUpdateRequest) {
		PsychologistDto psychologistDto = ControllerConverter.convertPatientUpdatRequestToPatientDto(psychologistUpdateRequest);
    	return ControllerConverter.convertPsychologistDtoToPsychologistResponse(psychologistService.updatePsychologist(id, psychologistDto));
    }
	@DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePsychologist(@PathVariable int idPsychologist) {
        psychologistService.deletePsychologist(idPsychologist);
    }
}
