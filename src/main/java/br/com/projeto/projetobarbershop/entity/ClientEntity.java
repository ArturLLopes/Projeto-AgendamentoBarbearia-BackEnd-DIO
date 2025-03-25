package br.com.projeto.projetobarbershop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * Entidade que representa um cliente.
 * Mapeada para a tabela CLIENTS no banco de dados.
 */
@Entity
@Table(
        name = "CLIENTS",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_EMAIL", columnNames = "email"),
                @UniqueConstraint(name = "UK_PHONE", columnNames = "phone")
        }
)
@Getter
@Setter
@ToString
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id; // Identificador único do cliente

    @Column(nullable = false, length = 150)
    private String name; // Nome do cliente

    @Column(nullable = false, length = 150)
    private String email; // E-mail do cliente

    @Column(nullable = false, length = 11, columnDefinition = "bpchar(11)")
    private String phone; // Telefone do cliente

    @ToString.Exclude
    @OneToMany(mappedBy = "client", cascade = ALL, orphanRemoval = true)
    private Set<ScheduleEntity> schedules = new HashSet<>(); // Relacionamento com agendamentos do cliente

    /**
     * Sobrescrita do método equals para comparar objetos da entidade ClientEntity.
     * Compara os atributos id, nome, email e telefone.
     *
     * @param o Objeto a ser comparado.
     * @return true se os objetos forem iguais, false caso contrário.
     */
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof ClientEntity that)) return false;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phone, that.phone);
    }

    /**
     * Sobrescrita do método hashCode para garantir que objetos iguais tenham o mesmo código hash.
     * Utiliza os atributos id, nome, email e telefone.
     *
     * @return código hash gerado com base nos atributos id, nome, email e telefone.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, phone);
    }
}
