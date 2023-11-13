package br.com.bradesco.challenge.domain.service;

import br.com.bradesco.challenge.domain.model.Matrix;

public interface IPalindromeFindingService {
    void findPalindromesInMatrix(Matrix matrixModel);
    boolean isPalindrome(String word);
}
