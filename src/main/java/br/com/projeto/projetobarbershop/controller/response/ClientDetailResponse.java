package br.com.projeto.projetobarbershop.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Resposta com os detalhes de um cliente.
 * Esta classe é usada para retornar informações detalhadas sobre um cliente após uma requisição.
 */

public record ClientDetailResponse(
        @JsonProperty("id")
        Long id,
        @JsonProperty("name")
        String name,
        @JsonProperty("email")
        String email,
        @JsonProperty("phone")
        String phone
) {}