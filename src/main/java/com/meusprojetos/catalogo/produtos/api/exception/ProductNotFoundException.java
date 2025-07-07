package com.meusprojetos.catalogo.produtos.api.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String message) { super(message); }
}
