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

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_materia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Materia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_materia", columnDefinition = "BINARY(16)")
    private UUID idMateria;
    
    @Column(nullable = false)
    private String nome;
    
    private String descricao;
    
    @OneToMany(mappedBy = "materia")
    @JsonIgnore
    private List<Turma> turmas;
    
    @OneToMany(mappedBy = "materia")
    @JsonIgnore
    private List<ProfessorMateria> professorMaterias;
}
