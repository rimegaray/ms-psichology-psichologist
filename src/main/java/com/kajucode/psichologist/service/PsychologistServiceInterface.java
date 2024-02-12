package com.kajucode.psichologist.service;

import java.util.List;

import com.kajucode.psichologist.service.dto.PsychologistDto;

public interface PsychologistServiceInterface{
	PsychologistDto addPsychologist(PsychologistDto psychologistDto);
    List<PsychologistDto> getAll();
    PsychologistDto getPsychologistById(int psychologistId);
    PsychologistDto updatePsychologist(int psychologistId, PsychologistDto psychologistDto);
    void deletePsychologist(int psychologistId);
	
}
