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
class MateriaServiceTest {
    
    @Mock
    private MateriaRepository materiaRepository;
    
    @InjectMocks
    private MateriaService materiaService;
    
    private Materia materia;
    private UUID materiaId;
    
    @BeforeEach
    void setUp() {
        materiaId = UUID.randomUUID();
        materia = new Materia();
        materia.setIdMateria(materiaId);
        materia.setNome("Matemática");
        materia.setDescricao("Disciplina de matemática básica");
    }
    
    @Test
    void deveCriarMateriaComSucesso() {
        when(materiaRepository.save(any(Materia.class))).thenReturn(materia);
        
        Materia resultado = materiaService.criar(materia);
        
        assertNotNull(resultado);
        assertEquals("Matemática", resultado.getNome());
        assertEquals("Disciplina de matemática básica", resultado.getDescricao());
        verify(materiaRepository, times(1)).save(materia);
    }
    
    @Test
    void deveListarTodasMaterias() {
        Materia materia2 = new Materia();
        materia2.setIdMateria(UUID.randomUUID());
        materia2.setNome("Português");
        materia2.setDescricao("Disciplina de português");
        
        List<Materia> materias = Arrays.asList(materia, materia2);
        when(materiaRepository.findAll()).thenReturn(materias);
        
        List<Materia> resultado = materiaService.listarTodos();
        
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(materiaRepository, times(1)).findAll();
    }
    
    @Test
    void deveBuscarMateriaPorIdComSucesso() {
        when(materiaRepository.findById(materiaId)).thenReturn(Optional.of(materia));
        
        Materia resultado = materiaService.buscarPorId(materiaId);
        
        assertNotNull(resultado);
        assertEquals(materiaId, resultado.getIdMateria());
        assertEquals("Matemática", resultado.getNome());
        verify(materiaRepository, times(1)).findById(materiaId);
    }
    
    @Test
    void deveLancarExcecaoQuandoMateriaNaoEncontrada() {
        UUID idInexistente = UUID.randomUUID();
        when(materiaRepository.findById(idInexistente)).thenReturn(Optional.empty());
        
        RuntimeException exception = assertThrows(RuntimeException.class, 
            () -> materiaService.buscarPorId(idInexistente));
        
        assertTrue(exception.getMessage().contains("Materia não encontrada com id"));
        verify(materiaRepository, times(1)).findById(idInexistente);
    }
    
    @Test
    void deveAtualizarMateriaComSucesso() {
        Materia materiaAtualizada = new Materia();
        materiaAtualizada.setNome("Matemática Avançada");
        materiaAtualizada.setDescricao("Disciplina de matemática avançada");
        
        when(materiaRepository.findById(materiaId)).thenReturn(Optional.of(materia));
        when(materiaRepository.save(any(Materia.class))).thenReturn(materia);
        
        Materia resultado = materiaService.atualizar(materiaId, materiaAtualizada);
        
        assertNotNull(resultado);
        assertEquals("Matemática Avançada", resultado.getNome());
        assertEquals("Disciplina de matemática avançada", resultado.getDescricao());
        verify(materiaRepository, times(1)).findById(materiaId);
        verify(materiaRepository, times(1)).save(materia);
    }
    
    @Test
    void deveDeletarMateriaComSucesso() {
        when(materiaRepository.findById(materiaId)).thenReturn(Optional.of(materia));
        doNothing().when(materiaRepository).delete(materia);
        
        materiaService.deletar(materiaId);
        
        verify(materiaRepository, times(1)).findById(materiaId);
        verify(materiaRepository, times(1)).delete(materia);
    }
    
    @Test
    void deveLancarExcecaoAoDeletarMateriaInexistente() {
        UUID idInexistente = UUID.randomUUID();
        when(materiaRepository.findById(idInexistente)).thenReturn(Optional.empty());
        
        assertThrows(RuntimeException.class, () -> materiaService.deletar(idInexistente));
        
        verify(materiaRepository, times(1)).findById(idInexistente);
        verify(materiaRepository, never()).delete(any());
    }
}
