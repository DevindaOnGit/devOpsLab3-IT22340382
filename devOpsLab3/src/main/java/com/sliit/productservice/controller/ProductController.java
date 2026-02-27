package com.sliit.productservice.controller;

import com.sliit.productservice.entity.Product;
import com.sliit.productservice.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository repo;

    public ProductController(ProductRepository repo){
        this.repo = repo;
    }

    // GET: all products
    @GetMapping
    public List<Product> getAll(){
        return repo.findAll();
    }

    // POST - create product
    @PostMapping
    public Product save(@RequestBody Product product){
        return repo.save(product);
    }

    // GET - product by id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id){
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: delete by id
    @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id){
            if (!repo.existsById(id)) return ResponseEntity.notFound().build();
            repo.deleteById(id);
            return ResponseEntity.noContent().build();
    }



}



