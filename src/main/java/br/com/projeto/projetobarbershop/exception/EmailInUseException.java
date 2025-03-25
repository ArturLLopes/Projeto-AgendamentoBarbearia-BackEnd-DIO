package br.com.projeto.projetobarbershop.exception;



public class EmailInUseException extends RuntimeException {

    public EmailInUseException(String message) {
        super(message);
    }

}