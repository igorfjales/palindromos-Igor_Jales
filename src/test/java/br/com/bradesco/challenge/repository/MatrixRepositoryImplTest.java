package br.com.bradesco.challenge.repository;

import br.com.bradesco.challenge.domain.model.Matrix;
import br.com.bradesco.challenge.infra.repository.MatrixRepositoryImpl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;

@DataJpaTest
public class MatrixRepositoryImplTest {

    @Autowired
    EntityManager entityManager;

    @Mock
    EntityManager testEntityManager;

    private MatrixRepositoryImpl matrixRepository;

    private MatrixRepositoryImpl testMatrixRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        matrixRepository = new MatrixRepositoryImpl(entityManager);
        testMatrixRepository = new MatrixRepositoryImpl(testEntityManager);
    }

    @Test
    public void testSave() {
        Matrix matrix = Matrix.builder()
                .matrix(new char[10][10])
                .build();

        Matrix testMatrix = Matrix.builder()
                .matrix(new char[10][10])
                .build();

        Matrix savedMatrix = matrixRepository.save(matrix);
        assertThat(savedMatrix).isNotNull();
        assertThat(savedMatrix.getId()).isNotNull();

        Matrix loadedMatrix = matrixRepository.findById(savedMatrix.getId()).orElse(null);
        assertThat(loadedMatrix).isNotNull();
        assertThat(loadedMatrix.getId()).isNotNull();

        Matrix testSavedMatrix = testMatrixRepository.save(testMatrix);
        assertThat(testSavedMatrix).isNotNull();

        verify(testEntityManager, times(1)).persist(any(Matrix.class));
    }

    @Test
    public void testFindById() {
        Matrix matrix = Matrix.builder()
                .matrix(new char[10][10])
                .build();

        Matrix savedMatrix = matrixRepository.save(matrix);

        Optional<Matrix> foundMatrix = matrixRepository.findById(savedMatrix.getId());
        assertThat(foundMatrix).isPresent();
        assertThat(foundMatrix.get().getId()).isEqualTo(savedMatrix.getId());
    }

    @Test
    public void testFindAll() {
        Matrix matrix1 = Matrix.builder()
                .matrix(new char[10][10])
                .build();

        Matrix matrix2 = Matrix.builder()
                .matrix(new char[10][10])
                .build();

        matrixRepository.save(matrix1);
        matrixRepository.save(matrix2);

        List<Matrix> matrices = matrixRepository.findAll();
        Assertions.assertThat(matrices).hasSize(2);
    }
}
