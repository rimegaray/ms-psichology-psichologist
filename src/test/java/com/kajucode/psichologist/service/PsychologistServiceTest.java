package com.kajucode.psichologist.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import com.kajucode.psichologist.repository.dao.PsychologistDao;
import com.kajucode.psichologist.repository.entity.PsychologistEntity;
import com.kajucode.psichologist.service.dto.PsychologistDto;

@ExtendWith(MockitoExtension.class)
public class PsychologistServiceTest {

	@Mock
	PsychologistDao psychologistDaoMock;

	@InjectMocks
    private PsychologistService psychologistService;
	
	
	@Test
	public void shouldAddPsychologistReturnSuccessfulWhenDaoIsOk() {
		
		//Precondiciones
		Date date = new Date("12/01/24");
		
		PsychologistDto psychologistDtoExpected = PsychologistDto.builder().fullName(("Yeremi"))
				.dni(76351126)
				.age(17)
				.contactNumber(912923412)
				.address("la casa del raton")
				.email("yeremi.elraton@gmail.com")
				.contractDate(date)
				.specialty("psychologo")
				.build();
		
		PsychologistDto psychologistDto = PsychologistDto.builder().fullName(("Yeremi"))
				.dni(76351126)
				.age(17)
				.contactNumber(912923412)
				.address("la casa del raton")
				.email("yeremi.elraton@gmail.com")
				.contractDate(date)
				.specialty("psychologo")
				.build();
		
		PsychologistEntity psychologisttEntityResult = new PsychologistEntity();
		psychologisttEntityResult.setPsychologistId(0);
		psychologisttEntityResult.setFullName("Yeremi");
		psychologisttEntityResult.setDni(76351126);
		psychologisttEntityResult.setAge(17);
		psychologisttEntityResult.setContactNumber(912923412);
		psychologisttEntityResult.setAddress("la casa del raton");
		psychologisttEntityResult.setEmail("yeremi.elraton@gmail.com");
		psychologisttEntityResult.setContractDate(date);
		psychologisttEntityResult.setSpecialty("psychologo");
		
		Mockito.when(psychologistDaoMock.save(Mockito.any())).thenReturn(psychologisttEntityResult);
		// Ejecucion
		PsychologistDto psychologistDtoResult = psychologistService.addPsychologist(psychologistDto);

		// Asserts
		assertNotNull(psychologistDtoResult);
		assertEquals(psychologistDtoResult.getFullName(), psychologistDtoExpected.getFullName());
		assertEquals(psychologistDtoResult.getDni(), psychologistDtoExpected.getDni());
		assertEquals(psychologistDtoResult.getAge(), psychologistDtoExpected.getAge());
		assertEquals(psychologistDtoResult.getContactNumber(), psychologistDtoExpected.getContactNumber());
		assertEquals(psychologistDtoResult.getAddress(), psychologistDtoExpected.getAddress());
		assertEquals(psychologistDtoResult.getEmail(), psychologistDtoExpected.getEmail());
		assertEquals(psychologistDtoResult.getContractDate(), psychologistDtoExpected.getContractDate());
		assertEquals(psychologistDtoResult.getSpecialty(), psychologistDtoExpected.getSpecialty());
	}
	
	@Test
	public void shouldDeletePsychologistIsSuccessfulWhenServiceIsSuccessful() {
		
		//precondiciones
		Mockito.doNothing().when(psychologistDaoMock).deleteById(Mockito.anyInt());
		//ejecucion
		psychologistService.deletePsychologist(123);
		//verificaciones
		Mockito.verify(psychologistDaoMock, Mockito.times(1)).deleteById(123);
	}
	
