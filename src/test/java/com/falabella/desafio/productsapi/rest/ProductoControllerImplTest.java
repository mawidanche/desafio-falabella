package com.falabella.desafio.productsapi.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import com.falabella.desafio.productsapi.model.Imagen;
import com.falabella.desafio.productsapi.model.Producto;
import com.falabella.desafio.productsapi.repository.IProductoRepository;
import com.falabella.desafio.productsapi.service.ProductoServiceImpl;

public class ProductoControllerImplTest {

    IProductoRepository productoRepository = Mockito.mock(IProductoRepository.class);

    ProductoServiceImpl productoService = new ProductoServiceImpl(productoRepository);

    ProductoControllerImpl productoController = new ProductoControllerImpl(productoService);

    @BeforeEach
    void setUp() {
        Imagen mockImagenNewBalance = Imagen.builder()
                                        .uuid("cf92fd3e-fe74-11ec-b939-0242ac120002")
                                        .url("https://falabella.scene7.com/is/image/Falabella/881952283_1")
                                        .build();
        Imagen mockImagenJeepUno = Imagen.builder()
                                        .uuid("cf92ff0a-fe74-11ec-b939-0242ac120002")
                                        .url("https://falabella.scene7.com/is/image/Falabella/8406270_1")
                                        .build();     
        Imagen mockImagenJeepDos = Imagen.builder()
                                        .uuid("cf93000e-fe74-11ec-b939-0242ac120002")
                                        .url("https://falabella.scene7.com/is/image/Falabella/881952283_2")
                                        .build();  
        Imagen mockImagenBasement = Imagen.builder()
                                        .uuid("cf9300f4-fe74-11ec-b939-0242ac120002")
                                        .url("https://falabella.scene7.com/is/image/Falabella/881898502_1")
                                        .build();  
        List<Imagen> otherImageJeepList = (new ArrayList<>());          
        otherImageJeepList.add(mockImagenJeepDos);                              
        Producto productoNewBalance = Producto.builder()
                                        .uuid("cf9301c6-fe74-11ec-b939-0242ac120002")
                                        .brand("NEW BALANCE")
                                        .name("500 Zapatilla Urbana Mujer")
                                        .price(BigDecimal.valueOf(42990.00))
                                        .size("37")
                                        .sku("FAL-8406270")
                                        .principalImage(mockImagenNewBalance)
                                        .build();
        Producto productoJeep = Producto.builder()
                                        .uuid("cf9302a2-fe74-11ec-b939-0242ac120002")
                                        .brand("JEEP")
                                        .name("Bicicleta Baltoro Aro 29")
                                        .price(BigDecimal.valueOf(399990.00))
                                        .size("ST")
                                        .sku("FAL-881952283")
                                        .principalImage(mockImagenJeepUno)
                                        .otherImage(otherImageJeepList)
                                        .build();
        Producto productoBesament = Producto.builder()
                                        .uuid("cf9304d2-fe74-11ec-b939-0242ac120002")
                                        .brand("BASEMENT")
                                        .name("Camisa Manga Corta Hombre")
                                        .price(BigDecimal.valueOf(24990.00))
                                        .size("M")
                                        .sku("FAL-881898502")
                                        .principalImage(mockImagenBasement)
                                        .build();    
        List<Producto> productos = List.of(productoNewBalance, productoJeep, productoBesament);

        Mockito.when(productoRepository.findAll()).thenReturn(productos);
        Mockito.when(productoRepository.findBySku("FAL-8406270")).thenReturn(productoNewBalance);   
        Mockito.when(productoRepository.existsById("NOT-EXIST")).thenReturn(false);
        Mockito.when(productoRepository.findBySku("NOT-EXIST")).thenReturn(null);   

    }

    @Test
    void testGetAllProductos() {
        ResponseEntity<List<Producto>> response =  productoController.getAllProductos();

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertFalse(response.getBody().isEmpty());
        Mockito.when(productoRepository.findAll()).thenReturn(List.of());
        response =  productoController.getAllProductos();
        Assertions.assertEquals(204, response.getStatusCodeValue());
        Assertions.assertNull(response.getBody());
  
    }

    @Test
    void testGetProductoBySku() {
        ResponseEntity<Producto> response = productoController.getProductoBySku("FAL-8406270");
        Assertions.assertEquals(200, response.getStatusCodeValue());
        response = productoController.getProductoBySku("NOT-000000001");
        Assertions.assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void testRemoveProductoByUuid() {
        ResponseEntity<Object> response = productoController.removeProductoByUuid("cf9301c6-fe74-11ec-b939-0242ac120002");
        // Assertions.assertEquals(200, response.getStatusCodeValue());
        response = productoController.removeProductoByUuid("NOT-EXIST");
        Assertions.assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void testAddProducto() {
        Producto producto = Producto.builder().build(); 
        ResponseEntity<Producto> response = productoController.addProducto(producto);
        Assertions.assertEquals(400, response.getStatusCodeValue());
        producto = productoRepository.findBySku("FAL-8406270");
        response = productoController.addProducto(producto);
        Assertions.assertEquals(409, response.getStatusCodeValue());
    }

    @Test
    void testUpdateProducto() {
        Producto producto = productoRepository.findBySku("FAL-8406270");
        ResponseEntity<Producto> response = productoController.updateProducto(producto);
        Assertions.assertEquals(200, response.getStatusCodeValue());
        producto = Producto.builder().sku("NOT-EXIST"). build(); 
        response = productoController.updateProducto(producto);
        Assertions.assertEquals(204, response.getStatusCodeValue());

    }
}
