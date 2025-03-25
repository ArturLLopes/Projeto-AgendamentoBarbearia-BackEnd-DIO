package br.com.projeto.projetobarbershop.exception;



public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

}