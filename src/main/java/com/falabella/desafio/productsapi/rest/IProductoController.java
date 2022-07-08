package com.falabella.desafio.productsapi.rest;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.falabella.desafio.productsapi.model.Producto;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@Api(tags = "Producto" )
@RequestMapping("/producto")
public interface IProductoController {
    
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
     
    @Operation(summary = "Listado de productos", description = "Obtiene un listado con todos los productos almacenados", 
                responses = {
        @ApiResponse(responseCode = "200", description = "", content = @Content(schema = @Schema(implementation = Producto.class))),
        @ApiResponse(responseCode = "204", description = "", content = @Content),
    })
    public ResponseEntity<List<Producto>> getAllProductos();
    
    @PostMapping(value = "/")
    @Operation(summary = "Ingresa producto", description = "Ingresa un nuevo producto", 
    responses = {
        @ApiResponse(responseCode = "201", description = "", content = @Content(schema = @Schema(implementation = Producto.class))),
    })   
    public ResponseEntity<Producto> addProducto(@RequestBody Producto producto);

    @GetMapping(value = "/{sku}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/")
    @Operation(summary = "Obtener producto por SKU", description = "Obtiene producto según el SKU enviado", 
    responses = {
        @ApiResponse(responseCode = "200", description = "", content = @Content(schema = @Schema(implementation = Producto.class))),
        @ApiResponse(responseCode = "204", description = "", content = @Content),
    })   
    public ResponseEntity<Producto> getProductoBySku(
        @Parameter(description = "Sku del producto", example = "FAL-1000000", required = true) @PathVariable String sku);

    @PutMapping(value = "/")
    @Operation(summary = "Actualizar producto", description = "Actualiza registro del producto enviado", 
    responses = {
        @ApiResponse(responseCode = "200", description = "", content = @Content(schema = @Schema(implementation = Producto.class))),
    }) 
    public ResponseEntity<Producto> updateProducto( @RequestBody Producto producto );

    
    @DeleteMapping(value = "/{uuid}")   
    @Operation(summary = "Eliminar producto", description = "Elimina producto según el SKU enviado", 
    responses = {
        @ApiResponse(responseCode = "204", description = "", content = @Content),
        @ApiResponse(responseCode = "200", description = "", content = @Content),
    }) 
    public ResponseEntity<Object> removeProductoByUuid(
        @Parameter(description = "uuid del producto", example = "xxxxxxxx-xxxx-Mxxx-Nxxx-xxxxxxxxxxxx", required = true) @PathVariable String uuid); 
}
