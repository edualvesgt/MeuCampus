package com.escola.api.controller;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.InscricaoEvento;
import com.escola.api.service.InscricaoEventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/inscricoes-evento")
@RequiredArgsConstructor
public class InscricaoEventoController {
    
    private final InscricaoEventoService inscricaoEventoService;
    
    @PostMapping
    public ResponseEntity<InscricaoEvento> criar(@RequestBody InscricaoEvento inscricaoEvento) {
        InscricaoEvento novaInscricaoEvento = inscricaoEventoService.criar(inscricaoEvento);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaInscricaoEvento);
    }
    
    @GetMapping
    public ResponseEntity<List<InscricaoEvento>> listarTodos() {
        List<InscricaoEvento> inscricoesEvento = inscricaoEventoService.listarTodos();
        return ResponseEntity.ok(inscricoesEvento);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<InscricaoEvento> buscarPorId(@PathVariable UUID id) {
        InscricaoEvento inscricaoEvento = inscricaoEventoService.buscarPorId(id);
        return ResponseEntity.ok(inscricaoEvento);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<InscricaoEvento> atualizar(@PathVariable UUID id, @RequestBody InscricaoEvento inscricaoEvento) {
        InscricaoEvento inscricaoEventoAtualizada = inscricaoEventoService.atualizar(id, inscricaoEvento);
        return ResponseEntity.ok(inscricaoEventoAtualizada);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        inscricaoEventoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
