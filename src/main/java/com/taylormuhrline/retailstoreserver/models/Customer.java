package com.taylormuhrline.retailstoreserver.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="customers")
public class Customer {
	
	// FIELDS ------------------------------------------- // 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String fname;
	
	private String lname;
	
	@OneToMany(mappedBy="customer", cascade=CascadeType.ALL)
	private Set<Product> purchases = new HashSet<>();
	
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
	
	
	
	// CONSTRUCTORS ------------------------------------------ //
	
	

	public Customer(String fname, String lname) {
		super();
		this.fname = fname;
		this.lname = lname;
	}

	public Customer() {
		super();
	}
	
	public Customer(Long id) {
		super();
		this.id = id;
	}

	// GETTERS AND SETTERS ----------------------------------- //

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

	public Set<Product> getPurchases() {
		return purchases;
	}

	public void setPurchases(Set<Product> purchases) {
		this.purchases = purchases;
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
