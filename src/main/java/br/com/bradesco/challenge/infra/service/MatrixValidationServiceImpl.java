package br.com.bradesco.challenge.infra.service;

import br.com.bradesco.challenge.domain.model.Matrix;
import br.com.bradesco.challenge.exception.MatrixValidationException;
import br.com.bradesco.challenge.domain.service.IMatrixValidationService;
import org.springframework.stereotype.Service;

@Service
public class MatrixValidationServiceImpl implements IMatrixValidationService {
    private static final int MAX_SIZE = 10;

    private static final int MIN_SIZE = 3;


    public void validate(Matrix matrixModel) {
        char[][] matrix = matrixModel.getMatrix();

        if (matrix == null) {
            throw new MatrixValidationException("Matrix cannot be null");
        }

        int rows = matrix.length;
        int columns = matrix[0].length;

        if (rows > MAX_SIZE || columns > MAX_SIZE) {
            throw new MatrixValidationException("Matrix cannot have more than " + MAX_SIZE + " rows or columns");
        }

        if (rows < MIN_SIZE || columns < MIN_SIZE) {
            throw new MatrixValidationException("Matrix must have at least " + MIN_SIZE + " rows and columns");
        }

        if (rows != columns) {
            throw new MatrixValidationException("Matrix must be square");
        } else {
            for (char[] chars : matrix) {
                if (chars.length != columns) {
                    throw new MatrixValidationException("Matrix must be square");
                }
            }
        }
    }
}
