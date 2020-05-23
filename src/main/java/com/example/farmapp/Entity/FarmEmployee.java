package com.example.farmapp.Entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FarmEmployee {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Farm farm;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Employee employee;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private EmployeeType employeeType;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "farmEmployee")
	private Set<WorkerGroupFarmEmployee> workerGroupFarmEmployee;
}
