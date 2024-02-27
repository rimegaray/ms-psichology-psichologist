package com.kajucode.psichologist.service;

import java.util.List;

import com.kajucode.psichologist.service.dto.PsychologistDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PsychologistServiceInterface{
	Mono<PsychologistDto> addPsychologist(PsychologistDto psychologistDto);
    Flux<PsychologistDto> getAll();
    Mono<PsychologistDto> getPsychologistById(int psychologistId);
    Mono<PsychologistDto> updatePsychologist(int psychologistId, PsychologistDto psychologistDto);
    Mono<Void> deletePsychologist(int psychologistId);
	
}
