package br.com.bradesco.challenge.service;

import br.com.bradesco.challenge.domain.model.Palindrome;
import br.com.bradesco.challenge.domain.repository.IPalindromeRepository;
import br.com.bradesco.challenge.infra.service.PalindromeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PalindromeServiceImplTest {

    @Mock
    private IPalindromeRepository repository;

    @InjectMocks
    private PalindromeServiceImpl service;

    @Test
    void testFindByIdSuccess() {
        UUID id = UUID.randomUUID();

        Palindrome palindrome = Palindrome.builder()
                .id(id)
                .palindrome("12321")
                .build();

        when(repository.findById(id)).thenReturn(Optional.of(palindrome));

        Optional<Palindrome> foundPalindrome = service.findById(id);

        verify(repository, times(1)).findById(id);
        assertTrue(foundPalindrome.isPresent());
        assertEquals(palindrome.getId(), foundPalindrome.get().getId());
    }

    @Test
    void testFindAllSuccess() {
        List<Palindrome> palindromes = new ArrayList<>();
        palindromes.add(Palindrome.builder().id(UUID.randomUUID()).palindrome("12321").build());
        palindromes.add(Palindrome.builder().id(UUID.randomUUID()).palindrome("ovo").build());

        when(repository.findAll()).thenReturn(palindromes);

        List<Palindrome> foundPalindromes = service.findAll();

        assertEquals(palindromes.size(), foundPalindromes.size());
    }
}
