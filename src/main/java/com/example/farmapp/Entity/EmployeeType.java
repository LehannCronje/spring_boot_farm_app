package com.example.farmapp.Entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class EmployeeType {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="employeeType")
	private Set<FarmEmployee> farmEmployees;
	
}
