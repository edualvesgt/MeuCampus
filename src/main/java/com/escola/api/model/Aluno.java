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

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_aluno")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_aluno", columnDefinition = "BINARY(16)")
    private UUID idAluno;
    
    @Column(nullable = false, unique = true)
    private String matricula;
    
    @Column(name = "data_ingresso")
    private LocalDate dataIngresso;
    
    @Column(nullable = false)
    private String situacao = "Ativo";
    
    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private Usuario usuario;
    
    @OneToMany(mappedBy = "aluno")
    @JsonIgnore
    private List<Matricula> matriculas;
}
