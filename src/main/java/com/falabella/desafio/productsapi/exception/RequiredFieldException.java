package com.falabella.desafio.productsapi.exception;

import java.util.List;

public class RequiredFieldException extends Exception{

 
    private static final long serialVersionUID = 8240257795290507257L;

    public RequiredFieldException(String message) {
        super(message);
    }

    public RequiredFieldException(List<String> message) {

        super(message.toString());
    }
 

 
}
