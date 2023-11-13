package br.com.bradesco.challenge.service;

import br.com.bradesco.challenge.domain.model.Matrix;
import br.com.bradesco.challenge.infra.service.PalindromeFindingServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PalindromeFindingServiceImplTest {

    @InjectMocks
    private PalindromeFindingServiceImpl service;

    @Test
    public void testFindPalindromesInMatrix() {
        Matrix matrix = Matrix.builder()
                .matrix(new char[][]{{'a', 'b', 'a'}, {'b', 'a', 'b'}, {'a', 'b', 'a'}})
                .build();

        service.findPalindromesInMatrix(matrix);

        Assertions.assertEquals(8, matrix.getPalindromes().size());
    }

    @Test
    public void testIsPalindrome() {
        Assertions.assertTrue(service.isPalindrome("aba"));
        Assertions.assertFalse(service.isPalindrome("abc"));
    }
}
