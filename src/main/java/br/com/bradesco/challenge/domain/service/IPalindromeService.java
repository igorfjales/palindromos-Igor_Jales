package br.com.bradesco.challenge.domain.service;

import br.com.bradesco.challenge.domain.model.Palindrome;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPalindromeService {

    Optional<Palindrome> findById(UUID id);

    List<Palindrome> findAll();
}
