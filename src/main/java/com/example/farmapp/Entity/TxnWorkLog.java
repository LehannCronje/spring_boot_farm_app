package com.example.farmapp.Entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@SQLDelete(sql = "UPDATE txn_work_log SET delete_date = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "delete_date IS NULL")
@Getter
@Setter
@NoArgsConstructor
public class TxnWorkLog {

	@Id
	@GeneratedValue
	private Long id;

	private Date deleteDate;

	private int totalHours;

	private LocalDateTime timestamp;

	@ManyToOne(fetch = FetchType.LAZY)
	private HourType hourType;

	@ManyToOne(fetch = FetchType.LAZY)
	private Employee employee;

	@ManyToOne(fetch = FetchType.LAZY)
	private CropWork cropWork;
}
