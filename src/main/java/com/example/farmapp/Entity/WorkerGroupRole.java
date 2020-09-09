package com.example.farmapp.Entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@SQLDelete(sql = "UPDATE worker_group_role SET delete_date = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "delete_date IS NULL")
@Getter
@Setter
@NoArgsConstructor
public class WorkerGroupRole {

	@Id
	@GeneratedValue
	private Long id;

	private Date deleteDate;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "workerGroupRole")
	private Set<WorkerGroupFarmEmployee> workerGroupFarmEmployees;

	@ManyToOne(fetch = FetchType.LAZY)
	private EmployeeRole employeeRole;

	@ManyToOne(fetch = FetchType.LAZY)
	private WorkerGroup workerGroup;

}
