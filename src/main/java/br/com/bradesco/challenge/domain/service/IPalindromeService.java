package br.com.bradesco.challenge.domain.service;

import br.com.bradesco.challenge.domain.entity.Palindrome;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPalindromeService {
    Palindrome save(Palindrome palindrome);

    Optional<Palindrome> findById(UUID id);

    List<Palindrome> findAll();


}
