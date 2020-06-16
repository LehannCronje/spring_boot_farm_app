package com.example.farmapp.Entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TxnWorkLog {

	@Id
	@GeneratedValue
	private Long id;

	private int totalHours;

	private LocalDateTime timestamp;

	@ManyToOne(fetch = FetchType.LAZY)
	private HourType hourType;

	@ManyToOne(fetch = FetchType.LAZY)
	private Employee employee;

	@ManyToOne(fetch = FetchType.LAZY)
	private CropWork cropWork;
}
