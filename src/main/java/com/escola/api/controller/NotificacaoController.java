package com.escola.api.controller;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Notificacao;
import com.escola.api.service.NotificacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notificacoes")
@RequiredArgsConstructor
public class NotificacaoController {
    
    private final NotificacaoService notificacaoService;
    
    @PostMapping
    public ResponseEntity<Notificacao> criar(@RequestBody Notificacao notificacao) {
        Notificacao novaNotificacao = notificacaoService.criar(notificacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaNotificacao);
    }
    
    @GetMapping
    public ResponseEntity<List<Notificacao>> listarTodos() {
        List<Notificacao> notificacoes = notificacaoService.listarTodos();
        return ResponseEntity.ok(notificacoes);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Notificacao> buscarPorId(@PathVariable UUID id) {
        Notificacao notificacao = notificacaoService.buscarPorId(id);
        return ResponseEntity.ok(notificacao);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Notificacao> atualizar(@PathVariable UUID id, @RequestBody Notificacao notificacao) {
        Notificacao notificacaoAtualizada = notificacaoService.atualizar(id, notificacao);
        return ResponseEntity.ok(notificacaoAtualizada);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        notificacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
