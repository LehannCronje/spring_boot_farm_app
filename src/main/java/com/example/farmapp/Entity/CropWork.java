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
@SQLDelete(sql = "UPDATE crop_work SET delete_date = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "delete_date IS NULL")
@Getter
@Setter
@NoArgsConstructor
public class CropWork {

	@Id
	@GeneratedValue
	private Long id;

	private Date deleteDate;

	@ManyToOne(fetch = FetchType.LAZY)
	private SiteCrop siteCrop;

	@ManyToOne(fetch = FetchType.LAZY)
	private Work work;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cropWork")
	private Set<TxnWorkLog> txnWorkLog;
}
