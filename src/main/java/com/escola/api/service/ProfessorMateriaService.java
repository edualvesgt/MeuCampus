package com.escola.api.service;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.ProfessorMateria;
import com.escola.api.repository.ProfessorMateriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfessorMateriaService {
    
    private final ProfessorMateriaRepository professorMateriaRepository;
    
    public ProfessorMateria criar(ProfessorMateria professorMateria) {
        return professorMateriaRepository.save(professorMateria);
    }
    
    public List<ProfessorMateria> listarTodos() {
        return professorMateriaRepository.findAll();
    }
    
    public ProfessorMateria buscarPorId(UUID id) {
        return professorMateriaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("ProfessorMateria não encontrado com id: " + id));
    }
    
    public ProfessorMateria atualizar(UUID id, ProfessorMateria professorMateriaAtualizado) {
        ProfessorMateria professorMateria = buscarPorId(id);
        professorMateria.setMateria(professorMateriaAtualizado.getMateria());
        professorMateria.setProfessor(professorMateriaAtualizado.getProfessor());
        return professorMateriaRepository.save(professorMateria);
    }
    
    public void deletar(UUID id) {
        ProfessorMateria professorMateria = buscarPorId(id);
        professorMateriaRepository.delete(professorMateria);
    }
}
