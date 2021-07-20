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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.taylormuhrline.retailstoreserver.models.Category;
import com.taylormuhrline.retailstoreserver.repositories.CategoryRepository;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
	
	// REPOS + SERVICES ------------------------------------ //

	@Autowired 
	CategoryRepository categoryRepository;
	
	
	
	
	// ROUTES ----------------------------------------------- //
	
	@GetMapping("/")
	public ResponseEntity<Page<Category>> getAllCategories(Pageable pageable){
		return ResponseEntity.ok(categoryRepository.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable Long id){
		
		Optional<Category> optionalCategory = categoryRepository.findById(id);
		if(!optionalCategory.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		} 
		return ResponseEntity.ok(optionalCategory.get());
	}
	
	@PostMapping("/new")
	public ResponseEntity<Category> createCategory(){
		
		Category newCategory = new Category("electronics");
		
		
		Category savedCategory = categoryRepository.save(newCategory);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
	            .buildAndExpand(savedCategory.getId()).toUri();
		
		return ResponseEntity.created(location).body(savedCategory);
	}
	
	@DeleteMapping("/{id}/delete")
	public void deleteCategory(@PathVariable Long id) {
		Optional<Category> optionalCategory = categoryRepository.findById(id);
		
		if(!optionalCategory.isPresent()) {
			
		} else {
			Category category = optionalCategory.get();
			categoryRepository.delete(category);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
