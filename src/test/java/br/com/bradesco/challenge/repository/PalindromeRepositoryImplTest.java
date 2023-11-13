package br.com.bradesco.challenge.repository;

import br.com.bradesco.challenge.domain.model.Palindrome;
import br.com.bradesco.challenge.infra.repository.PalindromeRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DataJpaTest
public class PalindromeRepositoryImplTest {

    @Autowired
    EntityManager entityManager;

    @Mock
    EntityManager testEntityManager;

    private PalindromeRepositoryImpl palindromeRepository;

    private PalindromeRepositoryImpl testPalindromeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        palindromeRepository = new PalindromeRepositoryImpl(entityManager);
        testPalindromeRepository = new PalindromeRepositoryImpl(testEntityManager);
    }

    @Test
    public void testSave() {
        Palindrome palindrome = Palindrome.builder()
                .palindrome("aba")
                .build();

        Palindrome palindromeTest = Palindrome.builder()
                .palindrome("12321")
                .build();

        Palindrome savedPalindrome = palindromeRepository.save(palindrome);
        assertThat(savedPalindrome).isNotNull();
        assertThat(savedPalindrome.getId()).isNotNull();

        Palindrome loadedPalindrome = palindromeRepository.findById(savedPalindrome.getId()).orElse(null);
        assertThat(loadedPalindrome).isNotNull();
        assertThat(loadedPalindrome.getId()).isNotNull();

        Palindrome testSavedPalindrome = testPalindromeRepository.save(palindromeTest);
        assertThat(testSavedPalindrome).isNotNull();

        verify(testEntityManager, times(1)).persist(any(Palindrome.class));
    }

    @Test
    public void testFindById() {
        Palindrome palindrome = Palindrome.builder()
                .palindrome("aba")
                .build();

        Palindrome savedPalindrome = palindromeRepository.save(palindrome);

        Optional<Palindrome> foundMatrix = palindromeRepository.findById(savedPalindrome.getId());
        assertThat(foundMatrix).isPresent();
        assertThat(foundMatrix.get().getId()).isEqualTo(savedPalindrome.getId());
    }

    @Test
    public void testFindAll() {
        Palindrome palindrome1 = Palindrome.builder()
                .palindrome("aba")
                .build();

        Palindrome palindrome2 = Palindrome.builder()
                .palindrome("aba")
                .build();

        palindromeRepository.save(palindrome1);
        palindromeRepository.save(palindrome2);

        List<Palindrome> palindromes = palindromeRepository.findAll();
        assertThat(palindromes).hasSize(2);
    }
}