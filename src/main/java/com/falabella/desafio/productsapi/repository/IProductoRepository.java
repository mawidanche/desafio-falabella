package com.falabella.desafio.productsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.falabella.desafio.productsapi.model.Producto;


@Repository
public interface IProductoRepository extends JpaRepository<Producto, String>{
    
    public Producto findBySku(String sku);
}
