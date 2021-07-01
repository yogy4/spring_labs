package com.example.labs.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GeneratorType;

import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "cloud not empty")
    private String name;

    @Column(name = "size")
    private String size;

    @Column(name = "price")
    @NotEmpty(message = "cloud not empty")
    private Integer price;

    @Column(name = "quantity")
    private Integer quantity;
    


    
}
