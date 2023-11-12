package br.com.bradesco.challenge.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Column;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "matrix")
public class Matrix {
    @Id
    @GeneratedValue
    private UUID id;

    @Transient
    private char[][] matrix;

    @Column(columnDefinition = "TEXT")
    private String matrixAsString;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @JsonManagedReference
    @OneToMany(mappedBy = "matrix", cascade = CascadeType.ALL)
    private List<Palindrome> palindromes = new ArrayList<>();

    @PrePersist
    @PreUpdate
    public void convertMatrixToString() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            this.matrixAsString = objectMapper.writeValueAsString(this.matrix);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting matrix to string", e);
        }
    }

    @PostLoad
    public void convertStringToMatrix() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            this.matrix = objectMapper.readValue(this.matrixAsString, TypeFactory.defaultInstance().constructArrayType(char[].class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting string to matrix", e);
        }
    }
}
