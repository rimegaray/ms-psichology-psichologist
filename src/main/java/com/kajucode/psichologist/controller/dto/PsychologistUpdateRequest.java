package com.kajucode.psichologist.controller.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
