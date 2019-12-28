package com.myatomy.myatomy.repository;

import com.myatomy.myatomy.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Products, Integer> {
    Optional<Products> findByName(String name);
    List<Products> findByNameContaining(String name);
}
