package com.taylormuhrline.retailstoreserver.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="products")
public class Product {
	
	// FIELDS --------------------- // 
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Min(value=2)
	private String name;
	
	private String category;
	
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer=5, fraction=2)
	private BigDecimal price;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="store_id")
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	private Store store;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="customer_id")
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	private Customer customer;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	@JoinTable(name="products_categories",
				joinColumns= {@JoinColumn(name="product_id")},
				inverseJoinColumns = {@JoinColumn(name="category_id")}
			)
	Set<Category> categories;
	
	
	
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    // CONSTRUCTORS ----------------------------------------------- //
    
    public Product() {
		super();
	}
    
	public Product(@Min(2) String name, String category,
			@DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 5, fraction = 2) BigDecimal price,
			Store store) {
		super();

		this.name = name;
		this.category = category;
		this.price = price;
		this.store = store;
		this.categories = new HashSet<Category>();
	}

	public Product(@Min(2) String name, String category,
			@DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 5, fraction = 2) BigDecimal price) {
		super();
		this.name = name;
		this.category = category;
		this.price = price;
	}
	
	public Product(@Min(2) String name, String category,
			@DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 5, fraction = 2) BigDecimal price,
			Store store, Category thisCategory) {
		super();
		
		Set<Category> categories = new HashSet<Category>();
		categories.add(thisCategory);
		
		this.name = name;
		this.category = category;
		this.price = price;
		this.store = store;
		this.categories = categories;
	}
	
	

	// GETTERS AND SETTERS --------------------------------------- //
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
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
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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
