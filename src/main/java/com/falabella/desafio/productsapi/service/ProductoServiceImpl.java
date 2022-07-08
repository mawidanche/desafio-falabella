package com.falabella.desafio.productsapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.falabella.desafio.productsapi.exception.ExistProductException;
import com.falabella.desafio.productsapi.exception.NotFoundProductException;
import com.falabella.desafio.productsapi.exception.RequiredFieldException;
import com.falabella.desafio.productsapi.model.Producto;
import com.falabella.desafio.productsapi.repository.IProductoRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements IProductoService{
    
    private final @NonNull IProductoRepository productoRepository;
    private static final String PRODUCTO_NO_EXISTE = "producto no existe";
    @Override
    public Producto addProducto(Producto producto) throws RequiredFieldException, ExistProductException {
        List<String> emptyFieldsRequired = new ArrayList<>();
         
        if(producto.getSku() == null){
            emptyFieldsRequired.add("No se ingreso SKU");
        }
        if(producto.getName() == null){
             emptyFieldsRequired.add("No se ingreso Name");

        }
        if(producto.getBrand() == null){
            emptyFieldsRequired.add("No se ingreso Brand");
        }
        if(producto.getPrice() == null){
            emptyFieldsRequired.add("No se ingreso Price");
        }
        if(producto.getPrincipalImage() == null){
            emptyFieldsRequired.add("No se ingreso Principal Image");
        }
        if(!emptyFieldsRequired.isEmpty()){
            throw new RequiredFieldException(emptyFieldsRequired);
        }


        Producto productOld =  productoRepository.findBySku(producto.getSku());
        if(productOld != null){
            throw new ExistProductException("Ya existe un producto con el SKU ["+producto.getSku()+"]");
        }
         return productoRepository.save(producto);
    }

    @Override
    public List<Producto> getAllProductos() throws NotFoundProductException {
        List<Producto> productos  = productoRepository.findAll();
        if(productos.isEmpty()){
            throw new NotFoundProductException("no se encontraron productos");
         }         
        return productos;
    }

    @Override
    public Producto getProductoBySku(String sku) throws NotFoundProductException {
        Producto producto =  productoRepository.findBySku(sku);
        if(producto == null){
            throw new NotFoundProductException(PRODUCTO_NO_EXISTE);
         }
         return producto;
    }

    @Override
    public void removeProducto(String uuid) throws NotFoundProductException {
        if(productoRepository.existsById(uuid)){
            productoRepository.deleteById(uuid);  
        }else{
            throw new NotFoundProductException(PRODUCTO_NO_EXISTE);
        }      
    }

    @Override
    public Producto updateProducto(Producto producto) throws NotFoundProductException {
        Producto old = productoRepository.findBySku(producto.getSku());
        if(old == null){ 
            throw new NotFoundProductException(PRODUCTO_NO_EXISTE);

        }
        return productoRepository.save(producto);
    }

 
    
}
