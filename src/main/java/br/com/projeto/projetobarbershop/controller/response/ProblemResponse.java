package br.com.projeto.projetobarbershop.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.OffsetDateTime;

/**
 * Resposta para descrever problemas (erros) durante a execução de uma requisição.
 * Esta classe é usada para retornar informações detalhadas sobre o erro ocorrido.
 */
public record ProblemResponse(

        // Código de status HTTP (por exemplo, 400 para erro de requisição)
        @JsonProperty("status")
        Integer status,

        // Data e hora em que o problema ocorreu, com fuso horário
        @JsonProperty("timestamp")
        OffsetDateTime timestamp,

        // Mensagem descrevendo o problema
        @JsonProperty("message")
        String message
) {

    /**
     * Construtor da classe que permite criar objetos de forma flexível utilizando o padrão Builder.
     */
    @Builder(toBuilder = true)
    public ProblemResponse {}
}
