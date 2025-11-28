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
@Table(name = "tb_usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_usuario", columnDefinition = "BINARY(16)")
    private UUID idUsuario;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String senha;
    
    private String celular;
    
    @OneToOne(mappedBy = "usuario")
    @JsonIgnore
    private Professor professor;
    
    @OneToOne(mappedBy = "usuario")
    @JsonIgnore
    private Aluno aluno;
    
    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<InscricaoEvento> inscricoesEvento;
}
