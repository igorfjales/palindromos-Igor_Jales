package br.com.bradesco.challenge.infra.repository;

import br.com.bradesco.challenge.domain.model.Matrix;
import br.com.bradesco.challenge.domain.repository.IMatrixRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class MatrixRepositoryImpl implements IMatrixRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public MatrixRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Matrix save(Matrix matrix) {
        entityManager.persist(matrix);
        return matrix;
    }

    @Override
    public Optional<Matrix> findById(UUID id) {
        Matrix matrix = entityManager.find(Matrix.class, id);
        return matrix != null ? Optional.of(matrix) : Optional.empty();
    }

    @Override
    public List<Matrix> findAll() {
        return entityManager.createQuery("from Matrix", Matrix.class).getResultList();
    }
}
