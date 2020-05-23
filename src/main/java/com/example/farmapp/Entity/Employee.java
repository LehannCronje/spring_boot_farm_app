package com.example.farmapp.Entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String email;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private Set<FarmEmployee> farmEmployees;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="employee")
    private Set<TxnWorkLog> txnWorkLog;
    
//    @OneToMany(cascade = CascadeType.ALL, mappedBy ="employee")
//    private Set<UserEmployee> userEmployee;
    
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

}