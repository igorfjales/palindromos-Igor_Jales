package br.com.bradesco.challenge.infra.service;

import br.com.bradesco.challenge.domain.exception.MatrixValidationException;
import br.com.bradesco.challenge.domain.service.IMatrixValidationService;
import org.springframework.stereotype.Service;

@Service
public class MatrixValidationServiceImpl implements IMatrixValidationService {
    private static final int MAX_SIZE = 10;

    public void validate(char[][] matrix) {
        if (matrix == null) {
            throw new MatrixValidationException("Matrix cannot be null");
        }

        if (matrix.length > MAX_SIZE) {
            throw new MatrixValidationException("Matrix cannot have more than " + MAX_SIZE + " rows");
        }

        for (char[] row : matrix) {
            if (row.length > MAX_SIZE) {
                throw new MatrixValidationException("Matrix cannot have more than " + MAX_SIZE + " columns");
            }

            if (row.length != matrix.length) {
                throw new MatrixValidationException("Matrix must be square");
            }
        }
    }
}
