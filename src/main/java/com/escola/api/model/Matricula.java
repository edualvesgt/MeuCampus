package com.escola.api.model;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tb_matricula",
       uniqueConstraints = @UniqueConstraint(columnNames = {"id_turma", "id_aluno"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Matricula {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_matricula", columnDefinition = "BINARY(16)")
    private UUID idMatricula;
    
    @ManyToOne
    @JoinColumn(name = "id_turma", nullable = false)
    private Turma turma;
    
    @ManyToOne
    @JoinColumn(name = "id_aluno", nullable = false)
    private Aluno aluno;
}