	@Test
	public void shouldGetAllReturnSuccessfulWhenDaoIsOk() {
		
		//precondiciones
		Date date1 = new Date("12/01/24");
		Date date2= new Date("12/02/24");
		
		PsychologistDto psychologistDtoExpected1 = PsychologistDto.builder().fullName(("Yeremi"))
				.dni(76351126)
				.age(17)
				.contactNumber(912923412)
				.address("la casa del raton")
				.email("yeremi.elraton@gmail.com")
				.contractDate(date1)
				.specialty("psychologo")
				.build();
		
		PsychologistDto psychologistDtoExpected2 = PsychologistDto.builder().fullName(("Juancho"))
				.dni(87654321)
				.age(28)
				.contactNumber(987654321)
				.address("2 cm antes de las nubes")
				.email("juancho.elraton@gmail.com")
				.contractDate(date2)
				.specialty("ratonchologist")
				.build();
		
		
		PsychologistEntity stubPsychologist1 = new PsychologistEntity();
		stubPsychologist1.setPsychologistId(0);
		stubPsychologist1.setFullName("Yeremi");
		stubPsychologist1.setDni(76351126);
		stubPsychologist1.setAge(17);
		stubPsychologist1.setContactNumber(912923412);
		stubPsychologist1.setAddress("la casa del raton");
		stubPsychologist1.setEmail("yeremi.elraton@gmail.com");
		stubPsychologist1.setContractDate(date1);
		stubPsychologist1.setSpecialty("psychologo");
		
		PsychologistEntity stubPsychologist2 = new PsychologistEntity();
		stubPsychologist2.setPsychologistId(1);
		stubPsychologist2.setFullName("Juancho");
		stubPsychologist2.setDni(87654321);
		stubPsychologist2.setAge(28);
		stubPsychologist2.setContactNumber(987654321);
		stubPsychologist2.setAddress("2 cm antes de las nubes");
		stubPsychologist2.setEmail("juancho.elraton@gmail.com");
		stubPsychologist2.setContractDate(date2);
		stubPsychologist2.setSpecialty("ratonchologist");
		
		List<PsychologistEntity> stubPsychologists = Arrays.asList(stubPsychologist1, stubPsychologist2);
		List<PsychologistDto> psychologistsDtoExpecteds = Arrays.asList(psychologistDtoExpected1, psychologistDtoExpected2);

		Mockito.when(psychologistDaoMock.findAll()).thenReturn(stubPsychologists);

		//ejecucion
		List<PsychologistDto> result = psychologistService.getAll();

		//assert
		assertNotNull(result);
		assertEquals(psychologistsDtoExpecteds.size(), result.size());
		verify(psychologistDaoMock, Mockito.times(1)).findAll();
		
		assertEquals(result.get(0).getFullName(), psychologistsDtoExpecteds.get(0).getFullName());
		assertEquals(result.get(0).getDni(), psychologistsDtoExpecteds.get(0).getDni());
		assertEquals(result.get(0).getAge(), psychologistsDtoExpecteds.get(0).getAge());
		assertEquals(result.get(0).getContactNumber(), psychologistsDtoExpecteds.get(0).getContactNumber());
		assertEquals(result.get(0).getAddress(), psychologistsDtoExpecteds.get(0).getAddress());
		assertEquals(result.get(0).getEmail(), psychologistsDtoExpecteds.get(0).getEmail());
		assertEquals(result.get(0).getContractDate(), psychologistsDtoExpecteds.get(0).getContractDate());
		assertEquals(result.get(0).getSpecialty(), psychologistsDtoExpecteds.get(0).getSpecialty());
		
		assertEquals(result.get(1).getFullName(), psychologistsDtoExpecteds.get(1).getFullName());
		assertEquals(result.get(1).getDni(), psychologistsDtoExpecteds.get(1).getDni());
		assertEquals(result.get(1).getAge(), psychologistsDtoExpecteds.get(1).getAge());
		assertEquals(result.get(1).getContactNumber(), psychologistsDtoExpecteds.get(1).getContactNumber());
		assertEquals(result.get(1).getAddress(), psychologistsDtoExpecteds.get(1).getAddress());
		assertEquals(result.get(1).getEmail(), psychologistsDtoExpecteds.get(1).getEmail());
		assertEquals(result.get(1).getContractDate(), psychologistsDtoExpecteds.get(1).getContractDate());
		assertEquals(result.get(1).getSpecialty(), psychologistsDtoExpecteds.get(1).getSpecialty());
	}
	
