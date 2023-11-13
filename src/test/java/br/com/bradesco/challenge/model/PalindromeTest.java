package br.com.bradesco.challenge.model;

import br.com.bradesco.challenge.domain.model.Matrix;
import br.com.bradesco.challenge.domain.model.Palindrome;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PalindromeTest {

    @Test
    public void testConstructorAndBuilder() {
        UUID id = UUID.randomUUID();
        String palindrome = "aba";
        Matrix matrix = new Matrix();

        Palindrome palindromeObj = Palindrome.builder()
                .id(id)
                .palindrome(palindrome)
                .matrix(matrix)
                .build();

        assertEquals(id, palindromeObj.getId());
        assertEquals(palindrome, palindromeObj.getPalindrome());
        assertEquals(matrix, palindromeObj.getMatrix());
    }

    @Test
    public void testSetters() {
        UUID id = UUID.randomUUID();
        String palindrome = "aba";
        Matrix matrix = new Matrix();

        Palindrome palindromeObj = new Palindrome();
        palindromeObj.setId(id);
        palindromeObj.setPalindrome(palindrome);
        palindromeObj.setMatrix(matrix);

        assertEquals(id, palindromeObj.getId());
        assertEquals(palindrome, palindromeObj.getPalindrome());
        assertEquals(matrix, palindromeObj.getMatrix());
    }

    @Test
    public void testToString() {
        UUID id = UUID.randomUUID();
        String palindrome = "aba";
        Matrix matrix = new Matrix();

        Palindrome palindromeObj = Palindrome.builder()
                .id(id)
                .palindrome(palindrome)
                .matrix(matrix)
                .build();

        String expected = "Palindrome(id=" + id + ", palindrome=" + palindrome + ", matrix=" + matrix + ")";
        assertEquals(expected, palindromeObj.toString());
    }
}