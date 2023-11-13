package br.com.bradesco.challenge.service;

import br.com.bradesco.challenge.domain.model.Matrix;
import br.com.bradesco.challenge.domain.repository.IMatrixRepository;
import br.com.bradesco.challenge.domain.service.IMatrixValidationService;
import br.com.bradesco.challenge.domain.service.IPalindromeFindingService;
import br.com.bradesco.challenge.infra.service.MatrixServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class MatrixServiceImplTest {

    @Mock
    private IMatrixRepository repository;

    @Mock
    private IPalindromeFindingService palindromeFindingService;

    @Mock
    private IMatrixValidationService matrixValidationService;

    @InjectMocks
    private MatrixServiceImpl service;

    @Test
    void testSaveSuccess() {
        Matrix matrix = Matrix.builder()
                .id(UUID.randomUUID())
                .matrix(new char[3][3])
                .build();

        when(repository.save(any(Matrix.class))).thenReturn(matrix);

        Matrix savedMatrix = service.save(matrix);

        assertNotNull(savedMatrix);
        assertEquals(matrix.getId(), savedMatrix.getId());

        verify(matrixValidationService, times(1)).validate(matrix);
        verify(palindromeFindingService, times(1)).findPalindromesInMatrix(matrix);
        verify(repository, times(1)).save(matrix);
    }

    @Test
    void testFindByIdSuccess() {
        UUID id = UUID.randomUUID();

        Matrix matrix = Matrix.builder()
                .id(id)
                .matrix(new char[3][3])
                .build();

        when(repository.findById(id)).thenReturn(Optional.of(matrix));

        Optional<Matrix> foundMatrix = service.findById(id);

        verify(repository, times(1)).findById(id);
        assertTrue(foundMatrix.isPresent());
        assertEquals(matrix.getId(), foundMatrix.get().getId());
    }

    @Test
    void testFindAllSuccess() {
        List<Matrix> matrices = new ArrayList<>();
        matrices.add(Matrix.builder().id(UUID.randomUUID()).matrix(new char[3][3]).build());
        matrices.add(Matrix.builder().id(UUID.randomUUID()).matrix(new char[3][3]).build());

        when(repository.findAll()).thenReturn(matrices);

        List<Matrix> foundMatrices = service.findAll();

        assertEquals(matrices.size(), foundMatrices.size());
    }

}
