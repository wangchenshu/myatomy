package com.myatomy.myatomy.service;

import com.myatomy.myatomy.model.Products;
import com.myatomy.myatomy.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    public List<Products> findAll() {
        return productsRepository.findAll();
    }

    public Optional<Products> findById(int id) {
        return productsRepository.findById(id);
    }

    public Optional<Products> findByName(String name) {
        return productsRepository.findByName(name);
    }

    public Optional<Products> findByNameContaining(String name) {
        return productsRepository.findByNameContaining(name);
    }
}
