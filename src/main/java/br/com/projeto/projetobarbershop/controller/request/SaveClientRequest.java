package br.com.projeto.projetobarbershop.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

/**
 * Requisição para salvar um novo cliente.
 * Utiliza a anotação @NotNull para garantir que os campos não sejam nulos
 * e @Email para validar o formato do e-mail.
 */
public record SaveClientRequest(

        // Nome do cliente - não pode ser nulo
        @NotNull
        @JsonProperty("name")
        String name,

        // E-mail do cliente - deve ser válido e não pode ser nulo
        @NotNull
        @Email
        @JsonProperty("email")
        String email,

        // Telefone do cliente - não pode ser nulo
        @NotNull
        @JsonProperty("phone")
        String phone
) {}
