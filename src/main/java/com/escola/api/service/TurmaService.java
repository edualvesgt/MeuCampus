package com.escola.api.service;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Turma;
import com.escola.api.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TurmaService {
    
    private final TurmaRepository turmaRepository;
    
    public Turma criar(Turma turma) {
        return turmaRepository.save(turma);
    }
    
    public List<Turma> listarTodos() {
        return turmaRepository.findAll();
    }
    
    public Turma buscarPorId(UUID id) {
        return turmaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Turma não encontrada com id: " + id));
    }
    
    public Turma atualizar(UUID id, Turma turmaAtualizada) {
        Turma turma = buscarPorId(id);
        turma.setNome(turmaAtualizada.getNome());
        turma.setProfessor(turmaAtualizada.getProfessor());
        turma.setMateria(turmaAtualizada.getMateria());
        return turmaRepository.save(turma);
    }
    
    public void deletar(UUID id) {
        Turma turma = buscarPorId(id);
        turmaRepository.delete(turma);
    }
}
