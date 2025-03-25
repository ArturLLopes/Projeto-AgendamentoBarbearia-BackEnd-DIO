package br.com.projeto.projetobarbershop.repository;

import br.com.projeto.projetobarbershop.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Repositório responsável pelas operações de persistência e recuperação de dados relacionados aos agendamentos.
 * Extende JpaRepository, que oferece métodos padrões para interação com o banco de dados.
 */
@Repository
public interface IScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

    /**
     * Encontra os agendamentos que ocorrem dentro do intervalo de tempo especificado,
     * ordenados pela data e hora de início e fim.
     *
     * @param startAt Data e hora de início do intervalo.
     * @param endAt Data e hora de fim do intervalo.
     * @return Lista de agendamentos que ocorrem dentro do intervalo.
     */
    List<ScheduleEntity> findByStartAtGreaterThanEqualAndEndAtLessThanEqualOrderByStartAtAscEndAtAsc(
            final OffsetDateTime startAt,
            final OffsetDateTime endAt
    );

    /**
     * Verifica se já existe um agendamento com o intervalo de tempo fornecido.
     *
     * @param startAt Data e hora de início do agendamento.
     * @param endAt Data e hora de fim do agendamento.
     * @return Retorna `true` se o agendamento com esse intervalo já existir; `false` caso contrário.
     */
    boolean existsByStartAtAndEndAt(final OffsetDateTime startAt, final OffsetDateTime endAt);
}
