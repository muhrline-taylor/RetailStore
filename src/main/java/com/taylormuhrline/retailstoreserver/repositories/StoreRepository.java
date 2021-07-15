package com.taylormuhrline.retailstoreserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taylormuhrline.retailstoreserver.models.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long>{

}
