package br.com.projeto.projetobarbershop.service.impl;

import br.com.projeto.projetobarbershop.entity.ScheduleEntity;
import br.com.projeto.projetobarbershop.repository.IScheduleRepository;
import br.com.projeto.projetobarbershop.service.IScheduleService;
import br.com.projeto.projetobarbershop.service.query.IScheduleQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Serviço que gerencia as operações de agendamentos, como a criação e exclusão de agendamentos.
 * A lógica de negócio é encapsulada nesta classe, utilizando os repositórios e serviços de consulta.
 */
@Service
@AllArgsConstructor
public class ScheduleService implements IScheduleService {

    private final IScheduleRepository repository;
    private final IScheduleQueryService queryService;

    /**
     * Salva um novo agendamento, após verificar se o intervalo de horário já está ocupado.
     *
     * @param entity O objeto agendamento a ser salvo.
     * @return O agendamento salvo, com id gerado.
     */
    @Override
    public ScheduleEntity save(final ScheduleEntity entity) {
        // Verifica se o intervalo de horários já existe, evitando sobreposição de agendamentos.
        queryService.verifyIfScheduleExists(entity.getStartAt(), entity.getEndAt());

        // Salva o agendamento no banco de dados
        return repository.save(entity);
    }

    /**
     * Exclui um agendamento baseado no id fornecido.
     *
     * Antes de excluir, verifica se o agendamento existe.
     *
     * @param id Id do agendamento a ser excluído.
     */
    @Override
    public void delete(final long id) {
        // Verifica se o agendamento existe antes de tentar excluir
        queryService.findById(id);

        // Exclui o agendamento do banco de dados
        repository.deleteById(id);
    }
}
