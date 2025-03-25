package br.com.projeto.projetobarbershop.controller;

import br.com.projeto.projetobarbershop.controller.request.SaveClientRequest;
import br.com.projeto.projetobarbershop.controller.request.UpdateClientRequest;
import br.com.projeto.projetobarbershop.controller.response.ClientDetailResponse;
import br.com.projeto.projetobarbershop.controller.response.ListClientResponse;
import br.com.projeto.projetobarbershop.controller.response.SaveClientResponse;
import br.com.projeto.projetobarbershop.controller.response.UpdateClientResponse;
import br.com.projeto.projetobarbershop.mapper.IClientMapper;
import br.com.projeto.projetobarbershop.service.IClientService;
import br.com.projeto.projetobarbershop.service.query.IClientQueryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

/**
 * Controlador de clientes. Gerencia as requisições HTTP para operações relacionadas a clientes.
 */
@RestController
@RequestMapping("clients")
@AllArgsConstructor
public class ClientController {

    private final IClientService service;           // Serviço para operações de negócio com clientes
    private final IClientQueryService queryService; // Serviço para consultas (queries) de clientes
    private final IClientMapper mapper;             // Mapper para converter entre objetos de requisição, resposta e entidades

    /**
     * Salva um novo cliente.
     * @param request Dados do cliente a ser salvo.
     * @return Resposta com os dados do cliente salvo.
     */
    @PostMapping
    @ResponseStatus(CREATED)
    SaveClientResponse save(@RequestBody @Valid final SaveClientRequest request){
        var entity = mapper.toEntity(request);   // Mapeia a requisição para uma entidade
        service.save(entity);                    // Salva o cliente
        return mapper.toSaveResponse(entity);    // Retorna a resposta com os dados do cliente salvo
    }

    /**
     * Atualiza as informações de um cliente existente.
     * @param id Identificador do cliente.
     * @param request Dados atualizados do cliente.
     * @return Resposta com os dados do cliente atualizado.
     */
    @PutMapping("{id}")
    UpdateClientResponse update(@PathVariable final long id, @RequestBody @Valid final UpdateClientRequest request){
        var entity = mapper.toEntity(id, request); // Mapeia a requisição para uma entidade com o ID fornecido
        service.update(entity);                     // Atualiza o cliente
        return mapper.toUpdateResponse(entity);     // Retorna a resposta com os dados do cliente atualizado
    }

    /**
     * Deleta um cliente.
     * @param id Identificador do cliente a ser deletado.
     */
    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    void delete(@PathVariable final long id){
        service.delete(id); // Deleta o cliente
    }

    /**
     * Busca um cliente pelo seu ID.
     * @param id Identificador do cliente.
     * @return Detalhes do cliente encontrado.
     */
    @GetMapping("{id}")
    ClientDetailResponse findById(@PathVariable final long id){
        var entity = queryService.findById(id);      // Busca o cliente pelo ID
        return mapper.toDetailResponse(entity);      // Retorna a resposta com os dados detalhados do cliente
    }

    /**
     * Lista todos os clientes.
     * @return Lista de clientes.
     */
    @GetMapping
    List<ListClientResponse> list(){
        var entities = queryService.list(); // Obtém todos os clientes
        return mapper.toListResponse(entities); // Retorna a lista de clientes
    }
}
