package br.com.bradesco.challenge.infra.service;

import br.com.bradesco.challenge.domain.model.Matrix;
import br.com.bradesco.challenge.domain.repository.IMatrixRepository;
import br.com.bradesco.challenge.domain.service.IMatrixService;
import br.com.bradesco.challenge.domain.service.IMatrixValidationService;
import br.com.bradesco.challenge.domain.service.IPalindromeFindingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MatrixServiceImpl implements IMatrixService {

    private final IMatrixRepository repository;

    private final IPalindromeFindingService palindromeFindingService;

    private final IMatrixValidationService matrixValidationService;

    @Override
    public Matrix save(Matrix matrix) {
        matrixValidationService.validate(matrix);
        palindromeFindingService.findPalindromesInMatrix(matrix);
        return repository.save(matrix);
    }

    @Override
    public Optional<Matrix> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<Matrix> findAll() {
        return repository.findAll();
    }
}
