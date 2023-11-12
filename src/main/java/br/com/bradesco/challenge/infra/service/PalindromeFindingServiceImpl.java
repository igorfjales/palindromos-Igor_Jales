package br.com.bradesco.challenge.infra.service;

import br.com.bradesco.challenge.domain.entity.Matrix;
import br.com.bradesco.challenge.domain.entity.Palindrome;
import br.com.bradesco.challenge.domain.service.IPalindromeFindingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PalindromeFindingServiceImpl implements IPalindromeFindingService {

    static final int MIN_PALINDROME_LENGTH = 3;

    @Override
    public void findPalindromesInMatrix(Matrix matrixEntity) {
        char[][] matrix = matrixEntity.getMatrix();
        int totalLines = matrix.length;
        int totalColumns = matrix[0].length;

        // Percorrer todas as posições na matriz
        for (int lineIndex = 0; lineIndex < totalLines; lineIndex++) {
            for (int columnIndex = 0; columnIndex < totalColumns; columnIndex++) {
                // Verificar palíndromos nas direções: horizontal, vertical, diagonal direita e diagonal esquerda
                verifyPalindromes(matrixEntity, lineIndex, columnIndex, 0, 1); // Horizontal
                verifyPalindromes(matrixEntity, lineIndex, columnIndex, 1, 0); // Vertical
                verifyPalindromes(matrixEntity, lineIndex, columnIndex, 1, 1); // Diagonal direita
                verifyPalindromes(matrixEntity, lineIndex, columnIndex, 1, -1); // Diagonal esquerda
            }
        }
    }

    public void verifyPalindromes(Matrix matrixEntity, int lineIndex, int columnIndex, int lineDirection, int columnDirecion) {
        char[][] matrix = matrixEntity.getMatrix();
        int totalLines = matrix.length;
        int totalColumns = matrix[0].length;
        StringBuilder word = new StringBuilder();

        if (lineIndex < 0) {
            throw new RuntimeException("The line can't be less than zero.");
        }

        if (lineIndex > totalLines) {
            throw new RuntimeException("The line can't be greater than total lines.");
        }

        if (columnIndex < 0) {
            throw new RuntimeException("The column can't be less than zero.");
        }

        if (columnIndex > totalColumns) {
            throw new RuntimeException("The column can't be greater than total columns.");
        }

        // Construir a palavra na direção especificada
        while (lineIndex >= 0 && lineIndex < totalLines && columnIndex >= 0 && columnIndex < totalColumns) {
            word.append(matrix[lineIndex][columnIndex]);
            lineIndex += lineDirection;
            columnIndex += columnDirecion;
        }

        // Verificar se a palavra é um palíndromo
        if (isPalindrome(word.toString())) {
            log.info("Palindrome found: " + word);

            Palindrome palindrome = Palindrome.builder()
                    .palindrome(word.toString())
                    .matrix(matrixEntity)
                    .build();

            matrixEntity.getPalindromes().add(palindrome);
        }
    }

    public boolean isPalindrome(String word) {
        word = word.replaceAll("\\s", "").toUpperCase();
        String invertedWord = new StringBuilder(word).reverse().toString();

        if (word.length() < MIN_PALINDROME_LENGTH) {
            log.debug(String.format("[%s] não possui o tamanho mínimo de %s para ser considerada uma palavra palindroma", word, MIN_PALINDROME_LENGTH));
            return false;
        }

        if (!word.equals(invertedWord)) {
            log.debug(String.format("[%s] não é uma palavra palindroma porque não é exatamente igual de trás pra frente.", word));
            return false;
        }

        return true;
    }
}
