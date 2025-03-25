package br.com.projeto.projetobarbershop.service.impl;

import br.com.projeto.projetobarbershop.entity.ClientEntity;
import br.com.projeto.projetobarbershop.repository.IClientRepository;
import br.com.projeto.projetobarbershop.service.IClientService;
import br.com.projeto.projetobarbershop.service.query.IClientQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Implementação do serviço para gerenciar as operações relacionadas aos clientes.
 * Este serviço lida com a lógica de negócio para criação, atualização e exclusão de clientes.
 */
@Service
@AllArgsConstructor
public class ClientService implements IClientService {

    private final IClientRepository repository;
    private final IClientQueryService queryService;

    /**
     * Salva um novo cliente no banco de dados.
     *
     * Antes de salvar, verifica se o e-mail e o telefone são únicos.
     *
     * @param entity Objeto cliente a ser salvo.
     * @return O cliente salvo com o id gerado.
     */
    @Override
    public ClientEntity save(final ClientEntity entity) {
        // Verifica se o e-mail e o telefone são únicos antes de salvar
        queryService.verifyEmail(entity.getEmail());
        queryService.verifyPhone(entity.getPhone());

        // Salva o cliente no banco de dados
        return repository.save(entity);
    }

    /**
     * Atualiza um cliente existente.
     *
     * Antes de atualizar, verifica se o 'email' e o telefone são únicos.
     *
     * @param entity Objeto cliente com os dados atualizados.
     * @return O cliente atualizado.
     */
    @Override
    public ClientEntity update(final ClientEntity entity) {
        // Verifica se o email e o telefone são únicos para o cliente com o id fornecido
        queryService.verifyEmail(entity.getId(), entity.getEmail());
        queryService.verifyPhone(entity.getId(), entity.getPhone());

        // Recupera o cliente armazenado para atualizar os dados
        var stored = queryService.findById(entity.getId());
        stored.setName(entity.getName());
        stored.setPhone(entity.getPhone());
        stored.setEmail(entity.getEmail());

        // Salva o cliente atualizado no banco de dados
        return repository.save(stored);
    }

    /**
     * Exclui um cliente do banco de dados.
     *
     * Antes de excluir, verifica se o cliente existe.
     *
     * @param id Id do cliente a ser excluído.
     */
    @Override
    public void delete(final long id) {
        // Verifica se o cliente existe antes de tentar excluir
        queryService.findById(id);

        // Exclui o cliente do banco de dados
        repository.deleteById(id);
    }

}