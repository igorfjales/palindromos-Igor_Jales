package br.com.bradesco.challenge.infra.service;

import br.com.bradesco.challenge.domain.model.Matrix;
import br.com.bradesco.challenge.domain.model.Palindrome;
import br.com.bradesco.challenge.domain.service.IPalindromeFindingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PalindromeFindingServiceImpl implements IPalindromeFindingService {

    static final int MIN_PALINDROME_LENGTH = 3;

    @Override
    public void findPalindromesInMatrix(Matrix matrixModel) {
        char[][] matrix = matrixModel.getMatrix();
        int totalLines = matrix.length;
        int totalColumns = matrix[0].length;

        for (int lineIndex = 0; lineIndex < totalLines; lineIndex++) {
            for (int columnIndex = 0; columnIndex < totalColumns; columnIndex++) {
                verifyPalindromes(matrixModel, lineIndex, columnIndex, 0, 1); // Horizontal
                verifyPalindromes(matrixModel, lineIndex, columnIndex, 1, 0); // Vertical
                verifyPalindromes(matrixModel, lineIndex, columnIndex, 1, 1); // Diagonal direita
                verifyPalindromes(matrixModel, lineIndex, columnIndex, 1, -1); // Diagonal esquerda
            }
        }
    }

    public void verifyPalindromes(Matrix matrixModel, int lineIndex, int columnIndex, int lineDirection, int columnDirecion) {
        char[][] matrix = matrixModel.getMatrix();
        int totalLines = matrix.length;
        int totalColumns = matrix[0].length;
        StringBuilder word = new StringBuilder();

        while (isValidIndex(lineIndex, totalLines) && isValidIndex(columnIndex, totalColumns)) {
            word.append(matrix[lineIndex][columnIndex]);
            lineIndex += lineDirection;
            columnIndex += columnDirecion;
        }

        if (isPalindrome(word.toString())) {
            log.info("Palindrome found: {}", word);

            matrixModel.getPalindromes().add(
                    buildPalindrome(word, matrixModel)
            );
        }
    }

    private boolean isValidIndex(int index, int limit) {
        return index >= 0 && index < limit;
    }

    private Palindrome buildPalindrome(StringBuilder word, Matrix matrixModel) {
        return Palindrome.builder()
                .palindrome(word.toString())
                .matrix(matrixModel)
                .build();
    }

    public boolean isPalindrome(String word) {
        String cleanedWord = cleanAndUpperCase(word);
        String invertedWord = new StringBuilder(cleanedWord).reverse().toString();

        if (cleanedWord.length() < MIN_PALINDROME_LENGTH) {
            log.debug("[{}] does not have the minimum length of [{}] to be considered a palindrome", cleanedWord, MIN_PALINDROME_LENGTH);
            return false;
        }

        if (!cleanedWord.equals(invertedWord)) {
            log.debug("[{}] is not a palindrome because it is not exactly the same backward.", cleanedWord);
            return false;
        }

        return true;
    }

    private String cleanAndUpperCase(String word) {
        return word.replaceAll("\\s", "").toUpperCase();
    }
}
