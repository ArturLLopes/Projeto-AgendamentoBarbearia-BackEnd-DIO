package br.com.projeto.projetobarbershop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * Entidade que representa um agendamento.
 * Mapeada para a tabela SCHEDULES no banco de dados.
 */
@Entity
@Table(
        name = "SCHEDULES",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_SCHEDULE_INTERVAL", columnNames = {"start_at", "end_at"}), // Garantindo que os intervalos de horários não se sobreponham
        }
)
@Getter
@Setter
@ToString
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id; // Identificador único do agendamento

    @Column(nullable = false, name = "start_at")
    private OffsetDateTime startAt; // Hora de início do agendamento

    @Column(nullable = false, name = "end_at")
    private OffsetDateTime endAt; // Hora de término do agendamento

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "client_id") // Relacionamento com a entidade Cliente
    private ClientEntity client = new ClientEntity(); // Cliente associado ao agendamento

    /**
     * Sobrescrita do método equals para comparar objetos da entidade ScheduleEntity.
     * Compara os atributos id, startAt e endAt.
     *
     * @param o Objeto a ser comparado.
     * @return true se os objetos forem iguais, false caso contrário.
     */
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof ScheduleEntity that)) return false;
        return Objects.equals(id, that.id) &&
                Objects.equals(startAt, that.startAt) &&
                Objects.equals(endAt, that.endAt);
    }

    /**
     * Sobrescrita do método hashCode para garantir que objetos iguais tenham o mesmo código hash.
     * Utiliza os atributos id, startAt e endAt.
     *
     * @return código hash gerado com base nos atributos id, startAt e endAt.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, startAt, endAt);
    }
}
