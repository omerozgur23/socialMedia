package com.socialMedia.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
public class Employee {

	@Id
	@GeneratedValue
	private UUID id;

	private String email;

	private String firstName;

	private String lastName;

	private String password;

	private String birthDate;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;
}
