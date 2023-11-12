package br.com.bradesco.challenge.domain.repository;



import br.com.bradesco.challenge.domain.entity.Matrix;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IMatrixRepository {

    Matrix save(Matrix matrix);

    Optional<Matrix> findById(UUID id);

    List<Matrix> findAll();
}
