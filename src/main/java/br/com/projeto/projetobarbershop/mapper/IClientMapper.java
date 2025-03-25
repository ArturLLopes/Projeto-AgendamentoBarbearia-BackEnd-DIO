package br.com.projeto.projetobarbershop.mapper;

import br.com.projeto.projetobarbershop.controller.response.ClientDetailResponse;
import br.com.projeto.projetobarbershop.controller.response.ListClientResponse;
import br.com.projeto.projetobarbershop.controller.response.SaveClientResponse;
import br.com.projeto.projetobarbershop.entity.ClientEntity;

import br.com.projeto.projetobarbershop.controller.request.SaveClientRequest;
import br.com.projeto.projetobarbershop.controller.request.UpdateClientRequest;
import br.com.projeto.projetobarbershop.controller.response.UpdateClientResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * "Interface" Mapper para conversões entre objetos de solicitação, resposta e entidade do cliente.
 * Utiliza o MapStruct para mapeamento automático de dados.
 */

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface IClientMapper {

    /**
     * Mapeia a solicitação de cliente para a entidade.
     *
     * @param request A solicitação de criação de cliente.
     * @return A entidade `ClientEntity` a ser salva no banco de dados.
     */

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "schedules", ignore = true)
    ClientEntity toEntity(final SaveClientRequest request);

    /**
     * Mapeia a entidade do cliente para a resposta de criação.
     *
     * @param entity A entidade `ClientEntity` após ser salva no banco de dados.
     * @return A resposta que será retornada ao cliente após a criação.
     */

    SaveClientResponse toSaveResponse(final ClientEntity entity);

    /**
     * Mapeia a solicitação de atualização de cliente para a entidade, usando o 'id' para identificar o cliente.
     *
     * @param id O 'id' do cliente a ser atualizado.
     * @param request A solicitação de atualização do cliente.
     * @return A entidade 'ClientEntity' com os dados atualizados.
     */

    @Mapping(target = "schedules", ignore = true)
    ClientEntity toEntity(final long id, final UpdateClientRequest request);

    UpdateClientResponse toUpdateResponse(final ClientEntity entity);

    ClientDetailResponse toDetailResponse(final ClientEntity entity);

    /**
     * Mapeia uma lista de entidades de clientes para uma lista de respostas.
     *
     * @param entities A lista de entidades de cliente a serem retornadas.
     * @return Uma lista de respostas para o cliente.
     */

    List<ListClientResponse> toListResponse(final List<ClientEntity> entities);

}


