package br.com.bradesco.challenge.repository;

import br.com.bradesco.challenge.domain.model.Palindrome;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PalindromeRepositoryImplTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testFindById() {
        Palindrome palindrome = Palindrome.builder()
                .palindrome("aba")
                .build();

        Palindrome savedPalindrome = entityManager.persistAndFlush(palindrome);

        Palindrome foundPalindrome = entityManager.find(Palindrome.class, savedPalindrome.getId());
        Optional<Palindrome> optionalPalindrome = foundPalindrome != null ? Optional.of(foundPalindrome) : Optional.empty();

        assertThat(optionalPalindrome).isPresent();
        assertThat(optionalPalindrome.get().getId()).isEqualTo(savedPalindrome.getId());
    }

    @Test
    public void testFindAll() {
        Palindrome palindrome1 = Palindrome.builder()
                .palindrome("aba")
                .build();

        Palindrome palindrome2 = Palindrome.builder()
                .palindrome("aba")
                .build();

        entityManager.persistAndFlush(palindrome1);
        entityManager.persistAndFlush(palindrome2);

        List<Palindrome> palindromes = entityManager.getEntityManager()
                .createQuery("from Palindrome", Palindrome.class)
                .getResultList();

        assertThat(palindromes).hasSize(2);
    }
}