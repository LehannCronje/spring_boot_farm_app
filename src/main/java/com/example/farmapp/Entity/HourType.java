package com.example.farmapp.Entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Data;

@Entity
@SQLDelete(sql = "UPDATE hour_type SET delete_date = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "delete_date IS NULL")
@Data
public class HourType {

	@Id
	@GeneratedValue
	private Long id;

	private Date deleteDate;

	private String name;

	private Double hourlyRate;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "hourType")
	private Set<TxnWorkLog> txnWorkLog;

}
