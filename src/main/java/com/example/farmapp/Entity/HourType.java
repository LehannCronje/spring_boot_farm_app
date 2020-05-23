package com.example.farmapp.Entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Data
public class HourType {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	private Long hourlyRate;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="hourType")
    private Set<TxnWorkLog> txnWorkLog;
	
}
