package com.escola.api.service;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Professor;
import com.escola.api.model.Usuario;
import com.escola.api.repository.ProfessorRepository;
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
class ProfessorServiceTest {
    
    @Mock
    private ProfessorRepository professorRepository;
    
    @InjectMocks
    private ProfessorService professorService;
    
    private Professor professor;
    private Usuario usuario;
    private UUID professorId;
    
    @BeforeEach
    void setUp() {
        professorId = UUID.randomUUID();
        
        usuario = new Usuario();
        usuario.setIdUsuario(UUID.randomUUID());
        usuario.setNome("Carlos Silva");
        usuario.setEmail("carlos@email.com");
        
        professor = new Professor();
        professor.setIdProfessor(professorId);
        professor.setFormacao("Mestrado em Matemática");
        professor.setUsuario(usuario);
    }
    
    @Test
    void deveCriarProfessorComSucesso() {
        when(professorRepository.save(any(Professor.class))).thenReturn(professor);
        
        Professor resultado = professorService.criar(professor);
        
        assertNotNull(resultado);
        assertEquals("Mestrado em Matemática", resultado.getFormacao());
        assertEquals(usuario, resultado.getUsuario());
        verify(professorRepository, times(1)).save(professor);
    }
    
    @Test
    void deveListarTodosProfessores() {
        Professor professor2 = new Professor();
        professor2.setIdProfessor(UUID.randomUUID());
        professor2.setFormacao("Doutorado em Física");
        
        List<Professor> professores = Arrays.asList(professor, professor2);
        when(professorRepository.findAll()).thenReturn(professores);
        
        List<Professor> resultado = professorService.listarTodos();
        
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(professorRepository, times(1)).findAll();
    }
    
    @Test
    void deveBuscarProfessorPorIdComSucesso() {
        when(professorRepository.findById(professorId)).thenReturn(Optional.of(professor));
        
        Professor resultado = professorService.buscarPorId(professorId);
        
        assertNotNull(resultado);
        assertEquals(professorId, resultado.getIdProfessor());
        assertEquals("Mestrado em Matemática", resultado.getFormacao());
        verify(professorRepository, times(1)).findById(professorId);
    }
    
    @Test
    void deveLancarExcecaoQuandoProfessorNaoEncontrado() {
        UUID idInexistente = UUID.randomUUID();
        when(professorRepository.findById(idInexistente)).thenReturn(Optional.empty());
        
        RuntimeException exception = assertThrows(RuntimeException.class, 
            () -> professorService.buscarPorId(idInexistente));
        
        assertTrue(exception.getMessage().contains("Professor não encontrado com id"));
        verify(professorRepository, times(1)).findById(idInexistente);
    }
    
    @Test
    void deveAtualizarProfessorComSucesso() {
        Usuario novoUsuario = new Usuario();
        novoUsuario.setIdUsuario(UUID.randomUUID());
        novoUsuario.setNome("Carlos Silva Atualizado");
        
        Professor professorAtualizado = new Professor();
        professorAtualizado.setFormacao("Doutorado em Matemática");
        professorAtualizado.setUsuario(novoUsuario);
        
        when(professorRepository.findById(professorId)).thenReturn(Optional.of(professor));
        when(professorRepository.save(any(Professor.class))).thenReturn(professor);
        
        Professor resultado = professorService.atualizar(professorId, professorAtualizado);
        
        assertNotNull(resultado);
        assertEquals("Doutorado em Matemática", resultado.getFormacao());
        assertEquals(novoUsuario, resultado.getUsuario());
        verify(professorRepository, times(1)).findById(professorId);
        verify(professorRepository, times(1)).save(professor);
    }
    
    @Test
    void deveDeletarProfessorComSucesso() {
        when(professorRepository.findById(professorId)).thenReturn(Optional.of(professor));
        doNothing().when(professorRepository).delete(professor);
        
        professorService.deletar(professorId);
        
        verify(professorRepository, times(1)).findById(professorId);
        verify(professorRepository, times(1)).delete(professor);
    }
    
    @Test
    void deveLancarExcecaoAoDeletarProfessorInexistente() {
        UUID idInexistente = UUID.randomUUID();
        when(professorRepository.findById(idInexistente)).thenReturn(Optional.empty());
        
        assertThrows(RuntimeException.class, () -> professorService.deletar(idInexistente));
        
        verify(professorRepository, times(1)).findById(idInexistente);
        verify(professorRepository, never()).delete(any());
    }
}
