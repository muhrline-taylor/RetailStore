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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.taylormuhrline.retailstoreserver.models.Store;
import com.taylormuhrline.retailstoreserver.repositories.EmployeeRepository;
import com.taylormuhrline.retailstoreserver.repositories.StoreRepository;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping(value="/api/v1/stores")
public class StoreController {
	
	@Autowired
	StoreRepository storeRepository;
	
	@Autowired 
	EmployeeRepository employeeRepository;
	
	
	// CALLS ------------------------------- //
	
	// GET ALL
	@GetMapping("/")
	public ResponseEntity<Page<Store>> getAllStores(Pageable pageable){
		return ResponseEntity.ok(storeRepository.findAll(pageable));
	}
	
	// GET BY ID
	@GetMapping("/{id}")
	public ResponseEntity<Store> getById(@PathVariable Long id){
		Optional<Store> optionalStore = storeRepository.findById(id);
		if(!optionalStore.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		return ResponseEntity.ok(optionalStore.get());
	}
	
	// GET BY ID, WHERE ID IS AN OBJECT
	
	// CREATE
	@PostMapping("/new")
    public ResponseEntity<Store> create(
    			@RequestBody Store store
    		) {
		
        Store savedStore = storeRepository.save(store);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(savedStore.getId()).toUri();

        return ResponseEntity.created(location).body(savedStore);
    }
	
	// DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<Store> deleteStore(@PathVariable Long id){
		Optional<Store> optionalStore = storeRepository.findById(id);
		if(!optionalStore.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		storeRepository.delete(optionalStore.get());
        return ResponseEntity.noContent().build();
	}
	
	// ADD EMPLOYEE BY STORE ID
	@PutMapping("/{id}")
	public ResponseEntity<Store> updateById(
				@PathVariable Long store_id,
				@RequestParam("employee_id")Long employee_id
			){
		
		return ResponseEntity.noContent().build();
	}
	

}
