package com.example.farmapp.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@SQLDelete(sql = "UPDATE sub_user_registry SET delete_date = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "delete_date IS NULL")
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

	private Date deleteDate;
}
