package br.com.projeto.projetobarbershop.controller;

import br.com.projeto.projetobarbershop.controller.request.SaveScheduleRequest;
import br.com.projeto.projetobarbershop.controller.response.SaveScheduleResponse;
import br.com.projeto.projetobarbershop.controller.response.ScheduleAppointmentMonthResponse;
import br.com.projeto.projetobarbershop.mapper.IScheduleMapper;
import br.com.projeto.projetobarbershop.service.IScheduleService;
import br.com.projeto.projetobarbershop.service.query.IScheduleQueryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;

import static java.time.ZoneOffset.UTC;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

/**
 * Controlador de agendamentos. Gerencia as requisições HTTP para operações relacionadas aos agendamentos.
 */
@RestController
@RequestMapping("schedules")
@AllArgsConstructor
public class ScheduleController {

    private final IScheduleService service;          // Serviço para operações de agendamentos
    private final IScheduleQueryService queryService; // Serviço para consultas (queries) de agendamentos
    private final IScheduleMapper mapper;            // Mapper para converter entre objetos de requisição, resposta e entidades

    /**
     * Cria um novo agendamento.
     * @param request Dados do agendamento a ser criado.
     * @return Resposta com os dados do agendamento salvo.
     */
    @PostMapping
    @ResponseStatus(CREATED)
    SaveScheduleResponse save(@RequestBody @Valid SaveScheduleRequest request){
        var entity = mapper.toEntity(request);    // Mapeia a requisição para uma entidade de agendamento
        service.save(entity);                      // Salva o agendamento
        return mapper.toSaveResponse(entity);     // Retorna a resposta com os dados do agendamento salvo
    }

    /**
     * Deleta um agendamento.
     * @param id Identificador do agendamento a ser deletado.
     */
    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    void delete(@PathVariable final long id){
        service.delete(id); // Deleta o agendamento pelo ID
    }

    /**
     * Lista os agendamentos de um determinado mês.
     * @param year Ano do mês a ser listado.
     * @param month Mês do ano a ser listado.
     * @return Resposta com os agendamentos do mês especificado.
     */
    @GetMapping("{year}/{month}")
    ScheduleAppointmentMonthResponse listMonth(@PathVariable final int year, @PathVariable final int month){
        // Cria um objeto YearMonth para representar o ano e mês solicitados
        var yearMonth = YearMonth.of(year, month);

        // Define o início e fim do mês para filtrar os agendamentos
        var startAt = yearMonth.atDay(1)
                .atTime(0, 0, 0, 0)
                .atOffset(UTC); // Início do mês
        var endAt = yearMonth.atEndOfMonth()
                .atTime(23, 59, 59, 999_999_999)
                .atOffset(UTC); // Fim do mês

        // Obtém os agendamentos dentro do intervalo de tempo definido
        var entities = queryService.findInMonth(startAt, endAt);

        // Retorna a resposta com os agendamentos para o mês
        return mapper.toMonthResponse(year, month, entities);
    }
}
