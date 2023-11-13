package br.com.bradesco.challenge.infra.service;

import br.com.bradesco.challenge.domain.model.Palindrome;
import br.com.bradesco.challenge.domain.repository.IPalindromeRepository;
import br.com.bradesco.challenge.domain.service.IPalindromeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PalindromeServiceImpl implements IPalindromeService {

    private final IPalindromeRepository repository;

    @Override
    public Optional<Palindrome> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<Palindrome> findAll() {
        return repository.findAll();
    }
}
