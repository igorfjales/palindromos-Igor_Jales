package br.com.bradesco.challenge.service;

import br.com.bradesco.challenge.domain.model.Matrix;
import br.com.bradesco.challenge.exception.MatrixValidationException;
import br.com.bradesco.challenge.infra.service.MatrixValidationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
public class MatrixValidationServiceImplTest {

    @InjectMocks
    private MatrixValidationServiceImpl service;

    @Test
    public void testValidateWithValidMatrix() {
        Matrix matrix = Matrix.builder()
                .matrix(new char[10][10])
                .build();

        assertDoesNotThrow(() -> service.validate(matrix));
    }

    @Test
    public void testValidateWithNullMatrix() {
        Matrix matrix = Matrix.builder()
                .matrix(null)
                .build();

        assertThrows(MatrixValidationException.class, () -> service.validate(matrix));
    }

    @Test
    public void testValidateWithTooManyLines() {
        Matrix matrix = Matrix.builder()
                .matrix(new char[11][10])
                .build();

        assertThrows(MatrixValidationException.class, () -> service.validate(matrix));
    }

    @Test
    public void testValidateWithTooManyColumns() {
        Matrix matrix = Matrix.builder()
                .matrix(new char[10][11])
                .build();

        assertThrows(MatrixValidationException.class, () -> service.validate(matrix));
    }

    @Test
    public void testValidateWithNonSquareMatrix() {
        Matrix matrix = Matrix.builder()
                .matrix(new char[10][9])
                .build();

        assertThrows(MatrixValidationException.class, () -> service.validate(matrix));
    }
}
