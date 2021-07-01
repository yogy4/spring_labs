package com.example.labs.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.labs.models.Product;
import com.example.labs.services.ProductSservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    private ProductSservice productSservice;

    @GetMapping("/products")
    public List<Product> all(){
        return productSservice.getAllProduct();
    }
    
    @PostMapping("/product")
    public ResponseEntity<Product> create(@Valid @RequestBody Product product){
        return ResponseEntity.ok(productSservice.saveProduct(product));

    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> update(@Valid @RequestBody Product product, @PathVariable(value = "id") Long id){
        return ResponseEntity.ok(productSservice.updateProduct(product, id));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Map<String, String> response = new HashMap<String, String>();
        if(productSservice.deleteProduct(id)){
            response.put("status", "success");
            response.put("message", "Product deleted successfully");
            return ResponseEntity.ok(response); 
        }else{
            response.put("status", "error");
            response.put("message","something wrong");
            return ResponseEntity.status(500).body(response);
        }
    }
}
