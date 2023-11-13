package br.com.bradesco.challenge.model;

import br.com.bradesco.challenge.domain.model.Matrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {

    @Test
    public void testConvertMatrixToString() {
        Matrix matrix = Matrix.builder()
                .matrix(new char[][]{{'a', 'b', 'a'}, {'b', 'a', 'b'}, {'a', 'b', 'a'}})
                .build();

        matrix.convertMatrixToString();

        assertEquals("[\"aba\",\"bab\",\"aba\"]", matrix.getMatrixAsString());
    }

    @Test
    public void testConvertStringToMatrix() {
        Matrix matrix = Matrix.builder()
                .matrixAsString("[[\"a\",\"b\",\"a\"],[\"b\",\"a\",\"b\"],[\"a\",\"b\",\"a\"]]")
                .build();

        matrix.convertStringToMatrix();

        assertArrayEquals(new char[][]{{'a', 'b', 'a'}, {'b', 'a', 'b'}, {'a', 'b', 'a'}}, matrix.getMatrix());
    }

    @Test
    public void testConvertStringToMatrixException() {
        Matrix matrix = new Matrix();
        matrix.setMatrixAsString("malformed string");

        Exception exception = assertThrows(RuntimeException.class, matrix::convertStringToMatrix);
        assertEquals("Error converting string to matrix", exception.getMessage());
    }

    @Test
    public void testToString() {
        Matrix matrix = Matrix.builder()
                .matrix(new char[][]{{'a', 'b', 'a'}, {'b', 'a', 'b'}, {'a', 'b', 'a'}})
                .build();

        String expected = "Matrix(id=null, matrix=[[a, b, a], [b, a, b], [a, b, a]], matrixAsString=null, createdAt=null, palindromes=[])";
        assertEquals(expected, matrix.toString());
    }
}