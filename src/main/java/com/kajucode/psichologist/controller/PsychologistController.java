package com.kajucode.psichologist.controller;

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
import com.kajucode.psichologist.service.PsychologistServiceInterface;
import com.kajucode.psichologist.service.dto.PsychologistDto;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/psychologist")
public class PsychologistController {
	private final PsychologistServiceInterface psychologistServiceInterface;

	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PsychologistResponse> addPsychologist (@RequestBody PsychologistCreationRequest psychologistCreationRequest) {
    	PsychologistDto newPsychologistDto = PsychologistDto.builder()
    												.fullName(psychologistCreationRequest.getFullName())
													.dni(psychologistCreationRequest.getDni())
													.age(psychologistCreationRequest.getAge())
													.contactNumber(psychologistCreationRequest.getContactNumber())
													.address(psychologistCreationRequest.getAddress())
													.email(psychologistCreationRequest.getEmail())
													.contractDate(psychologistCreationRequest.getContractDate())
													.specialty(psychologistCreationRequest.getSpecialty())
													.build();
    	 return psychologistServiceInterface.addPsychologist(newPsychologistDto)
                 .map(ControllerConverter::convertPsychologistDtoToPsychologistResponse);
    } 
	@GetMapping
    public Flux<PsychologistResponse> getAll() {
		return psychologistServiceInterface.getAll()
                .map(ControllerConverter::convertPsychologistDtoToPsychologistResponse);
    } 
	
	@GetMapping("/{id}")
    public Mono<PsychologistResponse> getPsychologistById(@PathVariable int id) {
		 return psychologistServiceInterface.getPsychologistById(id)
	                .map(ControllerConverter::convertPsychologistDtoToPsychologistResponse);
    }
	
	@PutMapping("/{id}")
    public Mono<PsychologistResponse> updatePsychologist(@PathVariable int id, @RequestBody PsychologistUpdateRequest psychologistUpdateRequest) {
		 PsychologistDto psychologistDto = ControllerConverter.convertPsychologistUpdatRequestToPsychologistDto(psychologistUpdateRequest);
	        return psychologistServiceInterface.updatePsychologist(id, psychologistDto)
	                .map(ControllerConverter::convertPsychologistDtoToPsychologistResponse);
    }
	
	@DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deletePsychologist(@PathVariable int idPsychologist) {
        return psychologistServiceInterface.deletePsychologist(idPsychologist);
    }
}
