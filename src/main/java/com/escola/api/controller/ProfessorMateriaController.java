package com.escola.api.controller;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.ProfessorMateria;
import com.escola.api.service.ProfessorMateriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/professor-materias")
@RequiredArgsConstructor
public class ProfessorMateriaController {
    
    private final ProfessorMateriaService professorMateriaService;
    
    @PostMapping
    public ResponseEntity<ProfessorMateria> criar(@RequestBody ProfessorMateria professorMateria) {
        ProfessorMateria novoProfessorMateria = professorMateriaService.criar(professorMateria);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProfessorMateria);
    }
    
    @GetMapping
    public ResponseEntity<List<ProfessorMateria>> listarTodos() {
        List<ProfessorMateria> professorMaterias = professorMateriaService.listarTodos();
        return ResponseEntity.ok(professorMaterias);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProfessorMateria> buscarPorId(@PathVariable UUID id) {
        ProfessorMateria professorMateria = professorMateriaService.buscarPorId(id);
        return ResponseEntity.ok(professorMateria);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProfessorMateria> atualizar(@PathVariable UUID id, @RequestBody ProfessorMateria professorMateria) {
        ProfessorMateria professorMateriaAtualizada = professorMateriaService.atualizar(id, professorMateria);
        return ResponseEntity.ok(professorMateriaAtualizada);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        professorMateriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
