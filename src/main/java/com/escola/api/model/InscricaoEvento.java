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
@Table(name = "tb_inscricao_evento",
       uniqueConstraints = @UniqueConstraint(columnNames = {"id_usuario", "id_evento"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscricaoEvento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_inscricao_evento", columnDefinition = "BINARY(16)")
    private UUID idInscricaoEvento;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "id_evento", nullable = false)
    private Evento evento;
}
