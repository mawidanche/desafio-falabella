package com.falabella.desafio.productsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.falabella.desafio.productsapi.model.Imagen;

public interface IImageRepository extends JpaRepository<Imagen, String>{
    
}
