package com.escola.api.controller;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Professor;
import com.escola.api.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/professores")
@RequiredArgsConstructor
public class ProfessorController {
    
    private final ProfessorService professorService;
    
    @PostMapping
    public ResponseEntity<Professor> criar(@RequestBody Professor professor) {
        Professor novoProfessor = professorService.criar(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProfessor);
    }
    
    @GetMapping
    public ResponseEntity<List<Professor>> listarTodos() {
        List<Professor> professores = professorService.listarTodos();
        return ResponseEntity.ok(professores);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscarPorId(@PathVariable UUID id) {
        Professor professor = professorService.buscarPorId(id);
        return ResponseEntity.ok(professor);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizar(@PathVariable UUID id, @RequestBody Professor professor) {
        Professor professorAtualizado = professorService.atualizar(id, professor);
        return ResponseEntity.ok(professorAtualizado);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        professorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
