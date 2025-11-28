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
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_notificacao")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_notificacao", columnDefinition = "BINARY(16)")
    private UUID idNotificacao;
    
    @Column(nullable = false)
    private String titulo;
    
    @Column(columnDefinition = "TEXT")
    private String conteudo;
    
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime data;
    
    @Column(nullable = false)
    private String status = "Nao lida";
    
    @ManyToOne
    @JoinColumn(name = "id_turma")
    private Turma turma;
    
    @ManyToOne
    @JoinColumn(name = "id_professor")
    private Professor professor;
}
