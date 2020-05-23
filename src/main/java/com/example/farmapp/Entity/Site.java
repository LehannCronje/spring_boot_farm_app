package com.example.farmapp.Entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Site {

	@Id
	@GeneratedValue
	private Long Id;

	
	private String name;


	@OneToMany(cascade = CascadeType.ALL, mappedBy = "site")
	@JsonIgnore
	private Set<FarmSite> farmSite = new HashSet<FarmSite>();

}