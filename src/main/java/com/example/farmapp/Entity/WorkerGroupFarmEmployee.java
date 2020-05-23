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

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class WorkerGroupFarmEmployee {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private FarmEmployee farmEmployee;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private WorkerGroupRole workerGroupRole;
}
