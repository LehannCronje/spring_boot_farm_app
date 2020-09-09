package com.example.farmapp.Entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@SQLDelete(sql = "UPDATE Site SET delete_date = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "delete_date IS NULL")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Site {

	@Id
	@GeneratedValue
	private Long Id;

	private String name;

	private Date deleteDate;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "site")
	@JsonIgnore
	private Set<FarmSite> farmSite = new HashSet<FarmSite>();

}