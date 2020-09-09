package com.example.farmapp.Entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@SQLDelete(sql = "UPDATE Employee SET delete_date = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "delete_date IS NULL")
@Getter
@Setter
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String surname;

    private String email;

    private int workedHours;

    private Date deleteDate;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private Set<FarmEmployee> farmEmployees;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private Set<TxnWorkLog> txnWorkLog;

    // @OneToMany(cascade = CascadeType.ALL, mappedBy ="employee")
    // private Set<UserEmployee> userEmployee;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

}