package br.com.projeto.projetobarbershop.service;


import br.com.projeto.projetobarbershop.entity.ClientEntity;

public interface IClientService {

    ClientEntity save(final ClientEntity entity);

    ClientEntity update(final ClientEntity entity);

    void delete(final long id);

}