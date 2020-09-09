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
@SQLDelete(sql = "UPDATE site_crop SET delete_date = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "delete_date IS NULL")
@Getter
@Setter
@NoArgsConstructor
public class SiteCrop {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private FarmSite farmSite;

	private Date deleteDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@Where(clause = "name != 'UNNASIGNED-TEMP'")
	private Crop crop;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "siteCrop")
	private Set<CropWork> cropWork;
}
