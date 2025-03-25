package br.com.projeto.projetobarbershop.service.query.impl;

import br.com.projeto.projetobarbershop.entity.ClientEntity;
import br.com.projeto.projetobarbershop.exception.EmailInUseException;
import br.com.projeto.projetobarbershop.exception.PhoneInUseException;
import br.com.projeto.projetobarbershop.repository.IClientRepository;
import br.com.projeto.projetobarbershop.service.query.IClientQueryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ClientQueryService implements IClientQueryService {

    private final IClientRepository repository;

    @Override
    public ClientEntity findById(final long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Não foi encontrado o cliente de id " + id));
    }

    @Override
    public List<ClientEntity> list() {
        return repository.findAll();
    }

    @Override
    public void verifyPhone(final String phone) {
        if (repository.existsByPhone(phone)) {
            throw new PhoneInUseException("O telefone " + phone + " já está em uso");
        }
    }

    @Override
    public void verifyPhone(final long id, final String phone) {
        var optional = repository.findByPhone(phone);
        if (optional.isPresent() && !Objects.equals(optional.get().getId(), id)) {
            throw new PhoneInUseException("O telefone " + phone + " já está em uso");
        }
    }

    @Override
    public void verifyEmail(final String email) {
        if (repository.existsByEmail(email)) {
            throw new EmailInUseException("O e-mail " + email + " já está em uso");
        }
    }

    @Override
    public void verifyEmail(final long id, final String email) {
        var optional = repository.findByEmail(email);
        if (optional.isPresent() && !Objects.equals(optional.get().getId(), id)) {
            throw new EmailInUseException("O e-mail " + email + " já está em uso");
        }
    }
}
