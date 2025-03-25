package br.com.projeto.projetobarbershop.repository;

import br.com.projeto.projetobarbershop.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório responsável pelas operações de persistência e recuperação de dados relacionados aos clientes.
 * Extende JpaRepository, que oferece métodos padrões para interação com o banco de dados.
 */
@Repository
public interface IClientRepository extends JpaRepository<ClientEntity, Long> {

    /**
     * Verifica se já existe um cliente com o 'e-mail 'fornecido.
     *
     * @param email O e-mail do cliente.
     * @return Retorna `true` se o e-mail já estiver cadastrado; false caso contrário.
     */
    boolean existsByEmail(final String email);

    /**
     * Verifica se já existe um cliente com o telefone fornecido.
     *
     * @param phone O telefone do cliente.
     * @return Retorna `true` se o telefone já estiver cadastrado; `false` caso contrário.
     */
    boolean existsByPhone(final String phone);

    /**
     * Encontra um cliente com o' e-mail' fornecido.
     *
     * @param email O e-mail do cliente.
     * @return Retorna um Optional<ClientEntity> contendo o cliente encontrado, ou `Optional.empty()` se não encontrado.
     */
    Optional<ClientEntity> findByEmail(final String email);

    /**
     * Encontra um cliente com o telefone fornecido.
     *
     * @param phone O telefone do cliente.
     * @return Retorna um `Optional<ClientEntity>` contendo o cliente encontrado, ou `Optional.empty()` se não encontrado.
     */
    Optional<ClientEntity> findByPhone(final String phone);
}
