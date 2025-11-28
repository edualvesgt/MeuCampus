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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {
    
    @Mock
    private UsuarioRepository usuarioRepository;
    
    @InjectMocks
    private UsuarioService usuarioService;
    
    private Usuario usuario;
    private UUID usuarioId;
    
    @BeforeEach
    void setUp() {
        usuarioId = UUID.randomUUID();
        usuario = new Usuario();
        usuario.setIdUsuario(usuarioId);
        usuario.setNome("João Silva");
        usuario.setEmail("joao@email.com");
        usuario.setSenha("senha123");
        usuario.setCelular("11999999999");
    }
    
    @Test
    void deveCriarUsuarioComSucesso() {
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        
        Usuario resultado = usuarioService.criar(usuario);
        
        assertNotNull(resultado);
        assertEquals("João Silva", resultado.getNome());
        assertEquals("joao@email.com", resultado.getEmail());
        verify(usuarioRepository, times(1)).save(usuario);
    }
    
    @Test
    void deveListarTodosUsuarios() {
        Usuario usuario2 = new Usuario();
        usuario2.setIdUsuario(UUID.randomUUID());
        usuario2.setNome("Maria Santos");
        usuario2.setEmail("maria@email.com");
        
        List<Usuario> usuarios = Arrays.asList(usuario, usuario2);
        when(usuarioRepository.findAll()).thenReturn(usuarios);
        
        List<Usuario> resultado = usuarioService.listarTodos();
        
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(usuarioRepository, times(1)).findAll();
    }
    
    @Test
    void deveBuscarUsuarioPorIdComSucesso() {
        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));
        
        Usuario resultado = usuarioService.buscarPorId(usuarioId);
        
        assertNotNull(resultado);
        assertEquals(usuarioId, resultado.getIdUsuario());
        assertEquals("João Silva", resultado.getNome());
        verify(usuarioRepository, times(1)).findById(usuarioId);
    }
    
    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        UUID idInexistente = UUID.randomUUID();
        when(usuarioRepository.findById(idInexistente)).thenReturn(Optional.empty());
        
        RuntimeException exception = assertThrows(RuntimeException.class, 
            () -> usuarioService.buscarPorId(idInexistente));
        
        assertTrue(exception.getMessage().contains("Usuario não encontrado com id"));
        verify(usuarioRepository, times(1)).findById(idInexistente);
    }
    
    @Test
    void deveAtualizarUsuarioComSucesso() {
        Usuario usuarioAtualizado = new Usuario();
        usuarioAtualizado.setNome("João Silva Atualizado");
        usuarioAtualizado.setEmail("joao.novo@email.com");
        usuarioAtualizado.setSenha("novaSenha123");
        usuarioAtualizado.setCelular("11988888888");
        
        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        
        Usuario resultado = usuarioService.atualizar(usuarioId, usuarioAtualizado);
        
        assertNotNull(resultado);
        assertEquals("João Silva Atualizado", resultado.getNome());
        assertEquals("joao.novo@email.com", resultado.getEmail());
        assertEquals("novaSenha123", resultado.getSenha());
        assertEquals("11988888888", resultado.getCelular());
        verify(usuarioRepository, times(1)).findById(usuarioId);
        verify(usuarioRepository, times(1)).save(usuario);
    }
    
    @Test
    void deveDeletarUsuarioComSucesso() {
        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));
        doNothing().when(usuarioRepository).delete(usuario);
        
        usuarioService.deletar(usuarioId);
        
        verify(usuarioRepository, times(1)).findById(usuarioId);
        verify(usuarioRepository, times(1)).delete(usuario);
    }
    
    @Test
    void deveLancarExcecaoAoDeletarUsuarioInexistente() {
        UUID idInexistente = UUID.randomUUID();
        when(usuarioRepository.findById(idInexistente)).thenReturn(Optional.empty());
        
        assertThrows(RuntimeException.class, () -> usuarioService.deletar(idInexistente));
        
        verify(usuarioRepository, times(1)).findById(idInexistente);
        verify(usuarioRepository, never()).delete(any());
    }
}
