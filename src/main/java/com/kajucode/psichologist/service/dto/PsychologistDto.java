package com.kajucode.psichologist.service.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PsychologistDto {
	private String fullName;
	private int dni;
	private int age;
	private int contactNumber;
	private String address;
	private String email;
	private Date contractDate;
	private String specialty;
}
