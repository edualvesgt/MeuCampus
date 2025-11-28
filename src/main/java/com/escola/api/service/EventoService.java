package com.escola.api.service;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Evento;
import com.escola.api.repository.EventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventoService {
    
    private final EventoRepository eventoRepository;
    
    public Evento criar(Evento evento) {
        return eventoRepository.save(evento);
    }
    
    @Transactional(readOnly = true)
    public List<Evento> listarTodos() {
        return eventoRepository.findAll();
    }
    
    public Evento buscarPorId(UUID id) {
        return eventoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Evento não encontrado com id: " + id));
    }
    
    public Evento atualizar(UUID id, Evento eventoAtualizado) {
        Evento evento = buscarPorId(id);
        evento.setNome(eventoAtualizado.getNome());
        evento.setData(eventoAtualizado.getData());
        evento.setLocal(eventoAtualizado.getLocal());
        evento.setInformacoes(eventoAtualizado.getInformacoes());
        evento.setProfessor(eventoAtualizado.getProfessor());
        evento.setTurma(eventoAtualizado.getTurma());
        return eventoRepository.save(evento);
    }
    
    public void deletar(UUID id) {
        Evento evento = buscarPorId(id);
        eventoRepository.delete(evento);
    }
}
