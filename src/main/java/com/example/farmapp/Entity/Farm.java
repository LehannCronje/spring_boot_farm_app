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

@Entity
@SQLDelete(sql = "UPDATE Farm SET delete_date = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "delete_date IS NULL")
@Getter
@Setter
@NoArgsConstructor
public class Farm {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Date deleteDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "farm")
    @JsonIgnore
    private Set<FarmSite> farmSite = new HashSet<FarmSite>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "farm")
    @JsonIgnore
    private Set<WorkerGroup> workerGroup;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "farm")
    @JsonIgnore
    private Set<FarmEmployee> farmEmployees;
}