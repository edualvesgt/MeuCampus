package com.escola.api.model;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_professor")
@Data
@ToString(exclude = {"turmas", "professorMaterias"})
@NoArgsConstructor
@AllArgsConstructor
public class Professor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_professor", columnDefinition = "BINARY(16)")
    private UUID idProfessor;
    
    private String formacao;
    
    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private Usuario usuario;
    
    @OneToMany(mappedBy = "professor")
    @JsonIgnore
    private List<Turma> turmas;
    
    @OneToMany(mappedBy = "professor")
    @JsonIgnore
    private List<ProfessorMateria> professorMaterias;
}
