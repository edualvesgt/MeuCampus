package com.escola.api.service;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Usuario;
import com.escola.api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    
    public Usuario criar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }
    
    public Usuario buscarPorId(UUID id) {
        return usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario não encontrado com id: " + id));
    }
    
    public Usuario atualizar(UUID id, Usuario usuarioAtualizado) {
        Usuario usuario = buscarPorId(id);
        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());
        usuario.setSenha(usuarioAtualizado.getSenha());
        usuario.setCelular(usuarioAtualizado.getCelular());
        return usuarioRepository.save(usuario);
    }
    
    public void deletar(UUID id) {
        Usuario usuario = buscarPorId(id);
        usuarioRepository.delete(usuario);
    }
    
    // Método para autenticação
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Usuario autenticar(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmailAndSenha(email, senha)
            .orElse(null);
        
        if (usuario != null) {
            // Forçar carregamento dos relacionamentos
            if (usuario.getProfessor() != null) {
                usuario.getProfessor().getIdProfessor(); // Força o carregamento
            }
            if (usuario.getAluno() != null) {
                usuario.getAluno().getIdAluno(); // Força o carregamento
            }
        }
        
        return usuario;
    }
    
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
            .orElse(null);
    }
}
