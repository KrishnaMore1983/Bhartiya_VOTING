package com.voatingsys.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Voter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @NotBlank(message = "Name Cannot be Empty")
    @Size(min=4,max=8,message="Name should have 4 and 8 chracters")
    @Pattern(regexp = "[A-Z,a-z]+$", message = "only character allowed")
    private String Name;
    private String address;
    private Date DOB;
    private String gender;
    private String username;
    private String password;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDOB() {
		return DOB;
	}
	public void setDOB(Date dOB) {
		DOB = dOB;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
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
	public Voter(int id,
			@NotBlank(message = "Name Cannot be Empty") @Size(min = 4, max = 8, message = "Name should have 4 and 8 chracters") @Pattern(regexp = "[A-Z,a-z]+$", message = "only character allowed") String name,
			String address, Date dOB, String gender, String username, String password) {
		super();
		this.id = id;
		Name = name;
		this.address = address;
		DOB = dOB;
		this.gender = gender;
		this.username = username;
		this.password = password;
	}

	public Voter() {
		
		this.id=0;
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Voter [id=" + id + ", Name=" + Name + ", address=" + address + ", DOB=" + DOB + ", gender=" + gender
				+ ", username=" + username + ", password=" + password + "]";
	}
    
    
    
    
    

}
  