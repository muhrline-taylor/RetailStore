package com.taylormuhrline.retailstoreserver.models;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

@Entity
@Table(name="employees")
public class Employee {
	
	// FIELDS
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String fname;
	
	@NotNull
	private String lname;
	
	private String gender;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="store_id")
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	private Store store;
	
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    // CONSTRUCTORS ------------------------------------ //
    
    public Employee() {
		super();
	}
    
	public Employee(String fname, String lname, Store store) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.store = store;
		this.gender = "Unspecified";
	}

	public Employee(String fname, String lname, String gender, Store store) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.gender = gender;
		this.store = store;
	}

	public Employee(String fname, String lname) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.gender = "Unspecified";
	}

	public Employee(String fname, String lname, String gender) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.gender = gender;
	}

	// GETTERS AND SETTERS --------------------------- //
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
	
    
    
    
    
    
    
	

}
