package com.escola.api.service;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.InscricaoEvento;
import com.escola.api.repository.InscricaoEventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InscricaoEventoService {
    
    private final InscricaoEventoRepository inscricaoEventoRepository;
    
    public InscricaoEvento criar(InscricaoEvento inscricaoEvento) {
        return inscricaoEventoRepository.save(inscricaoEvento);
    }
    
    public List<InscricaoEvento> listarTodos() {
        return inscricaoEventoRepository.findAll();
    }
    
    public InscricaoEvento buscarPorId(UUID id) {
        return inscricaoEventoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("InscricaoEvento não encontrado com id: " + id));
    }
    
    public InscricaoEvento atualizar(UUID id, InscricaoEvento inscricaoEventoAtualizado) {
        InscricaoEvento inscricaoEvento = buscarPorId(id);
        inscricaoEvento.setUsuario(inscricaoEventoAtualizado.getUsuario());
        inscricaoEvento.setEvento(inscricaoEventoAtualizado.getEvento());
        return inscricaoEventoRepository.save(inscricaoEvento);
    }
    
    public void deletar(UUID id) {
        InscricaoEvento inscricaoEvento = buscarPorId(id);
        inscricaoEventoRepository.delete(inscricaoEvento);
    }
    
    public List<InscricaoEvento> buscarPorUsuario(UUID idUsuario) {
        return inscricaoEventoRepository.findAll().stream()
            .filter(i -> i.getUsuario().getIdUsuario().equals(idUsuario))
            .toList();
    }
    
    public boolean jaInscrito(UUID idUsuario, UUID idEvento) {
        return inscricaoEventoRepository.findAll().stream()
            .anyMatch(i -> i.getUsuario().getIdUsuario().equals(idUsuario) 
                        && i.getEvento().getIdEvento().equals(idEvento));
    }
}
