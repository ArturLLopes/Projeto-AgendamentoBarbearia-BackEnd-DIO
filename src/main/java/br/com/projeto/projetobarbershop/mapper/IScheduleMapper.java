package br.com.projeto.projetobarbershop.mapper;

import br.com.projeto.projetobarbershop.controller.request.SaveScheduleRequest;
import br.com.projeto.projetobarbershop.controller.response.ClientScheduleAppointmentResponse;
import br.com.projeto.projetobarbershop.controller.response.SaveScheduleResponse;
import br.com.projeto.projetobarbershop.controller.response.ScheduleAppointmentMonthResponse;
import br.com.projeto.projetobarbershop.entity.ScheduleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * Interface Mapper para conversões entre objetos de solicitação, resposta e entidade de agendamento.
 * Utiliza o MapStruct para mapeamento automático de dados.
 */
@Mapper(componentModel = SPRING)
public interface IScheduleMapper {

    /**
     * Mapeia a solicitação de agendamento para a entidade de agendamento.
     *
     * @param request A solicitação de agendamento com os dados do cliente e horário.
     * @return A entidade `ScheduleEntity` a ser salva no banco de dados.
     */
    @Mapping(target = "id", ignore = true) // O id é ignorado, pois será gerado automaticamente
    @Mapping(target = "client.id", source = "clientId") // O id do cliente é mapeado da solicitação para a entidade
    ScheduleEntity toEntity(final SaveScheduleRequest request);

    /**
     * Mapeia a entidade de agendamento para a resposta de agendamento criado.
     *
     * @param entity A entidade `ScheduleEntity` após ser salva no banco de dados.
     * @return A resposta que será retornada ao cliente após a criação do agendamento.
     */
    @Mapping(target = "clientId", source = "client.id") // O id do cliente é extraído da entidade para a resposta
    SaveScheduleResponse toSaveResponse(final ScheduleEntity entity);

    /**
     * Mapeia a lista de entidades de agendamento para uma resposta de agendamentos do mês.
     *
     * @param year O ano do agendamento.
     * @param month O mês do agendamento.
     * @param entities A lista de entidades de agendamento para o mês especificado.
     * @return A resposta contendo os agendamentos do mês.
     */
    @Mapping(target = "scheduledAppointments", expression = "java(toClientMonthResponse(entities))") // Converte a lista de agendamentos em respostas individuais
    ScheduleAppointmentMonthResponse toMonthResponse(final int year, final int month, final List<ScheduleEntity> entities);

    /**
     * Converte uma lista de entidades de agendamento em uma lista de respostas de agendamento por cliente.
     *
     * @param entities A lista de entidades de agendamento.
     * @return Uma lista de respostas, contendo agendamentos de cada cliente no mês.
     */
    List<ClientScheduleAppointmentResponse> toClientMonthResponse(final List<ScheduleEntity> entities);

    /**
     * Mapeia um agendamento específico para a resposta de agendamento do cliente.
     *
     * @param entity A entidade de agendamento.
     * @return A resposta contendo os dados do agendamento do cliente.
     */
    @Mapping(target = "clientId", source = "client.id") // O id do cliente é extraído da entidade
    @Mapping(target = "clientName", source = "client.name") // O nome do cliente é extraído da entidade
    @Mapping(target = "day", expression = "java(entity.getStartAt().getDayOfMonth())") // Extraímos o dia do mês do agendamento
    ClientScheduleAppointmentResponse toClientMonthResponse(final ScheduleEntity entity);
}
