package com.escola.api.service;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Notificacao;
import com.escola.api.repository.NotificacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificacaoService {
    
    private final NotificacaoRepository notificacaoRepository;
    
    public Notificacao criar(Notificacao notificacao) {
        return notificacaoRepository.save(notificacao);
    }
    
    @Transactional(readOnly = true)
    public List<Notificacao> listarTodos() {
        return notificacaoRepository.findAll();
    }
    
    public Notificacao buscarPorId(UUID id) {
        return notificacaoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Notificacao não encontrada com id: " + id));
    }
    
    public Notificacao atualizar(UUID id, Notificacao notificacaoAtualizada) {
        Notificacao notificacao = buscarPorId(id);
        notificacao.setTitulo(notificacaoAtualizada.getTitulo());
        notificacao.setConteudo(notificacaoAtualizada.getConteudo());
        notificacao.setStatus(notificacaoAtualizada.getStatus());
        notificacao.setTurma(notificacaoAtualizada.getTurma());
        notificacao.setProfessor(notificacaoAtualizada.getProfessor());
        return notificacaoRepository.save(notificacao);
    }
    
    public void deletar(UUID id) {
        Notificacao notificacao = buscarPorId(id);
        notificacaoRepository.delete(notificacao);
    }
}
