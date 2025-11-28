package com.escola.api.service;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Materia;
import com.escola.api.repository.MateriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MateriaService {
    
    private final MateriaRepository materiaRepository;
    
    public Materia criar(Materia materia) {
        return materiaRepository.save(materia);
    }
    
    public List<Materia> listarTodos() {
        return materiaRepository.findAll();
    }
    
    public Materia buscarPorId(UUID id) {
        return materiaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Materia não encontrada com id: " + id));
    }
    
    public Materia atualizar(UUID id, Materia materiaAtualizada) {
        Materia materia = buscarPorId(id);
        materia.setNome(materiaAtualizada.getNome());
        materia.setDescricao(materiaAtualizada.getDescricao());
        return materiaRepository.save(materia);
    }
    
    public void deletar(UUID id) {
        Materia materia = buscarPorId(id);
        materiaRepository.delete(materia);
    }
}
