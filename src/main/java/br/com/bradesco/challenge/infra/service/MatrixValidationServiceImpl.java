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

        int rows = matrix.length;
        int columns = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            if (matrix[i].length != columns) {
                throw new MatrixValidationException("All rows must have the same number of columns");
            }
        }

        for (int j = 0; j < columns; j++) {
            int currentColumnSize = matrix[0].length;

            for (int i = 1; i < rows; i++) {
                if (matrix[i].length != currentColumnSize) {
                    throw new MatrixValidationException("All columns must have the same number of rows");
                }
            }
        }

        if (rows > MAX_SIZE || columns > MAX_SIZE) {
            throw new MatrixValidationException("Matrix cannot have more than " + MAX_SIZE + " rows or columns");
        }

        if (rows != columns) {
            throw new MatrixValidationException("Matrix must be square");
        }
    }
}
