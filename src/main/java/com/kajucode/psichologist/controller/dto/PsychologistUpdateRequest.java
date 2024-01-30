package com.kajucode.psichologist.controller.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PsychologistUpdateRequest {
	private String fullName;
	private int dni;
	private int age;
	private int contactNumber;
	private String address;
	private String email;
	private Date contractDate;
	private String specialty;
}
