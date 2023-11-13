package br.com.bradesco.challenge.domain.service;

import br.com.bradesco.challenge.domain.model.Matrix;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IMatrixService {

    Matrix save(Matrix matrix);

    Optional<Matrix> findById(UUID id);

    List<Matrix> findAll();
}
