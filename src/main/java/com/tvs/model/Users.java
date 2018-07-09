package com.tvs.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.tvs.model.*;

@Entity
@Table(name="users")
public class Users  {
	
	@Id
	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "enabled")
	private Short enabled;

	@OneToMany(mappedBy = "user", targetEntity = Authorities.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	// @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	// @JoinColumn(name="username" )
	@Fetch(FetchMode.SUBSELECT)
	// @BatchSize(size = 1)
	@OrderBy("username")
	private List<Authorities> authorities = new ArrayList<Authorities>();

	@ManyToMany(cascade=CascadeType.ALL, mappedBy = "users")
	private List<Company> companys = new ArrayList<Company>();

	
	
	public List<Company> getCompanys() {
		return companys;
	}

	public void setUsers(ArrayList<Company> companys) {
		this.companys = companys;
	}

	public Users(String username, String password, Short enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.authorities = new ArrayList<Authorities>();
		this.companys = new ArrayList<Company>();
	}

	public Users() {
		this.authorities = new ArrayList<Authorities>();
		this.companys = new ArrayList<Company>();
	}

	public List<Authorities> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authorities> authorities) {
		this.authorities = authorities;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}

}

