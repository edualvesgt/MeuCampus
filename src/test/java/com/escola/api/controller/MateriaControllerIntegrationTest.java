package com.escola.api.controller;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Materia;
import com.escola.api.repository.MateriaRepository;
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
class MateriaControllerIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private MateriaRepository materiaRepository;
    
    @BeforeEach
    void setUp() {
        materiaRepository.deleteAll();
    }
    
    @Test
    void deveRetornar201AoCriarMateria() throws Exception {
        Materia materia = new Materia();
        materia.setNome("Matemática");
        materia.setDescricao("Disciplina de cálculo e álgebra");
        
        mockMvc.perform(post("/api/materias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(materia)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Matemática"))
                .andExpect(jsonPath("$.descricao").value("Disciplina de cálculo e álgebra"))
                .andExpect(jsonPath("$.idMateria").exists());
    }
    
    @Test
    void deveRetornar200AoListarTodasMaterias() throws Exception {
        Materia materia1 = new Materia();
        materia1.setNome("Matemática");
        materia1.setDescricao("Disciplina de cálculo");
        materiaRepository.save(materia1);
        
        Materia materia2 = new Materia();
        materia2.setNome("Física");
        materia2.setDescricao("Disciplina de física");
        materiaRepository.save(materia2);
        
        mockMvc.perform(get("/api/materias")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nome").value("Matemática"))
                .andExpect(jsonPath("$[1].nome").value("Física"));
    }
    
    @Test
    void deveRetornar200AoBuscarMateriaPorId() throws Exception {
        Materia materia = new Materia();
        materia.setNome("Química");
        materia.setDescricao("Disciplina de química orgânica");
        Materia materiaSalva = materiaRepository.save(materia);
        
        mockMvc.perform(get("/api/materias/{id}", materiaSalva.getIdMateria())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idMateria").value(materiaSalva.getIdMateria().toString()))
                .andExpect(jsonPath("$.nome").value("Química"))
                .andExpect(jsonPath("$.descricao").value("Disciplina de química orgânica"));
    }
    
    @Test
    void deveRetornar200AoAtualizarMateria() throws Exception {
        Materia materia = new Materia();
        materia.setNome("História");
        materia.setDescricao("Disciplina de história");
        Materia materiaSalva = materiaRepository.save(materia);
        
        Materia materiaAtualizada = new Materia();
        materiaAtualizada.setNome("História Moderna");
        materiaAtualizada.setDescricao("Disciplina de história moderna e contemporânea");
        
        mockMvc.perform(put("/api/materias/{id}", materiaSalva.getIdMateria())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(materiaAtualizada)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("História Moderna"))
                .andExpect(jsonPath("$.descricao").value("Disciplina de história moderna e contemporânea"));
    }
    
    @Test
    void deveRetornar204AoDeletarMateria() throws Exception {
        Materia materia = new Materia();
        materia.setNome("Geografia");
        materia.setDescricao("Disciplina de geografia");
        Materia materiaSalva = materiaRepository.save(materia);
        
        mockMvc.perform(delete("/api/materias/{id}", materiaSalva.getIdMateria())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
