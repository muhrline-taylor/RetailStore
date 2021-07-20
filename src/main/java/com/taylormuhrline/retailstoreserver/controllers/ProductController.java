package com.taylormuhrline.retailstoreserver.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.taylormuhrline.retailstoreserver.models.Category;
import com.taylormuhrline.retailstoreserver.models.Customer;
import com.taylormuhrline.retailstoreserver.models.Product;
import com.taylormuhrline.retailstoreserver.models.Store;
import com.taylormuhrline.retailstoreserver.repositories.CategoryRepository;
import com.taylormuhrline.retailstoreserver.repositories.CustomerRepository;
import com.taylormuhrline.retailstoreserver.repositories.ProductRepository;
import com.taylormuhrline.retailstoreserver.repositories.StoreRepository;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
	
	@Autowired 
	ProductRepository productRepository;
	
	@Autowired
	StoreRepository storeRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired 
	CategoryRepository categoryRepository;
	
	
	// ROUTES ---------------------------------------------- // 
	
		// GET ALL
		@GetMapping("/")
		public ResponseEntity<Page<Product>> getAllProducts(Pageable pageable){
			return ResponseEntity.ok(productRepository.findAll(pageable));
		} 
		
		// GET BY ID
		@GetMapping("/{id}")
		public ResponseEntity<Product> getProductById(@PathVariable Long id){
			Optional<Product> optionalProduct = productRepository.findById(id);
			if(!optionalProduct.isPresent()) {
				return ResponseEntity.unprocessableEntity().build();
			} else {
				return ResponseEntity.ok(optionalProduct.get());
			}
		}
		
		// CREATE
		@PostMapping("/new/{store_id}")
		public ResponseEntity<Product> createProduct(
					@RequestBody Product product,
					@PathVariable Long store_id
				){
			
			System.out.println("into createProduct()");
			System.out.println(product.getName());
			Optional<Store> optionalStore = storeRepository.findById(store_id);
			System.out.println(optionalStore);
			if(!optionalStore.isPresent()) {
				return ResponseEntity.unprocessableEntity().build();
			}

			
			Store store = optionalStore.get();
			Product newProduct = new Product(
						product.getName(),
						product.getCategory(),
						product.getPrice(),
						store
					);
			
			System.out.println("created newProduct");
			
			Product savedProduct = productRepository.save(newProduct);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			            .buildAndExpand(savedProduct.getId()).toUri();
			
			return ResponseEntity.created(location).body(savedProduct);

		}
		
		// CREATE TESTING
//		@PostMapping("/new/testing")
//		public ResponseEntity<Product> createProductTesting(@RequestParam("category")String category){
//			// block
//			
//						Category foundCategory;
//						List<Category> categories = categoryRepository.findAll();
//						for(int i=0;i<categories.size();i++) {
//							if(category == categories.get(i).getName()) {
//								foundCategory.equals(categories.get(i));
//							}
//						}
//						
//						if(foundCategory.getId() != null) {
//							System.out.println("category found!");
//						} else {
//							System.out.println("category not found!");
//						}
//						
//						return ResponseEntity.noContent().build();
//						
//						// end block
//			
//		}
		
		// DELETE BY ID
		@DeleteMapping("/{id}")
		public ResponseEntity<Product> deleteProductById(@PathVariable Long id){
			Optional<Product> optionalProduct = productRepository.findById(id);
			
			if(!optionalProduct.isPresent()) {
				return ResponseEntity.unprocessableEntity().build();
			} else {
				productRepository.delete(optionalProduct.get());
				return ResponseEntity.noContent().build();
			}
		}
		
		// BUY PRODUCT
		@PostMapping("/{product_id}/buy")
		public ResponseEntity<Customer> buyProduct(
					@RequestBody Customer rawCustomer,
					@PathVariable Long product_id
				) {
			System.out.println("into buyProduct()");
			System.out.println(rawCustomer.getId());
			Optional<Product> optionalProduct = productRepository.findById(product_id);
			Optional<Customer> optionalCustomer = customerRepository.findById(rawCustomer.getId());
			if(!optionalCustomer.isPresent()) {
				System.out.println("customer not found");
				return ResponseEntity.unprocessableEntity().build();
			}
			if(!optionalProduct.isPresent()) {
				System.out.println("product not found");
				return ResponseEntity.unprocessableEntity().build();
			}
			
			Customer customer = optionalCustomer.get();
			Product product = optionalProduct.get();
			
			Set<Product> allPurchases = customer.getPurchases();
			allPurchases.add(product);
			customer.setPurchases(allPurchases);
			product.setCustomer(customer);
			
			System.out.println("Purchases set");
			System.out.println(customer.getPurchases());
			
			customerRepository.save(customer);
			productRepository.save(product);
			
			
			return ResponseEntity.noContent().build();
		}
	
	
	
	

}
