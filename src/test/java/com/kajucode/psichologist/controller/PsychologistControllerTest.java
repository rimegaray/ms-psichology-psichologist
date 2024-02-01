package com.kajucode.psichologist.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kajucode.psichologist.controller.dto.PsychologistCreationRequest;
import com.kajucode.psichologist.controller.dto.PsychologistResponse;
import com.kajucode.psichologist.controller.dto.PsychologistUpdateRequest;
import com.kajucode.psichologist.service.PsychologistService;
import com.kajucode.psichologist.service.dto.PsychologistDto;

@ExtendWith(MockitoExtension.class)
public class PsychologistControllerTest {
	@InjectMocks
    private PsychologistController psychologistController;

    @Mock
    private PsychologistService psychologistServiceMock;


    @Test
    public void shouldGetPsychologistByIdReturnPatientResponseWhenServiceReturnPsychologistDto() {
        // Precondiciones
        int patientId = 1;
        Date date = new Date("12/01/24");
        
        // Configurar el mock del servicio para devolver un paciente simulado
        PsychologistResponse expectedPsychologistResponse = PsychologistResponse.builder()
        		.fullName("Yeremi")
                .dni(76351126)
                .age(17)
                .contactNumber(912923412)
                .address("la casa del raton")
                .email("yeremi.elraton@gmail.com")
                .contractDate(date)
                .specialty("psychologo")
                .build();
        
        PsychologistDto psychologistDtoStub = PsychologistDto.builder()
        		.fullName("Yeremi")
                .dni(76351126)
                .age(17)
                .contactNumber(912923412)
                .address("la casa del raton")
                .email("yeremi.elraton@gmail.com")
                .contractDate(date)
                .specialty("psychologo")
                .build();

        Mockito.when(psychologistServiceMock.getPsychologistById(anyInt())).thenReturn(psychologistDtoStub);

        PsychologistResponse psychologistResponseResult = psychologistController.getPsychologistById(patientId);
        
        // Verificar el resultado
        assertEquals(expectedPsychologistResponse.getFullName(), psychologistResponseResult.getFullName());
        assertEquals(expectedPsychologistResponse.getDni(), psychologistResponseResult.getDni());
        assertEquals(expectedPsychologistResponse.getAge(), psychologistResponseResult.getAge());
        assertEquals(expectedPsychologistResponse.getContactNumber() , psychologistResponseResult.getContactNumber());
        assertEquals(expectedPsychologistResponse.getAddress(), psychologistResponseResult.getAddress());
        assertEquals(expectedPsychologistResponse.getEmail(), psychologistResponseResult.getEmail());
        assertEquals(expectedPsychologistResponse.getContractDate(), psychologistResponseResult.getContractDate());
        assertEquals(expectedPsychologistResponse.getSpecialty(), psychologistResponseResult.getSpecialty());
    }
    
