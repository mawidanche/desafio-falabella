package com.falabella.desafio.productsapi.exception;

public class NotFoundProductException extends Exception{

 
    private static final long serialVersionUID = 6481245075311725778L;

    public NotFoundProductException(String message) {
        super(message);
    }
 
    
    
}
