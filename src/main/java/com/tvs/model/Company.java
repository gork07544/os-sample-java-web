package com.tvs.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "company")
public class Company {

	@Id
	@Column(name = "CompanyId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int CompanyId;

	@Column(name = "Name")
	private String Name;
	
	@Column(name = "Address")
	private String Address;

	@OneToMany(mappedBy = "company", targetEntity = Applications.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	// @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	// @JoinColumn(name="username" )
	@Fetch(FetchMode.SUBSELECT)
	// @BatchSize(size = 1)
	//@OrderBy("username")
	private List<Applications> applications = new ArrayList<Applications>();

	@ManyToMany(cascade = CascadeType.ALL, targetEntity = Users.class)
	@JoinTable(name = "user_company", 
				joinColumns = @JoinColumn(name = "CompanyId") ,
				inverseJoinColumns = @JoinColumn(name = "username") )
	private List<Users> users = new ArrayList<Users>();

	public List<Users> getUsers() {
		return users;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}
	public void setUsers(ArrayList<Users> users) {
		this.users = users;
	}

	public Company() {
		this.applications = new ArrayList<Applications>();
		this.users = new ArrayList<Users>();
	}
	public Company(String name) {
		this.Name = name;
		this.applications = new ArrayList<Applications>();
		this.users = new ArrayList<Users>();
	}
	public Company(String name, String address ) {
		this.Name = name;
		this.Address = address;		
		this.applications = new ArrayList<Applications>();
		this.users = new ArrayList<Users>();
	}
	
	public Company(String name, ArrayList<Applications> applications, ArrayList<Users> users) {
		this.Name = name;
		this.applications = applications;
		this.users = users;
	}

	public List<Applications> getApplications() {
		return applications;
	}

	public void setApplications(List<Applications> applications) {
		this.applications = applications;
	}
	
	public int getCompanyId() {
		return CompanyId;
	}

	public void setCompanyId(int companyId) {
		CompanyId = companyId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}	
	
}
