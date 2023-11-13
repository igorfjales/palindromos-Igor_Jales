package br.com.bradesco.challenge.infra.repository;

import br.com.bradesco.challenge.domain.model.Palindrome;
import br.com.bradesco.challenge.domain.repository.IPalindromeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PalindromeRepositoryImpl implements IPalindromeRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public PalindromeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Palindrome> findById(UUID id) {
        Palindrome palindrome = entityManager.find(Palindrome.class, id);
        return palindrome != null ? Optional.of(palindrome) : Optional.empty();
    }

    @Override
    public List<Palindrome> findAll() {
        return entityManager.createQuery("from Palindrome", Palindrome.class).getResultList();
    }
}
