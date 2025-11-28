package com.escola.api.controller;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Turma;
import com.escola.api.service.TurmaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/turmas")
@RequiredArgsConstructor
public class TurmaController {
    
    private final TurmaService turmaService;
    
    @PostMapping
    public ResponseEntity<Turma> criar(@RequestBody Turma turma) {
        Turma novaTurma = turmaService.criar(turma);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTurma);
    }
    
    @GetMapping
    public ResponseEntity<List<Turma>> listarTodos() {
        List<Turma> turmas = turmaService.listarTodos();
        return ResponseEntity.ok(turmas);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Turma> buscarPorId(@PathVariable UUID id) {
        Turma turma = turmaService.buscarPorId(id);
        return ResponseEntity.ok(turma);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Turma> atualizar(@PathVariable UUID id, @RequestBody Turma turma) {
        Turma turmaAtualizada = turmaService.atualizar(id, turma);
        return ResponseEntity.ok(turmaAtualizada);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        turmaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
