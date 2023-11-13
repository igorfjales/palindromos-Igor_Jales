package br.com.bradesco.challenge.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "palindrome")
public class Palindrome {
    @Id
    @GeneratedValue
    private UUID id;

    private String palindrome;

    @ManyToOne
    @JoinColumn(name = "matrix_id")
    @JsonBackReference
    private Matrix matrix;
}
