package com.escola.api.service;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Professor;
import com.escola.api.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfessorService {
    
    private final ProfessorRepository professorRepository;
    
    public Professor criar(Professor professor) {
        return professorRepository.save(professor);
    }
    
    public List<Professor> listarTodos() {
        return professorRepository.findAll();
    }
    
    public Professor buscarPorId(UUID id) {
        return professorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Professor não encontrado com id: " + id));
    }
    
    public Professor atualizar(UUID id, Professor professorAtualizado) {
        Professor professor = buscarPorId(id);
        professor.setFormacao(professorAtualizado.getFormacao());
        professor.setUsuario(professorAtualizado.getUsuario());
        return professorRepository.save(professor);
    }
    
    public void deletar(UUID id) {
        Professor professor = buscarPorId(id);
        professorRepository.delete(professor);
    }
}
