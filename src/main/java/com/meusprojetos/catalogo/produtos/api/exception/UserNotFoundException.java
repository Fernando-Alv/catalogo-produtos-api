package com.meusprojetos.catalogo.produtos.api.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) { super(message); }
}
