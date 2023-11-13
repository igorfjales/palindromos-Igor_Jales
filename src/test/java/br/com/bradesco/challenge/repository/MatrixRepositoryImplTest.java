package br.com.bradesco.challenge.repository;

import br.com.bradesco.challenge.domain.model.Matrix;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MatrixRepositoryImplTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testSave() {
        Matrix matrix = Matrix.builder()
                .matrix(new char[][]{{'a', 'b', 'a'}, {'b', 'a', 'b'}, {'a', 'b', 'a'}})
                .build();

        Matrix savedMatrix = entityManager.persistAndFlush(matrix);

        assertThat(savedMatrix).isNotNull();
        assertThat(savedMatrix.getId()).isNotNull();
    }

    @Test
    public void testFindById() {
        Matrix newMatrix = Matrix.builder()
                .matrix(new char[][]{{'a', 'b', 'a'}, {'b', 'a', 'b'}, {'a', 'b', 'a'}})
                .build();

        Matrix savedMatrix = entityManager.persistAndFlush(newMatrix);

        Matrix foundMatrix = entityManager.find(Matrix.class, savedMatrix.getId());
        Optional<Matrix> optionalMatrix = foundMatrix != null ? Optional.of(foundMatrix) : Optional.empty();

        assertThat(optionalMatrix).isPresent();
        assertThat(optionalMatrix.get().getId()).isEqualTo(savedMatrix.getId());
    }

    @Test
    public void testFindAll() {
        Matrix matrix1 = Matrix.builder()
                .matrix(new char[][]{{'a', 'b', 'a'}, {'b', 'a', 'b'}, {'a', 'b', 'a'}})
                .build();

        Matrix matrix2 = Matrix.builder()
                .matrix(new char[][]{{'a', 'b', 'a'}, {'b', 'a', 'b'}, {'a', 'b', 'a'}})
                .build();

        entityManager.persistAndFlush(matrix1);
        entityManager.persistAndFlush(matrix2);

        List<Matrix> matrices = entityManager.getEntityManager()
                .createQuery("from Matrix", Matrix.class)
                .getResultList();

        assertThat(matrices).hasSize(2);
    }
}
