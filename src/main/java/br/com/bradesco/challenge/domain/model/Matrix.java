package br.com.bradesco.challenge.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.PostLoad;
import javax.persistence.Column;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
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
    private Instant createdAt;

    @Builder.Default
    @JsonManagedReference
    @OneToMany(mappedBy = "matrix", cascade = CascadeType.ALL)
    private List<Palindrome> palindromes = new ArrayList<>();

    @PrePersist
    @PreUpdate
    public void convertMatrixToString() {
        try {
            this.matrixAsString = new ObjectMapper().writeValueAsString(this.matrix);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting matrix to string", e);
        }
    }

    @PostLoad
    public void convertStringToMatrix() {
        try {
            this.matrix = new ObjectMapper().readValue(this.matrixAsString, TypeFactory.defaultInstance().constructArrayType(char[].class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting string to matrix", e);
        }
    }
}
