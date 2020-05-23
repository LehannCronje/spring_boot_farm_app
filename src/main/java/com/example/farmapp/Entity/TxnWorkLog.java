package com.example.farmapp.Entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Data
public class TxnWorkLog {
	
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private HourType hourType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Employee employee;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private CropWork cropWork;
}
