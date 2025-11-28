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
@Table(name = "tb_turma")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Turma {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_turma", columnDefinition = "BINARY(16)")
    private UUID idTurma;
    
    @Column(nullable = false)
    private String nome;
    
    @ManyToOne
    @JoinColumn(name = "id_professor", nullable = false)
    private Professor professor;
    
    @ManyToOne
    @JoinColumn(name = "id_materia", nullable = false)
    private Materia materia;
    
    @OneToMany(mappedBy = "turma")
    @JsonIgnore
    private List<Matricula> matriculas;
    
    @OneToMany(mappedBy = "turma")
    @JsonIgnore
    private List<Evento> eventos;
    
    @OneToMany(mappedBy = "turma")
    @JsonIgnore
    private List<Notificacao> notificacoes;
}
