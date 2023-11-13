//package br.com.bradesco.challenge.repository;
//
//import br.com.bradesco.challenge.domain.model.Matrix;
//import br.com.bradesco.challenge.infra.repository.MatrixRepositoryImpl;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//public class MatrixRepositoryImplDOISTest {
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Autowired
//    private MatrixRepositoryImpl matrixRepository;
//
//    @Test
//    public void testSave() {
//        Matrix matrix = Matrix.builder()
//                .matrix(new char[][]{{'a', 'b', 'a'}, {'b', 'a', 'b'}, {'a', 'b', 'a'}})
//                .build();
//
//        Matrix savedMatrix = matrixRepository.save(matrix);
//
//        assertThat(savedMatrix).isNotNull();
//        assertThat(savedMatrix.getId()).isNotNull();
//    }
//
//    @Test
//    public void testFindById() {
//        Matrix matrix = Matrix.builder()
//                .matrix(new char[][]{{'a', 'b', 'a'}, {'b', 'a', 'b'}, {'a', 'b', 'a'}})
//                .build();
//
//        Matrix savedMatrix = entityManager.persist(matrix);
//        entityManager.flush();
//
//        Optional<Matrix> foundMatrix = matrixRepository.findById(savedMatrix.getId());
//
//        assertThat(foundMatrix).isPresent();
//        assertThat(foundMatrix.get().getId()).isEqualTo(savedMatrix.getId());
//    }
//
//    @Test
//    public void testFindAll() {
//        Matrix matrix1 = Matrix.builder()
//                .matrix(new char[][]{{'a', 'b', 'a'}, {'b', 'a', 'b'}, {'a', 'b', 'a'}})
//                .build();
//
//        Matrix matrix2 = Matrix.builder()
//                .matrix(new char[][]{{'a', 'b', 'a'}, {'b', 'a', 'b'}, {'a', 'b', 'a'}})
//                .build();
//
//        entityManager.persist(matrix1);
//        entityManager.persist(matrix2);
//        entityManager.flush();
//
//        List<Matrix> matrices = matrixRepository.findAll();
//
//        assertThat(matrices).hasSize(2);
//    }
//}