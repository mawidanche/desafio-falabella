package com.falabella.desafio.productsapi.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.falabella.desafio.productsapi.exception.ExistProductException;
import com.falabella.desafio.productsapi.exception.NotFoundProductException;
import com.falabella.desafio.productsapi.exception.RequiredFieldException;
import com.falabella.desafio.productsapi.model.Producto;
import com.falabella.desafio.productsapi.service.IProductoService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductoControllerImpl implements IProductoController {

    private final @NonNull IProductoService productoService;

    @Override
    public ResponseEntity<List<Producto>> getAllProductos() {
        try {
            return new ResponseEntity<>(productoService.getAllProductos(),HttpStatus.OK);
        } catch(NotFoundProductException nfp){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<Producto> addProducto(Producto producto) {
        try {
            return new ResponseEntity<>(productoService.addProducto(producto),HttpStatus.CREATED);
        } catch (ExistProductException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch(RequiredFieldException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<Producto> getProductoBySku(String sku) {
        try {
            return new ResponseEntity<>(productoService.getProductoBySku(sku),HttpStatus.OK);
        }  catch(NotFoundProductException nfp){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<Producto> updateProducto(Producto producto) {
        try {
            return new ResponseEntity<>(productoService.updateProducto(producto) ,HttpStatus.OK);
        }catch(NotFoundProductException nfp){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
      
    }

    @Override
    public ResponseEntity<Object> removeProductoByUuid(String uuid) {
        try {
            productoService.removeProducto(uuid);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch(NotFoundProductException nfp){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
}
