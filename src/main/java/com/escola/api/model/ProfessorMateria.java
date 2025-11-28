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
@Table(name = "tb_professor_materia",
       uniqueConstraints = @UniqueConstraint(columnNames = {"id_materia", "id_professor"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorMateria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_professor_materia", columnDefinition = "BINARY(16)")
    private UUID idProfessorMateria;
    
    @ManyToOne
    @JoinColumn(name = "id_materia", nullable = false)
    private Materia materia;
    
    @ManyToOne
    @JoinColumn(name = "id_professor", nullable = false)
    private Professor professor;
}
