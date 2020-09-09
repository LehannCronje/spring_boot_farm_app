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
@SQLDelete(sql = "UPDATE user_application_role SET delete_date = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "delete_date IS NULL")
@Getter
@Setter
@NoArgsConstructor
public class UserApplicationRole {

	@Id
	@GeneratedValue
	private Long Id;

	private Date deleteDate;

	@ManyToOne(fetch = FetchType.EAGER)
	private ApplicationRole applicationRole;

	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
}
