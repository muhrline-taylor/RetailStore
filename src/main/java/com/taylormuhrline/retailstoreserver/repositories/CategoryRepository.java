package com.taylormuhrline.retailstoreserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taylormuhrline.retailstoreserver.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}