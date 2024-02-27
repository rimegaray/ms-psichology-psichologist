package com.kajucode.psichologist.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kajucode.psichologist.repository.dao.PsychologistDao;
import com.kajucode.psichologist.repository.entity.PsychologistEntity;
import com.kajucode.psichologist.service.convert.ServiceConverter;
import com.kajucode.psichologist.service.dto.PsychologistDto;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class PsychologistService implements PsychologistServiceInterface {
	private final PsychologistDao psychologistDao;

	@Override
	public Mono<PsychologistDto> addPsychologist(PsychologistDto psychologistDto) {
		return Mono.just(psychologistDto).map(ServiceConverter::convertPsychologistDtoToPsychologistEntity)
				.flatMap(psychologistEntity -> Mono.just(psychologistDao.save(psychologistEntity)))
				.map(savedPsychologistEntity -> ServiceConverter
						.convertPsychologistEntityToPsychologistDto(savedPsychologistEntity));
	}

	@Override
	public Flux<PsychologistDto> getAll() {
		return Flux.fromIterable(psychologistDao.findAll())
				.map(ServiceConverter::convertPsychologistEntityToPsychologistDto);
	}

	@Override
	public Mono<PsychologistDto> getPsychologistById(int psychologistId) {
		return Mono.fromCallable(() -> {
			PsychologistEntity existingPsychologist = psychologistDao.findById(psychologistId)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Psychologist not found"));
			return ServiceConverter.convertPsychologistEntityToPsychologistDto(existingPsychologist);
		});
	}

	@Override
	public Mono<PsychologistDto> updatePsychologist(int psychologistId, PsychologistDto psychologistDto) {
		return Mono.fromCallable(() -> {
			PsychologistEntity existingPsychologist = psychologistDao.findById(psychologistId)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Psychologist not found"));

			existingPsychologist.setFullName(psychologistDto.getFullName());
			existingPsychologist.setDni(psychologistDto.getDni());
			existingPsychologist.setAge(psychologistDto.getAge());
			existingPsychologist.setContactNumber(psychologistDto.getContactNumber());
			existingPsychologist.setAddress(psychologistDto.getAddress());
			existingPsychologist.setEmail(psychologistDto.getEmail());
			existingPsychologist.setContractDate(psychologistDto.getContractDate());
			existingPsychologist.setSpecialty(psychologistDto.getSpecialty());

			PsychologistEntity updatedPsychologist = psychologistDao.save(existingPsychologist);
			return ServiceConverter.convertPsychologistEntityToPsychologistDto(updatedPsychologist);
		});
	}

	public Mono<Void> deletePsychologist(int idPsychologist) {
		return Mono.fromRunnable(() -> psychologistDao.deleteById(idPsychologist));
	}
}