	@Test
	public void shouldAddPsychologisttReturnPatientResponseWhenServiceReturnPsychologistDto() {
		// Precondiciones
	    Date date = new Date("12/01/24");
	 
	    PsychologistCreationRequest psychologistCreationRequest = PsychologistCreationRequest.builder()
	    		.fullName("Yeremi")
	            .dni(76351126)
	            .age(17)
	            .contactNumber(912923412)
	            .address("la casa del raton")
	            .email("yeremi.elraton@gmail.com")
	            .contractDate(date)
	            .specialty("psychologo")
	            .build();
	    
	    PsychologistDto psychologistDtoStub = PsychologistDto.builder()
	    		.fullName("Yeremi")
	            .dni(76351126)
	            .age(17)
	            .contactNumber(912923412)
	            .address("la casa del raton")
	            .email("yeremi.elraton@gmail.com")
	            .contractDate(date)
	            .specialty("psychologo")
	            .build();
	    
	    PsychologistResponse expectedPsychologistResponse = PsychologistResponse.builder()
	            .fullName("Yeremi")
	            .dni(76351126)
	            .age(17)
	            .contactNumber(912923412)
	            .address("la casa del raton")
	            .email("yeremi.elraton@gmail.com")
	            .contractDate(date)
	            .specialty("psychologo")
	            .build();
	
	            
	    when(psychologistServiceMock.addPsychologist(any())).thenReturn(psychologistDtoStub);
	
	    // Ejecución
	    PsychologistResponse psychologistResponseResult = psychologistController.addPsychologist(psychologistCreationRequest);
	
	    // Assert
	    assertNotNull(psychologistResponseResult);
	    assertEquals(expectedPsychologistResponse.getFullName(), psychologistResponseResult.getFullName());
	    assertEquals(expectedPsychologistResponse.getDni(), psychologistResponseResult.getDni());
	    assertEquals(expectedPsychologistResponse.getAge(), psychologistResponseResult.getAge());
	    assertEquals(expectedPsychologistResponse.getContactNumber() , psychologistResponseResult.getContactNumber());
	    assertEquals(expectedPsychologistResponse.getAddress(), psychologistResponseResult.getAddress());
	    assertEquals(expectedPsychologistResponse.getEmail(), psychologistResponseResult.getEmail());
	    assertEquals(expectedPsychologistResponse.getContractDate(), psychologistResponseResult.getContractDate());
	    assertEquals(expectedPsychologistResponse.getSpecialty(), psychologistResponseResult.getSpecialty());
	} 

	@Test
	public void shouldUpdatePsychologistReturnPatientResponseWhenServiceReturnPsychologistDto() {
		int id = 1;
		Date date = new Date("12/01/24");
	
		PsychologistUpdateRequest expectedPsychologistResponse = new PsychologistUpdateRequest();
	    expectedPsychologistResponse.setFullName("Yeremi");
	    expectedPsychologistResponse.setDni(76351126);
	    expectedPsychologistResponse.setAge(17);
	    expectedPsychologistResponse.setContactNumber(912923412);
	    expectedPsychologistResponse.setAddress("la casa del raton");
	    expectedPsychologistResponse.setEmail("yeremi.elraton@gmail.com");
	    expectedPsychologistResponse.setContractDate(date);
	    expectedPsychologistResponse.setSpecialty("psychologo");
	    
	    PsychologistDto psychologistDtoStub = PsychologistDto.builder()
	            .fullName("Yeremi")
	            .dni(76351126)
	            .age(17)
	            .contactNumber(912923412)
	            .address("la casa del raton")
	            .email("yeremi.elraton@gmail.com")
	            .contractDate(date)
	            .specialty("psychologo")
	            .build();
	    
	    when(psychologistServiceMock.updatePsychologist(anyInt(), any())).thenReturn(psychologistDtoStub);
	    
	    PsychologistResponse patientResponseResult = psychologistController.updatePsychologist(id, expectedPsychologistResponse);
	    
	    assertNotNull(patientResponseResult);
	    assertEquals(expectedPsychologistResponse.getFullName(), patientResponseResult.getFullName());
	    assertEquals(expectedPsychologistResponse.getDni(), patientResponseResult.getDni());
	    assertEquals(expectedPsychologistResponse.getAge(), patientResponseResult.getAge());
	    assertEquals(expectedPsychologistResponse.getContactNumber() , patientResponseResult.getContactNumber());
	    assertEquals(expectedPsychologistResponse.getAddress(), patientResponseResult.getAddress());
	    assertEquals(expectedPsychologistResponse.getEmail(), patientResponseResult.getEmail());
	    assertEquals(expectedPsychologistResponse.getContractDate(), patientResponseResult.getContractDate());
	    assertEquals(expectedPsychologistResponse.getSpecialty(), patientResponseResult.getSpecialty());
	}

	@Test
	public void shouldDeletePsychologistIsSuccessfulWhenServiceIsSuccessful() {
		int id = 1;
		
		doNothing().when(psychologistServiceMock).deletePsychologist(eq(id));
		
	    psychologistController.deletePsychologist(id);
		
	    verify(psychologistServiceMock, times(1)).deletePsychologist(eq(id));
	
	}
	
