package com.escola.api.service;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Aluno;
import com.escola.api.model.Usuario;
import com.escola.api.repository.AlunoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlunoServiceTest {
    
    @Mock
    private AlunoRepository alunoRepository;
    
    @InjectMocks
    private AlunoService alunoService;
    
    private Aluno aluno;
    private UUID alunoId;
    private Usuario usuario;
    
    @BeforeEach
    void setUp() {
        alunoId = UUID.randomUUID();
        
        usuario = new Usuario();
        usuario.setIdUsuario(UUID.randomUUID());
        usuario.setNome("João Silva");
        usuario.setEmail("joao@email.com");
        
        aluno = new Aluno();
        aluno.setIdAluno(alunoId);
        aluno.setMatricula("2024001");
        aluno.setDataIngresso(LocalDate.of(2024, 1, 15));
        aluno.setSituacao("Ativo");
        aluno.setUsuario(usuario);
    }
    
    @Test
    void deveCriarAlunoComSucesso() {
        when(alunoRepository.save(any(Aluno.class))).thenReturn(aluno);
        
        Aluno resultado = alunoService.criar(aluno);
        
        assertNotNull(resultado);
        assertEquals("2024001", resultado.getMatricula());
        assertEquals("Ativo", resultado.getSituacao());
        assertEquals(LocalDate.of(2024, 1, 15), resultado.getDataIngresso());
        verify(alunoRepository, times(1)).save(aluno);
    }
    
    @Test
    void deveListarTodosAlunos() {
        Aluno aluno2 = new Aluno();
        aluno2.setIdAluno(UUID.randomUUID());
        aluno2.setMatricula("2024002");
        aluno2.setSituacao("Ativo");
        
        List<Aluno> alunos = Arrays.asList(aluno, aluno2);
        when(alunoRepository.findAll()).thenReturn(alunos);
        
        List<Aluno> resultado = alunoService.listarTodos();
        
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(alunoRepository, times(1)).findAll();
    }
    
    @Test
    void deveBuscarAlunoPorIdComSucesso() {
        when(alunoRepository.findById(alunoId)).thenReturn(Optional.of(aluno));
        
        Aluno resultado = alunoService.buscarPorId(alunoId);
        
        assertNotNull(resultado);
        assertEquals(alunoId, resultado.getIdAluno());
        assertEquals("2024001", resultado.getMatricula());
        verify(alunoRepository, times(1)).findById(alunoId);
    }
    
    @Test
    void deveLancarExcecaoQuandoAlunoNaoEncontrado() {
        UUID idInexistente = UUID.randomUUID();
        when(alunoRepository.findById(idInexistente)).thenReturn(Optional.empty());
        
        RuntimeException exception = assertThrows(RuntimeException.class, 
            () -> alunoService.buscarPorId(idInexistente));
        
        assertTrue(exception.getMessage().contains("Aluno não encontrado com id"));
        verify(alunoRepository, times(1)).findById(idInexistente);
    }
    
    @Test
    void deveAtualizarAlunoComSucesso() {
        Aluno alunoAtualizado = new Aluno();
        alunoAtualizado.setMatricula("2024001-UPD");
        alunoAtualizado.setDataIngresso(LocalDate.of(2024, 2, 1));
        alunoAtualizado.setSituacao("Inativo");
        alunoAtualizado.setUsuario(usuario);
        
        when(alunoRepository.findById(alunoId)).thenReturn(Optional.of(aluno));
        when(alunoRepository.save(any(Aluno.class))).thenReturn(aluno);
        
        Aluno resultado = alunoService.atualizar(alunoId, alunoAtualizado);
        
        assertNotNull(resultado);
        assertEquals("2024001-UPD", resultado.getMatricula());
        assertEquals(LocalDate.of(2024, 2, 1), resultado.getDataIngresso());
        assertEquals("Inativo", resultado.getSituacao());
        verify(alunoRepository, times(1)).findById(alunoId);
        verify(alunoRepository, times(1)).save(aluno);
    }
    
    @Test
    void deveDeletarAlunoComSucesso() {
        when(alunoRepository.findById(alunoId)).thenReturn(Optional.of(aluno));
        doNothing().when(alunoRepository).delete(aluno);
        
        alunoService.deletar(alunoId);
        
        verify(alunoRepository, times(1)).findById(alunoId);
        verify(alunoRepository, times(1)).delete(aluno);
    }
    
    @Test
    void deveLancarExcecaoAoDeletarAlunoInexistente() {
        UUID idInexistente = UUID.randomUUID();
        when(alunoRepository.findById(idInexistente)).thenReturn(Optional.empty());
        
        assertThrows(RuntimeException.class, () -> alunoService.deletar(idInexistente));
        
        verify(alunoRepository, times(1)).findById(idInexistente);
        verify(alunoRepository, never()).delete(any());
    }
}
