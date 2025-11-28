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

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_evento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_evento", columnDefinition = "BINARY(16)")
    private UUID idEvento;
    
    @Column(nullable = false)
    private String nome;
    
    private LocalDateTime data;
    
    private String local;
    
    @Column(columnDefinition = "TEXT")
    private String informacoes;
    
    @ManyToOne
    @JoinColumn(name = "id_professor")
    private Professor professor;
    
    @ManyToOne
    @JoinColumn(name = "id_turma")
    private Turma turma;
    
    @OneToMany(mappedBy = "evento")
    @JsonIgnore
    private List<InscricaoEvento> inscricoes;
}
