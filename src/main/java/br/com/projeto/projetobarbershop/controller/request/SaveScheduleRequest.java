package br.com.projeto.projetobarbershop.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

/**
 * Requisição para salvar um novo agendamento.
 * A classe usa anotações de validação para garantir que os campos não sejam nulos.
 */
public record SaveScheduleRequest(

        // Data e hora de início do agendamento - não pode ser nula
        @NotNull
        @JsonProperty("startAt")
        OffsetDateTime startAt,

        // Data e hora de término do agendamento - não pode ser nula
        @NotNull
        @JsonProperty("endAt")
        OffsetDateTime endAt,

        // ID do cliente associado ao agendamento - não pode ser nulo
        @NotNull
        @JsonProperty("clientId")
        Long clientId
) {}
