package com.example.farmapp.Entity;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails{

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
	@NotEmpty
	private String username;

	@NotEmpty
	private String password;

    @OneToMany(fetch= FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    private Set<UserApplicationRole> userApplicationRoles = new HashSet<UserApplicationRole>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdByUser")
    private Set<SubUserRegistry> subUsers = new HashSet<SubUserRegistry>();
    
    @OneToOne(mappedBy= "user", cascade = CascadeType.ALL)
    private Employee employee;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<ApplicationRole> appRoles = this.userApplicationRoles.stream().map(UserApplicationRole::getApplicationRole).collect(toList());
		List<String> roles = appRoles.stream().map(ApplicationRole::getRole).collect(toList());
		return roles.stream().map(SimpleGrantedAuthority::new).collect(toList());
	}

	public List<String> getRoles(){
		List<ApplicationRole> appRoles = this.userApplicationRoles.stream().map(UserApplicationRole::getApplicationRole).collect(toList());
		return appRoles.stream().map(ApplicationRole::getRole).collect(toList());
	}
	
	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
    

}