package br.com.projeto.projetobarbershop.exception;



public class PhoneInUseException extends RuntimeException {

    public PhoneInUseException(String message) {
        super(message);
    }

}