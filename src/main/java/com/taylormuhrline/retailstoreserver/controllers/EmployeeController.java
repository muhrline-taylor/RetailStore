package com.taylormuhrline.retailstoreserver.controllers;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.taylormuhrline.retailstoreserver.models.Customer;
import com.taylormuhrline.retailstoreserver.models.Employee;
import com.taylormuhrline.retailstoreserver.models.Store;
import com.taylormuhrline.retailstoreserver.repositories.CustomerRepository;
import com.taylormuhrline.retailstoreserver.repositories.EmployeeRepository;
import com.taylormuhrline.retailstoreserver.repositories.StoreRepository;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping(value="/api/v1/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	StoreRepository storeRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	
	
	// ROUTES --------------------------------------- //
	
	// GET ALL
	@GetMapping("/")
	public ResponseEntity<Page<Employee>> getAllEmployees(Pageable pageable){
		return ResponseEntity.ok(employeeRepository.findAll(pageable));
	}
	
	// GET BY ID
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		if(!optionalEmployee.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		} else {
			return ResponseEntity.ok(optionalEmployee.get());
		}
	}
	
	// CREATE
	@PostMapping(value="/new/{store_id}")
	public ResponseEntity<Employee> createEmployee(
				@RequestBody Employee rawEmployee,
				@PathVariable Long store_id
			){
		
		System.out.println("into createEmployee()");
		System.out.println(rawEmployee);
		System.out.println(rawEmployee.getFname());
		System.out.println(store_id);
		
		Optional<Store> optionalStore = storeRepository.findById(store_id);
		if(!optionalStore.isPresent()) {
			ResponseEntity.unprocessableEntity().build();
		}
		Store store = optionalStore.get();
		Employee newEmployee = new Employee(
					rawEmployee.getFname(),
					rawEmployee.getLname(),
					rawEmployee.getGender(),
					store
				);
		
		System.out.println("newEmployee created");
		System.out.println(newEmployee.getStore());
		
		Employee savedEmployee = employeeRepository.save(newEmployee);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		            .buildAndExpand(savedEmployee.getId()).toUri();
		
		Customer customer = new Customer(
					rawEmployee.getFname(),
					rawEmployee.getLname()
				);
		customerRepository.save(customer);
		
		return ResponseEntity.created(location).body(savedEmployee);
		
		
	
	}
	
	// DELETE BY ID
	@DeleteMapping("/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id){
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		if(!optionalEmployee.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		employeeRepository.delete(optionalEmployee.get());
		
		return ResponseEntity.noContent().build();
	}

}
