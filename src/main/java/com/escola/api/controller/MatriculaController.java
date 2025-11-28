package com.escola.api.controller;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Matricula;
import com.escola.api.service.MatriculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/matriculas")
@RequiredArgsConstructor
public class MatriculaController {
    
    private final MatriculaService matriculaService;
    
    @PostMapping
    public ResponseEntity<Matricula> criar(@RequestBody Matricula matricula) {
        Matricula novaMatricula = matriculaService.criar(matricula);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMatricula);
    }
    
    @GetMapping
    public ResponseEntity<List<Matricula>> listarTodos() {
        List<Matricula> matriculas = matriculaService.listarTodos();
        return ResponseEntity.ok(matriculas);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Matricula> buscarPorId(@PathVariable UUID id) {
        Matricula matricula = matriculaService.buscarPorId(id);
        return ResponseEntity.ok(matricula);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Matricula> atualizar(@PathVariable UUID id, @RequestBody Matricula matricula) {
        Matricula matriculaAtualizada = matriculaService.atualizar(id, matricula);
        return ResponseEntity.ok(matriculaAtualizada);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        matriculaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
