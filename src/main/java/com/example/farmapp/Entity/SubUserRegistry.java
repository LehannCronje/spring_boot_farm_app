package com.example.farmapp.Entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SubUserRegistry {

	@GeneratedValue
	@Id
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private User createdByUser;

	@OneToOne(fetch = FetchType.LAZY)
	private User user;
}
