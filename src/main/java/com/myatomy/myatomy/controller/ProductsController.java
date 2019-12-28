package com.myatomy.myatomy.controller;

import com.myatomy.myatomy.model.Products;
import com.myatomy.myatomy.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @GetMapping("/products")
    public ResponseEntity<List<Products>> showAll() {
        return new ResponseEntity<>(productsService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Products> showById(@PathVariable(value = "id") int id) {
        return productsService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().body(null));
    }

    @GetMapping("/products/name/{name}")
    public ResponseEntity<Products> showByName(@PathVariable(value = "name") String name) {
        return productsService.findByName(name)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().body(null));
    }
}
