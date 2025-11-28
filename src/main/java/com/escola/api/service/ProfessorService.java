package com.escola.api.service;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Professor;
import com.escola.api.model.Turma;
import com.escola.api.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    
    @Transactional(readOnly = true)
    public Professor buscarPorId(UUID id) {
        Professor professor = professorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Professor não encontrado com id: " + id));
        
        // Forçar carregamento das turmas
        if (professor.getTurmas() != null) {
            professor.getTurmas().size();
            // Forçar carregamento das matrículas de cada turma
            professor.getTurmas().forEach(turma -> {
                if (turma.getMatriculas() != null) {
                    turma.getMatriculas().size();
                }
            });
        }
        
        return professor;
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
