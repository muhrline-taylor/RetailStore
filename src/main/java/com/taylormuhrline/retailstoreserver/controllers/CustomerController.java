package com.taylormuhrline.retailstoreserver.controllers;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.taylormuhrline.retailstoreserver.models.Customer;
import com.taylormuhrline.retailstoreserver.repositories.CustomerRepository;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
	
	@Autowired 
	CustomerRepository customerRepository;
	
	
	// ROUTES --------------------------------------------- //
	
		// GET ALL
		@GetMapping("/")
		public ResponseEntity<Page<Customer>> getAllCustomers(Pageable pageable){
			return ResponseEntity.ok(customerRepository.findAll(pageable));
		} 
		
		// GET BY ID
		@GetMapping("/{id}")
		public ResponseEntity<Customer> getCustomerById(
					@PathVariable Long id
				){
			Optional<Customer> optionalCustomer = customerRepository.findById(id);
			if(!optionalCustomer.isPresent()) {
				return ResponseEntity.unprocessableEntity().build();
			}
			return ResponseEntity.ok(optionalCustomer.get());
		}
		
		@PostMapping("/new")
		public ResponseEntity<Customer> createCustomer(
					@RequestBody Customer rawCustomer
				){
			System.out.println("into createCustomer()");
			System.out.println(rawCustomer.getFname());
			System.out.println(rawCustomer.getLname());
			
			Customer savedCustomer = customerRepository.save(rawCustomer);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			            .buildAndExpand(savedCustomer.getId()).toUri();
			
			return ResponseEntity.created(location).body(savedCustomer);
		}
	
	

}
