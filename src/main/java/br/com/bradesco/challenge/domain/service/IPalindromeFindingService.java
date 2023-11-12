package br.com.bradesco.challenge.domain.service;

import br.com.bradesco.challenge.domain.entity.Matrix;

public interface IPalindromeFindingService {
    void findPalindromesInMatrix(Matrix matrixEntity);
    boolean isPalindrome(String word);

}
