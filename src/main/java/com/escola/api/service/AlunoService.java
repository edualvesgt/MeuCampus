package com.escola.api.service;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Aluno;
import com.escola.api.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AlunoService {
    
    private final AlunoRepository alunoRepository;
    
    public Aluno criar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }
    
    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Aluno buscarPorId(UUID id) {
        Aluno aluno = alunoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Aluno não encontrado com id: " + id));
        // Forçar carregamento das matrículas
        if (aluno.getMatriculas() != null) {
            aluno.getMatriculas().size();
        }
        return aluno;
    }
    
    public Aluno atualizar(UUID id, Aluno alunoAtualizado) {
        Aluno aluno = buscarPorId(id);
        aluno.setMatricula(alunoAtualizado.getMatricula());
        aluno.setDataIngresso(alunoAtualizado.getDataIngresso());
        aluno.setSituacao(alunoAtualizado.getSituacao());
        aluno.setUsuario(alunoAtualizado.getUsuario());
        return alunoRepository.save(aluno);
    }
    
    public void deletar(UUID id) {
        Aluno aluno = buscarPorId(id);
        alunoRepository.delete(aluno);
    }
}
