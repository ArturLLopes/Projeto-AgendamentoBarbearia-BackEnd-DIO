package br.com.projeto.projetobarbershop.exceptionhandler;

import br.com.projeto.projetobarbershop.controller.response.ProblemResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Classe que trata exceções não capturadas de forma centralizada.
 * Essa classe aplica a interceptação de exceções lançadas em qualquer parte do sistema.
 * Ela também cria uma resposta padronizada (ProblemResponse) para erros internos.
 */
@Log4j2
@ControllerAdvice
public class BarberShopExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Método que captura exceções não tratadas e retorna uma resposta com informações do erro.
     *
     * @param ex       Exceção lançada.
     * @param request  A requisição HTTP que gerou a exceção.
     * @return         ResponseEntity contendo os detalhes do erro.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUncaught(final Exception ex, final WebRequest request){
        // Logando o erro para monitoramento
        log.error("handleUncaught: ", ex);

        // Definindo o status HTTP para o erro (500 - Internal Server Error)
        var status = INTERNAL_SERVER_ERROR;

        // Criando uma resposta estruturada com o status, timestamp e a mensagem de erro
        var response = ProblemResponse.builder()
                .status(status.value()) // Status do erro
                .timestamp(OffsetDateTime.now()) // Timestamp do erro
                .message(ex.getMessage()) // Mensagem da exceção
                .build();

        // Retorna a resposta com os detalhes do erro
        return handleExceptionInternal(ex, response, new HttpHeaders(), status, request);
    }

}
