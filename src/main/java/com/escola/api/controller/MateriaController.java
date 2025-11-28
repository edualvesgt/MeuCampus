package com.escola.api.controller;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Materia;
import com.escola.api.service.MateriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/materias")
@RequiredArgsConstructor
public class MateriaController {
    
    private final MateriaService materiaService;
    
    @PostMapping
    public ResponseEntity<Materia> criar(@RequestBody Materia materia) {
        Materia novaMateria = materiaService.criar(materia);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMateria);
    }
    
    @GetMapping
    public ResponseEntity<List<Materia>> listarTodos() {
        List<Materia> materias = materiaService.listarTodos();
        return ResponseEntity.ok(materias);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Materia> buscarPorId(@PathVariable UUID id) {
        Materia materia = materiaService.buscarPorId(id);
        return ResponseEntity.ok(materia);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Materia> atualizar(@PathVariable UUID id, @RequestBody Materia materia) {
        Materia materiaAtualizada = materiaService.atualizar(id, materia);
        return ResponseEntity.ok(materiaAtualizada);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        materiaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
