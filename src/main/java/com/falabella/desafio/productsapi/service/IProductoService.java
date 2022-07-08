package com.falabella.desafio.productsapi.service;

import java.util.List;

import com.falabella.desafio.productsapi.exception.ExistProductException;
import com.falabella.desafio.productsapi.exception.NotFoundProductException;
import com.falabella.desafio.productsapi.exception.RequiredFieldException;
import com.falabella.desafio.productsapi.model.Producto;

public interface IProductoService {
    
    public List<Producto> getAllProductos() throws NotFoundProductException;
    public Producto addProducto(Producto producto) throws RequiredFieldException, ExistProductException;
    public void removeProducto(String uuid) throws NotFoundProductException ;
    public Producto updateProducto(Producto producto) throws NotFoundProductException;
    public Producto getProductoBySku(String sku) throws NotFoundProductException ;
}
