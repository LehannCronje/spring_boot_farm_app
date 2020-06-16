package com.example.farmapp.Entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FarmSite {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Farm farm;

	@ManyToOne(fetch = FetchType.LAZY)
	@Where(clause = "name != 'UNNASIGNED-TEMP'")
	private Site site;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "farmSite")
	@JsonIgnore
	private Set<SiteCrop> siteCrop = new HashSet<SiteCrop>();
}
