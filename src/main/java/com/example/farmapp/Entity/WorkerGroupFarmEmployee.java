package com.example.farmapp.Entity;

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
@SQLDelete(sql = "UPDATE worker_group_farm_employee SET delete_date = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "delete_date IS NULL")
@Getter
@Setter
@NoArgsConstructor
public class WorkerGroupFarmEmployee {

	@Id
	@GeneratedValue
	private Long id;

	private Date deleteDate;

	@ManyToOne(fetch = FetchType.LAZY)
	private FarmEmployee farmEmployee;

	@ManyToOne(fetch = FetchType.LAZY)
	private WorkerGroupRole workerGroupRole;
}
