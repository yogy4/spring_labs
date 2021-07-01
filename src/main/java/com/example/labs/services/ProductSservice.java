package com.example.labs.services;

import java.util.List;

import com.example.labs.models.Product;
import com.example.labs.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("productService")
public class ProductSservice {

    @Autowired
    private ProductRepository productRepository;

    public Product findProductByName(String name){
        return productRepository.findByName(name);
    }

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(Product product, Long id){
        Product updateProduct = productRepository.findById(id).orElse(null);
        if(updateProduct != null){
            updateProduct.setName(product.getName());
            updateProduct.setPrice(product.getPrice());
            updateProduct.setQuantity(product.getQuantity());
        }
        final Product sProduct = productRepository.save(updateProduct);
        return sProduct;
    }

    public Boolean deleteProduct(Long id){
        Product dProduct = productRepository.findById(id).orElse(null);
        if(dProduct != null){
            productRepository.delete(dProduct);
            return true;
        }
        return false;
    }

    
}
