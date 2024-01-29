package com.kajucode.psichologist.repository.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "psychologist")
public class PsychologistEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_psychologist")
	private int psychologistId;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "dni")
	private int dni;

	@Column(name = "age")
	private int age;

	@Column(name = "contact_number")
	private int contactNumber;

	@Column(name = "address")
	private String address;

	@Column(name = "email")
	private String email;

	@Column(name = "contract_date")
	private Date contractDate;

	@Column(name = "specialty")
	private String specialty;
}
