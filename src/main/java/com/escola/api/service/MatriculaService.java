package com.escola.api.service;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Matricula;
import com.escola.api.repository.MatriculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MatriculaService {
    
    private final MatriculaRepository matriculaRepository;
    
    public Matricula criar(Matricula matricula) {
        return matriculaRepository.save(matricula);
    }
    
    public List<Matricula> listarTodos() {
        return matriculaRepository.findAll();
    }
    
    public Matricula buscarPorId(UUID id) {
        return matriculaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Matricula não encontrada com id: " + id));
    }
    
    public Matricula atualizar(UUID id, Matricula matriculaAtualizada) {
        Matricula matricula = buscarPorId(id);
        matricula.setTurma(matriculaAtualizada.getTurma());
        matricula.setAluno(matriculaAtualizada.getAluno());
        return matriculaRepository.save(matricula);
    }
    
    public void deletar(UUID id) {
        Matricula matricula = buscarPorId(id);
        matriculaRepository.delete(matricula);
    }
}
