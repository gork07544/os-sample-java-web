package com.tvs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "applications")
public class Applications {

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;

	@ManyToOne
	@JoinColumn(name = "CompanyId")
	private Company company;
	
	@Column(name = "ApplicationName")
	private String ApplicationName;

	@ManyToOne(optional = false)
	@JoinColumn(name = "Creator")
	private Users user;

	Applications() {
	}

	public Applications( Company company, String applicationName) {
		this.company = company;
		this.ApplicationName = applicationName;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		this.Id = id;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	
	public String getApplicationName() {
		return ApplicationName;
	}

	public void setApplicationName(String applicationName) {
		this.ApplicationName = applicationName;
	}
	
}
