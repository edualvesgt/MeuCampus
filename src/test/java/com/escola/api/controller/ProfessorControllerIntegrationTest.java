package com.escola.api.controller;

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
import com.escola.api.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class ProfessorControllerIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private ProfessorRepository professorRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @BeforeEach
    void setUp() {
        professorRepository.deleteAll();
        usuarioRepository.deleteAll();
    }
    
    @Test
    void deveRetornar201AoCriarProfessor() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("Carlos Silva");
        usuario.setEmail("carlos@email.com");
        usuario.setSenha("senha123");
        usuario.setCelular("11999999999");
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        
        Professor professor = new Professor();
        professor.setFormacao("Mestrado em Matemática");
        professor.setUsuario(usuarioSalvo);
        
        mockMvc.perform(post("/api/professores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(professor)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.formacao").value("Mestrado em Matemática"))
                .andExpect(jsonPath("$.idProfessor").exists())
                .andExpect(jsonPath("$.usuario.nome").value("Carlos Silva"));
    }
    
    @Test
    void deveRetornar200AoListarTodosProfessores() throws Exception {
        Usuario usuario1 = new Usuario();
        usuario1.setNome("Carlos Silva");
        usuario1.setEmail("carlos@email.com");
        usuario1.setSenha("senha123");
        Usuario usuarioSalvo1 = usuarioRepository.save(usuario1);
        
        Professor professor1 = new Professor();
        professor1.setFormacao("Mestrado em Matemática");
        professor1.setUsuario(usuarioSalvo1);
        professorRepository.save(professor1);
        
        Usuario usuario2 = new Usuario();
        usuario2.setNome("Ana Santos");
        usuario2.setEmail("ana@email.com");
        usuario2.setSenha("senha456");
        Usuario usuarioSalvo2 = usuarioRepository.save(usuario2);
        
        Professor professor2 = new Professor();
        professor2.setFormacao("Doutorado em Física");
        professor2.setUsuario(usuarioSalvo2);
        professorRepository.save(professor2);
        
        mockMvc.perform(get("/api/professores")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].formacao").value("Mestrado em Matemática"))
                .andExpect(jsonPath("$[1].formacao").value("Doutorado em Física"));
    }
    
    @Test
    void deveRetornar200AoBuscarProfessorPorId() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("Pedro Costa");
        usuario.setEmail("pedro@email.com");
        usuario.setSenha("senha789");
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        
        Professor professor = new Professor();
        professor.setFormacao("Especialização em Química");
        professor.setUsuario(usuarioSalvo);
        Professor professorSalvo = professorRepository.save(professor);
        
        mockMvc.perform(get("/api/professores/{id}", professorSalvo.getIdProfessor())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idProfessor").value(professorSalvo.getIdProfessor().toString()))
                .andExpect(jsonPath("$.formacao").value("Especialização em Química"))
                .andExpect(jsonPath("$.usuario.nome").value("Pedro Costa"));
    }
    
    @Test
    void deveRetornar200AoAtualizarProfessor() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("Lucia Oliveira");
        usuario.setEmail("lucia@email.com");
        usuario.setSenha("senha321");
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        
        Professor professor = new Professor();
        professor.setFormacao("Graduação em História");
        professor.setUsuario(usuarioSalvo);
        Professor professorSalvo = professorRepository.save(professor);
        
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome("Lucia Oliveira Atualizada");
        novoUsuario.setEmail("lucia.nova@email.com");
        novoUsuario.setSenha("novaSenha");
        Usuario novoUsuarioSalvo = usuarioRepository.save(novoUsuario);
        
        Professor professorAtualizado = new Professor();
        professorAtualizado.setFormacao("Mestrado em História");
        professorAtualizado.setUsuario(novoUsuarioSalvo);
        
        mockMvc.perform(put("/api/professores/{id}", professorSalvo.getIdProfessor())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(professorAtualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.formacao").value("Mestrado em História"));
    }
    
    @Test
    void deveRetornar204AoDeletarProfessor() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("Roberto Lima");
        usuario.setEmail("roberto@email.com");
        usuario.setSenha("senha654");
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        
        Professor professor = new Professor();
        professor.setFormacao("Doutorado em Geografia");
        professor.setUsuario(usuarioSalvo);
        Professor professorSalvo = professorRepository.save(professor);
        
        mockMvc.perform(delete("/api/professores/{id}", professorSalvo.getIdProfessor())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
    
    @Test
    void deveRetornar404AoBuscarProfessorInexistente() throws Exception {
        java.util.UUID idInexistente = java.util.UUID.randomUUID();
        
        mockMvc.perform(get("/api/professores/{id}", idInexistente)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
