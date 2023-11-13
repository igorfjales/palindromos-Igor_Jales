package br.com.bradesco.challenge.domain.repository;

import br.com.bradesco.challenge.domain.model.Palindrome;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPalindromeRepository {

    Optional<Palindrome> findById(UUID id);

    List<Palindrome> findAll();
}
