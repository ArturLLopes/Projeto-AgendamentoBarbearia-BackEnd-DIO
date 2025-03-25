package br.com.projeto.projetobarbershop.controller.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;


/**
 * Requisição para atualizar as informações de um cliente existente.
 * A classe utiliza anotações de validação para garantir que os campos não sejam nulos e que o e-mail tenha um formato válido.
 */

public record UpdateClientRequest(
        @NotNull
        @JsonProperty("name")
        String name,
        @NotNull
        @Email
        @JsonProperty("email")
        String email,
        @NotNull
        @JsonProperty("phone")
        String phone
) {}