	@Test
	public void shouldGetPsychologistByIdReturnSuccessfulWhenDaoIsOk() {
		
		//precondiciones
		int psychologistId = 1;
        Date date = new Date("12/01/24");
        
        PsychologistDto expectedPsychologistDto = PsychologistDto.builder()
                .fullName("Yeremi")
                .dni(76351126)
                .age(17)
                .contactNumber(912923412)
                .address("la casa del raton")
                .email("yeremi.elraton@gmail.com")
                .contractDate(date)
                .specialty("psychologo")
                .build();
        
        PsychologistEntity psychologistEntityStub = new PsychologistEntity();
		psychologistEntityStub.setPsychologistId(0);
		psychologistEntityStub.setFullName("Yeremi");
		psychologistEntityStub.setDni(76351126);
		psychologistEntityStub.setAge(17);
		psychologistEntityStub.setContactNumber(912923412);
		psychologistEntityStub.setAddress("la casa del raton");
		psychologistEntityStub.setEmail("yeremi.elraton@gmail.com");
		psychologistEntityStub.setContractDate(date);
		psychologistEntityStub.setSpecialty("psychologo");
		
		when(psychologistDaoMock.findById(anyInt())).thenReturn(Optional.of(psychologistEntityStub));
		
		//ejecucion
		PsychologistDto result = psychologistService.getPsychologistById(psychologistId);
		
		//assert
		assertEquals(expectedPsychologistDto.getFullName(), result.getFullName());
        assertEquals(expectedPsychologistDto.getDni(), result.getDni());
        assertEquals(expectedPsychologistDto.getAge(), result.getAge());
        assertEquals(expectedPsychologistDto.getContactNumber() , result.getContactNumber());
        assertEquals(expectedPsychologistDto.getAddress(), result.getAddress());
        assertEquals(expectedPsychologistDto.getEmail(), result.getEmail());
        assertEquals(expectedPsychologistDto.getContractDate(), result.getContractDate());
        assertEquals(expectedPsychologistDto.getSpecialty(), result.getSpecialty());   
	}

	@Test
	public void shouldUpdatePsychologistReturnSuccessfulWhenDaoIsOk() {
		//precondiciones
		int id = 1;
    	Date date = new Date("12/01/24");
    	
    	PsychologistDto expectedPsychologistDto = PsychologistDto.builder()
    			.fullName("Yeremi")
                .dni(76351126)
                .age(17)
                .contactNumber(912923412)
                .address("la casa del raton")
                .email("yeremi.elraton@gmail.com")
                .contractDate(date)
                .specialty("psychologo")
                .build();
        
        PsychologistEntity psychologistEntityStub = new PsychologistEntity();
		psychologistEntityStub.setPsychologistId(0);
		psychologistEntityStub.setFullName("Yeremi");
		psychologistEntityStub.setDni(76351126);
		psychologistEntityStub.setAge(17);
		psychologistEntityStub.setContactNumber(912923412);
		psychologistEntityStub.setAddress("la casa del raton");
		psychologistEntityStub.setEmail("yeremi.elraton@gmail.com");
		psychologistEntityStub.setContractDate(date);
		psychologistEntityStub.setSpecialty("psychologo");
		
		when(psychologistDaoMock.save(any())).thenReturn(psychologistEntityStub);
		when(psychologistDaoMock.findById(anyInt())).thenReturn(Optional.of(psychologistEntityStub)); 
		
		//ejecucion
		PsychologistDto result = psychologistService.updatePsychologist(id, expectedPsychologistDto);

		//assert
        verify(psychologistDaoMock, times(1)).findById(id);
		assertEquals(expectedPsychologistDto.getFullName(), result.getFullName());
        assertEquals(expectedPsychologistDto.getDni(), result.getDni());
        assertEquals(expectedPsychologistDto.getAge(), result.getAge());
        assertEquals(expectedPsychologistDto.getContactNumber() , result.getContactNumber());
        assertEquals(expectedPsychologistDto.getAddress(), result.getAddress());
        assertEquals(expectedPsychologistDto.getEmail(), result.getEmail());
        assertEquals(expectedPsychologistDto.getContractDate(), result.getContractDate());
        assertEquals(expectedPsychologistDto.getSpecialty(), result.getSpecialty());
	}
	
	@Test
    public void shouldGetPsychologistByIdReturnExceptionWhenDaoReturnEmpty() {
		
        int id = 1;
        when(psychologistDaoMock.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
        	psychologistService.getPsychologistById(id);
        });
	}
}
