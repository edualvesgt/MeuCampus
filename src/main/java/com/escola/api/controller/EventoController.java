package com.escola.api.controller;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Evento;
import com.escola.api.service.EventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/eventos")
@RequiredArgsConstructor
public class EventoController {
    
    private final EventoService eventoService;
    
    @PostMapping
    public ResponseEntity<Evento> criar(@RequestBody Evento evento) {
        Evento novoEvento = eventoService.criar(evento);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEvento);
    }
    
    @GetMapping
    public ResponseEntity<List<Evento>> listarTodos() {
        List<Evento> eventos = eventoService.listarTodos();
        return ResponseEntity.ok(eventos);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Evento> buscarPorId(@PathVariable UUID id) {
        Evento evento = eventoService.buscarPorId(id);
        return ResponseEntity.ok(evento);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Evento> atualizar(@PathVariable UUID id, @RequestBody Evento evento) {
        Evento eventoAtualizado = eventoService.atualizar(id, evento);
        return ResponseEntity.ok(eventoAtualizado);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        eventoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