	@Test
	public void shouldGetAllPsychologistsReturnPsychologistResponseWhenServiceReturnListToPsychologistDto() {
		Date date1 = new Date("12/01/24");
		Date date2 = new Date("13/01/24"); 
		
		PsychologistResponse psychologistResponse1 = PsychologistResponse.builder()
				.fullName("Yeremi")
	            .dni(76351126)
	            .age(17)
	            .contactNumber(912923412)
	            .address("la casa del raton")
	            .email("yeremi.elraton@gmail.com")
	            .contractDate(date1)
	            .specialty("psychologo")
	            .build();
	    
		
		PsychologistResponse psychologistResponse2 = PsychologistResponse.builder()
				.fullName("Yeremi")
	            .dni(76351126)
	            .age(17)
	            .contactNumber(912923412)
	            .address("la casa del raton")
	            .email("yeremi.elraton@gmail.com")
	            .contractDate(date2)
	            .specialty("psychologo")
	            .build();
	    
		
		PsychologistDto stubPsychologistDto1 = PsychologistDto.builder().fullName(("Yeremi"))
				.fullName("Yeremi")
	            .dni(76351126)
	            .age(17)
	            .contactNumber(912923412)
	            .address("la casa del raton")
	            .email("yeremi.elraton@gmail.com")
	            .contractDate(date1)
	            .specialty("psychologo")
	            .build();
	    
		
		PsychologistDto stubPsychologistDto2 = PsychologistDto.builder().fullName(("Juancho"))
				.fullName("Yeremi")
	            .dni(76351126)
	            .age(17)
	            .contactNumber(912923412)
	            .address("la casa del raton")
	            .email("yeremi.elraton@gmail.com")
	            .contractDate(date2)
	            .specialty("psychologo")
	            .build();
	    
		
		
		List<PsychologistDto> stubPsychologistDtos = Arrays.asList(stubPsychologistDto1, stubPsychologistDto2);
		List<PsychologistResponse> psychologistResponses = Arrays.asList(psychologistResponse1, psychologistResponse2);
		
		when(psychologistServiceMock.getAll()).thenReturn(stubPsychologistDtos);
		
		List<PsychologistResponse> psychologistResponseResult = psychologistController.getAll();
		
		assertEquals(psychologistResponseResult.get(0).getFullName(), psychologistResponses.get(0).getFullName());
		assertEquals(psychologistResponseResult.get(0).getDni(), psychologistResponses.get(0).getDni());
		assertEquals(psychologistResponseResult.get(0).getAge(), psychologistResponses.get(0).getAge());
		assertEquals(psychologistResponseResult.get(0).getContactNumber(), psychologistResponses.get(0).getContactNumber());
		assertEquals(psychologistResponseResult.get(0).getAddress(), psychologistResponses.get(0).getAddress());
		assertEquals(psychologistResponseResult.get(0).getEmail(), psychologistResponses.get(0).getEmail());
		assertEquals(psychologistResponseResult.get(0).getContractDate(), psychologistResponses.get(0).getContractDate());
		assertEquals(psychologistResponseResult.get(0).getSpecialty(), psychologistResponses.get(0).getSpecialty());
		
		assertEquals(psychologistResponseResult.get(1).getFullName(), psychologistResponses.get(1).getFullName());
		assertEquals(psychologistResponseResult.get(1).getDni(), psychologistResponses.get(1).getDni());
		assertEquals(psychologistResponseResult.get(1).getAge(), psychologistResponses.get(1).getAge());
		assertEquals(psychologistResponseResult.get(1).getContactNumber(), psychologistResponses.get(1).getContactNumber());
		assertEquals(psychologistResponseResult.get(1).getAddress(), psychologistResponses.get(1).getAddress());
		assertEquals(psychologistResponseResult.get(1).getEmail(), psychologistResponses.get(1).getEmail());
		assertEquals(psychologistResponseResult.get(1).getContractDate(), psychologistResponses.get(1).getContractDate());
		assertEquals(psychologistResponseResult.get(1).getSpecialty(), psychologistResponses.get(1).getSpecialty());
				
	}
}
