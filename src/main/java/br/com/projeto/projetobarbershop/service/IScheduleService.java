package br.com.projeto.projetobarbershop.service;


import br.com.projeto.projetobarbershop.entity.ScheduleEntity;

public interface IScheduleService {

    ScheduleEntity save(final ScheduleEntity entity);

    void delete(final long id);

}