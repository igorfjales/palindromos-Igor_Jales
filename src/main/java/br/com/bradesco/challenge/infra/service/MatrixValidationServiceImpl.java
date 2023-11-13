package br.com.bradesco.challenge.infra.service;

import br.com.bradesco.challenge.domain.model.Matrix;
import br.com.bradesco.challenge.exception.MatrixValidationException;
import br.com.bradesco.challenge.domain.service.IMatrixValidationService;
import org.springframework.stereotype.Service;

@Service
public class MatrixValidationServiceImpl implements IMatrixValidationService {
    private static final int MAX_SIZE = 10;

    public void validate(Matrix matrixModel) {
        char[][] matrix = matrixModel.getMatrix();

        if (matrix == null) {
            throw new MatrixValidationException("Matrix cannot be null");
        }

        if (matrix.length > MAX_SIZE) {
            throw new MatrixValidationException("Matrix cannot have more than " + MAX_SIZE + " lines");
        }

        if (matrix[0].length > MAX_SIZE) {
            throw new MatrixValidationException("Matrix cannot have more than " + MAX_SIZE + " columns");
        }

        if (matrix.length != matrix[0].length) {
            throw new MatrixValidationException("Matrix must be square");
        }
    }
}
