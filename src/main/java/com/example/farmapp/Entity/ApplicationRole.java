package com.example.farmapp.Entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@SQLDelete(sql = "UPDATE application_role SET delete_date = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "delete_date IS NULL")
@Getter
@Setter
@NoArgsConstructor
public class ApplicationRole {

	@Id
	@GeneratedValue
	private Long id;

	private String role;

	private Date deleteDate;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "applicationRole")
	@JsonManagedReference
	private Set<UserApplicationRole> userApplicationRoles;

	public ApplicationRole(String role) {
		this.role = role;
	}

}
