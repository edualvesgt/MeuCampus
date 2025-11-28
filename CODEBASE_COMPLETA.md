# CODEBASE COMPLETA - MEU CAMPUS
## Sistema de Gestão Escolar

---

## Arquivo: pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.0</version>
        <relativePath/>
    </parent>
    
    <groupId>com.escola</groupId>
    <artifactId>api-escola</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>
    <name>API Escola</name>
    <description>API RESTful para Sistema de Gestão Escolar</description>
    
    <properties>
        <java.version>17</java.version>
        <start-class>com.escola.api.EscolaApiApplication</start-class>
    </properties>
    
    <dependencies>
        <!-- Spring Boot Starter Web -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        
        <!-- Spring Boot Starter Data JPA -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        
        <!-- MySQL Driver -->
        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <scope>runtime</scope>
        </dependency>
        
        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        
        <!-- Spring Boot Starter Test -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        
        <!-- H2 Database for Testing -->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>test</scope>
        </dependency>
        
        <!-- JSP Support -->
        <dependency>
            <groupId>org.apache.tomcat.embed</groupId>
            <artifactId>tomcat-embed-jasper</artifactId>
        </dependency>
        
        <!-- JSTL -->
        <dependency>
            <groupId>jakarta.servlet.jsp.jstl</groupId>
            <artifactId>jakarta.servlet.jsp.jstl-api</artifactId>
        </dependency>
        
        <dependency>
            <groupId>org.glassfish.web</groupId>
            <artifactId>jakarta.servlet.jsp.jstl</artifactId>
        </dependency>
    </dependencies>
    
    <build>
        <resources>
            <resource>
                <directory>src/main/resources</directory>
            </resource>
            <resource>
                <directory>src/main/webapp</directory>
                <targetPath>META-INF/resources</targetPath>
            </resource>
        </resources>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

---

## Arquivo: README.md

```markdown
# Meu Campus - Sistema de Gestão Escolar

Sistema web para gestão de atividades acadêmicas, permitindo que alunos e professores gerenciem turmas, eventos e notificações.

## Autores

- Anthony
- Eduardo
- Gustavo Gabriel Souza
- Matheus Pegoraro
- Mateus Matos

## Tecnologias

- Java 17
- Spring Boot 3.x
- MySQL
- JSP (JavaServer Pages)
- Maven

## Funcionalidades

### Para Alunos
- Dashboard com visão geral
- Visualização de turmas matriculadas
- Consulta de eventos
- Inscrição em eventos
- Notificações
- Perfil do usuário

### Para Professores
- Dashboard com estatísticas
- Gerenciamento de turmas
- Visualização de eventos
- Consulta de alunos

## Como Executar

1. Configure o banco de dados MySQL
2. Atualize as credenciais em `src/main/resources/application.properties`
3. Execute o comando:
```bash
mvn spring-boot:run
```
4. Acesse: `http://localhost:8080`

## Estrutura do Projeto

src/
├── main/
│   ├── java/com/escola/api/
│   │   ├── controller/     # Controllers REST e Views
│   │   ├── model/          # Entidades JPA
│   │   ├── repository/     # Repositórios
│   │   └── service/        # Lógica de negócio
│   ├── resources/
│   │   ├── static/         # CSS e recursos estáticos
│   │   └── application.properties
│   └── webapp/WEB-INF/views/  # Páginas JSP
└── test/                   # Testes unitários


## Licença

Projeto acadêmico desenvolvido para fins educacionais.
```

---

## Arquivo: src/main/resources/application.properties

```properties
# Configuração do Banco de Dados
spring.datasource.url=jdbc:mysql://localhost:3306/bd_escola?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=P@ssw0rd
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Configuração do Hibernate
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# Configuração do Jackson (JSON)
spring.jackson.serialization.fail-on-empty-beans=false

# Configuração do Servidor
server.port=8082

# Configuração JSP
spring.mvc.view.prefix=/WEB-INF/views/
spring.mvc.view.suffix=.jsp
```

---

## Arquivo: src/main/java/com/escola/api/EscolaApiApplication.java

```java
package com.escola.api;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EscolaApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EscolaApiApplication.class, args);
    }
}
```

---

## Arquivo: src/main/java/com/escola/api/config/CorsConfig.java

```java
package com.escola.api.config;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOriginPatterns(Arrays.asList("*")); // Permite todas as origens
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        
        return new CorsFilter(source);
    }
}
```

---

## Arquivo: src/main/java/com/escola/api/exception/GlobalExceptionHandler.java

```java
package com.escola.api.exception;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body("Erro de integridade de dados: " + ex.getMessage());
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Erro interno: " + ex.getMessage());
    }
}
```

---


## Arquivo: src/main/java/com/escola/api/model/Usuario.java

```java
package com.escola.api.model;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_usuario", columnDefinition = "BINARY(16)")
    private UUID idUsuario;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String senha;
    
    private String celular;
    
    @OneToOne(mappedBy = "usuario")
    @JsonIgnore
    private Professor professor;
    
    @OneToOne(mappedBy = "usuario")
    @JsonIgnore
    private Aluno aluno;
    
    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<InscricaoEvento> inscricoesEvento;
}
```

---

## Arquivo: src/main/java/com/escola/api/model/Aluno.java

```java
package com.escola.api.model;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_aluno")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_aluno", columnDefinition = "BINARY(16)")
    private UUID idAluno;
    
    @Column(nullable = false, unique = true)
    private String matricula;
    
    @Column(name = "data_ingresso")
    private LocalDate dataIngresso;
    
    @Column(nullable = false)
    private String situacao = "Ativo";
    
    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private Usuario usuario;
    
    @OneToMany(mappedBy = "aluno")
    @JsonIgnore
    private List<Matricula> matriculas;
}
```

---

## Arquivo: src/main/java/com/escola/api/model/Professor.java

```java
package com.escola.api.model;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_professor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Professor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_professor", columnDefinition = "BINARY(16)")
    private UUID idProfessor;
    
    private String formacao;
    
    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private Usuario usuario;
    
    @OneToMany(mappedBy = "professor")
    @JsonIgnore
    private List<Turma> turmas;
    
    @OneToMany(mappedBy = "professor")
    @JsonIgnore
    private List<ProfessorMateria> professorMaterias;
}
```

---

## Arquivo: src/main/java/com/escola/api/model/Materia.java

```java
package com.escola.api.model;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_materia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Materia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_materia", columnDefinition = "BINARY(16)")
    private UUID idMateria;
    
    @Column(nullable = false)
    private String nome;
    
    private String descricao;
    
    @OneToMany(mappedBy = "materia")
    @JsonIgnore
    private List<Turma> turmas;
    
    @OneToMany(mappedBy = "materia")
    @JsonIgnore
    private List<ProfessorMateria> professorMaterias;
}
```

---

## Arquivo: src/main/java/com/escola/api/model/Turma.java

```java
package com.escola.api.model;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_turma")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Turma {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_turma", columnDefinition = "BINARY(16)")
    private UUID idTurma;
    
    @Column(nullable = false)
    private String nome;
    
    @ManyToOne
    @JoinColumn(name = "id_professor", nullable = false)
    private Professor professor;
    
    @ManyToOne
    @JoinColumn(name = "id_materia", nullable = false)
    private Materia materia;
    
    @OneToMany(mappedBy = "turma")
    @JsonIgnore
    private List<Matricula> matriculas;
    
    @OneToMany(mappedBy = "turma")
    @JsonIgnore
    private List<Evento> eventos;
    
    @OneToMany(mappedBy = "turma")
    @JsonIgnore
    private List<Notificacao> notificacoes;
}
```

---

## Arquivo: src/main/java/com/escola/api/model/Matricula.java

```java
package com.escola.api.model;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tb_matricula",
       uniqueConstraints = @UniqueConstraint(columnNames = {"id_turma", "id_aluno"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Matricula {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_matricula", columnDefinition = "BINARY(16)")
    private UUID idMatricula;
    
    @ManyToOne
    @JoinColumn(name = "id_turma", nullable = false)
    private Turma turma;
    
    @ManyToOne
    @JoinColumn(name = "id_aluno", nullable = false)
    private Aluno aluno;
}
```

---

## Arquivo: src/main/java/com/escola/api/model/Evento.java

```java
package com.escola.api.model;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_evento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_evento", columnDefinition = "BINARY(16)")
    private UUID idEvento;
    
    @Column(nullable = false)
    private String nome;
    
    private LocalDateTime data;
    
    private String local;
    
    @Column(columnDefinition = "TEXT")
    private String informacoes;
    
    @ManyToOne
    @JoinColumn(name = "id_professor")
    private Professor professor;
    
    @ManyToOne
    @JoinColumn(name = "id_turma")
    private Turma turma;
    
    @OneToMany(mappedBy = "evento")
    @JsonIgnore
    private List<InscricaoEvento> inscricoes;
}
```

---

## Arquivo: src/main/java/com/escola/api/model/InscricaoEvento.java

```java
package com.escola.api.model;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tb_inscricao_evento",
       uniqueConstraints = @UniqueConstraint(columnNames = {"id_usuario", "id_evento"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscricaoEvento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_inscricao_evento", columnDefinition = "BINARY(16)")
    private UUID idInscricaoEvento;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "id_evento", nullable = false)
    private Evento evento;
}
```

---

## Arquivo: src/main/java/com/escola/api/model/Notificacao.java

```java
package com.escola.api.model;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_notificacao")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_notificacao", columnDefinition = "BINARY(16)")
    private UUID idNotificacao;
    
    @Column(nullable = false)
    private String titulo;
    
    @Column(columnDefinition = "TEXT")
    private String conteudo;
    
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime data;
    
    @Column(nullable = false)
    private String status = "Nao lida";
    
    @ManyToOne
    @JoinColumn(name = "id_turma")
    private Turma turma;
    
    @ManyToOne
    @JoinColumn(name = "id_professor")
    private Professor professor;
}
```

---

## Arquivo: src/main/java/com/escola/api/model/ProfessorMateria.java

```java
package com.escola.api.model;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tb_professor_materia",
       uniqueConstraints = @UniqueConstraint(columnNames = {"id_materia", "id_professor"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorMateria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_professor_materia", columnDefinition = "BINARY(16)")
    private UUID idProfessorMateria;
    
    @ManyToOne
    @JoinColumn(name = "id_materia", nullable = false)
    private Materia materia;
    
    @ManyToOne
    @JoinColumn(name = "id_professor", nullable = false)
    private Professor professor;
}
```

---


## Arquivo: src/main/java/com/escola/api/repository/UsuarioRepository.java

```java
package com.escola.api.repository;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByEmailAndSenha(String email, String senha);
}
```

---

## Arquivo: src/main/java/com/escola/api/repository/AlunoRepository.java

```java
package com.escola.api.repository;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, UUID> {
}
```

---

## Arquivo: src/main/java/com/escola/api/repository/ProfessorRepository.java

```java
package com.escola.api.repository;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, UUID> {
}
```

---

## Arquivo: src/main/java/com/escola/api/repository/MateriaRepository.java

```java
package com.escola.api.repository;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, UUID> {
}
```

---

## Arquivo: src/main/java/com/escola/api/repository/TurmaRepository.java

```java
package com.escola.api.repository;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, UUID> {
}
```

---

## Arquivo: src/main/java/com/escola/api/repository/MatriculaRepository.java

```java
package com.escola.api.repository;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, UUID> {
}
```

---

## Arquivo: src/main/java/com/escola/api/repository/EventoRepository.java

```java
package com.escola.api.repository;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventoRepository extends JpaRepository<Evento, UUID> {
}
```

---

## Arquivo: src/main/java/com/escola/api/repository/InscricaoEventoRepository.java

```java
package com.escola.api.repository;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.InscricaoEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InscricaoEventoRepository extends JpaRepository<InscricaoEvento, UUID> {
}
```

---

## Arquivo: src/main/java/com/escola/api/repository/NotificacaoRepository.java

```java
package com.escola.api.repository;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, UUID> {
}
```

---

## Arquivo: src/main/java/com/escola/api/repository/ProfessorMateriaRepository.java

```java
package com.escola.api.repository;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.ProfessorMateria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProfessorMateriaRepository extends JpaRepository<ProfessorMateria, UUID> {
}
```

---


## Arquivo: src/main/java/com/escola/api/service/UsuarioService.java

```java
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
    public Usuario autenticar(String email, String senha) {
        return usuarioRepository.findByEmailAndSenha(email, senha)
            .orElse(null);
    }
    
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
            .orElse(null);
    }
}
```

---

## Arquivo: src/main/java/com/escola/api/service/AlunoService.java

```java
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
```

---

## Arquivo: src/main/java/com/escola/api/service/ProfessorService.java

```java
package com.escola.api.service;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Professor;
import com.escola.api.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfessorService {
    
    private final ProfessorRepository professorRepository;
    
    public Professor criar(Professor professor) {
        return professorRepository.save(professor);
    }
    
    public List<Professor> listarTodos() {
        return professorRepository.findAll();
    }
    
    public Professor buscarPorId(UUID id) {
        return professorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Professor não encontrado com id: " + id));
    }
    
    public Professor atualizar(UUID id, Professor professorAtualizado) {
        Professor professor = buscarPorId(id);
        professor.setFormacao(professorAtualizado.getFormacao());
        professor.setUsuario(professorAtualizado.getUsuario());
        return professorRepository.save(professor);
    }
    
    public void deletar(UUID id) {
        Professor professor = buscarPorId(id);
        professorRepository.delete(professor);
    }
}
```

---

## Arquivo: src/main/java/com/escola/api/service/MateriaService.java

```java
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MateriaService {
    
    private final MateriaRepository materiaRepository;
    
    public Materia criar(Materia materia) {
        return materiaRepository.save(materia);
    }
    
    public List<Materia> listarTodos() {
        return materiaRepository.findAll();
    }
    
    public Materia buscarPorId(UUID id) {
        return materiaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Materia não encontrada com id: " + id));
    }
    
    public Materia atualizar(UUID id, Materia materiaAtualizada) {
        Materia materia = buscarPorId(id);
        materia.setNome(materiaAtualizada.getNome());
        materia.setDescricao(materiaAtualizada.getDescricao());
        return materiaRepository.save(materia);
    }
    
    public void deletar(UUID id) {
        Materia materia = buscarPorId(id);
        materiaRepository.delete(materia);
    }
}
```

---

## Arquivo: src/main/java/com/escola/api/service/TurmaService.java

```java
package com.escola.api.service;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Turma;
import com.escola.api.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TurmaService {
    
    private final TurmaRepository turmaRepository;
    
    public Turma criar(Turma turma) {
        return turmaRepository.save(turma);
    }
    
    public List<Turma> listarTodos() {
        return turmaRepository.findAll();
    }
    
    public Turma buscarPorId(UUID id) {
        return turmaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Turma não encontrada com id: " + id));
    }
    
    public Turma atualizar(UUID id, Turma turmaAtualizada) {
        Turma turma = buscarPorId(id);
        turma.setNome(turmaAtualizada.getNome());
        turma.setProfessor(turmaAtualizada.getProfessor());
        turma.setMateria(turmaAtualizada.getMateria());
        return turmaRepository.save(turma);
    }
    
    public void deletar(UUID id) {
        Turma turma = buscarPorId(id);
        turmaRepository.delete(turma);
    }
}
```

---

## Arquivo: src/main/java/com/escola/api/service/MatriculaService.java

```java
package com.escola.api.service;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Matricula;
import com.escola.api.repository.MatriculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MatriculaService {
    
    private final MatriculaRepository matriculaRepository;
    
    public Matricula criar(Matricula matricula) {
        return matriculaRepository.save(matricula);
    }
    
    public List<Matricula> listarTodos() {
        return matriculaRepository.findAll();
    }
    
    public Matricula buscarPorId(UUID id) {
        return matriculaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Matricula não encontrada com id: " + id));
    }
    
    public Matricula atualizar(UUID id, Matricula matriculaAtualizada) {
        Matricula matricula = buscarPorId(id);
        matricula.setTurma(matriculaAtualizada.getTurma());
        matricula.setAluno(matriculaAtualizada.getAluno());
        return matriculaRepository.save(matricula);
    }
    
    public void deletar(UUID id) {
        Matricula matricula = buscarPorId(id);
        matriculaRepository.delete(matricula);
    }
}
```

---

## Arquivo: src/main/java/com/escola/api/service/EventoService.java

```java
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
```

---

## Arquivo: src/main/java/com/escola/api/service/InscricaoEventoService.java

```java
package com.escola.api.service;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.InscricaoEvento;
import com.escola.api.repository.InscricaoEventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InscricaoEventoService {
    
    private final InscricaoEventoRepository inscricaoEventoRepository;
    
    public InscricaoEvento criar(InscricaoEvento inscricaoEvento) {
        return inscricaoEventoRepository.save(inscricaoEvento);
    }
    
    public List<InscricaoEvento> listarTodos() {
        return inscricaoEventoRepository.findAll();
    }
    
    public InscricaoEvento buscarPorId(UUID id) {
        return inscricaoEventoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("InscricaoEvento não encontrado com id: " + id));
    }
    
    public InscricaoEvento atualizar(UUID id, InscricaoEvento inscricaoEventoAtualizado) {
        InscricaoEvento inscricaoEvento = buscarPorId(id);
        inscricaoEvento.setUsuario(inscricaoEventoAtualizado.getUsuario());
        inscricaoEvento.setEvento(inscricaoEventoAtualizado.getEvento());
        return inscricaoEventoRepository.save(inscricaoEvento);
    }
    
    public void deletar(UUID id) {
        InscricaoEvento inscricaoEvento = buscarPorId(id);
        inscricaoEventoRepository.delete(inscricaoEvento);
    }
    
    public List<InscricaoEvento> buscarPorUsuario(UUID idUsuario) {
        return inscricaoEventoRepository.findAll().stream()
            .filter(i -> i.getUsuario().getIdUsuario().equals(idUsuario))
            .toList();
    }
    
    public boolean jaInscrito(UUID idUsuario, UUID idEvento) {
        return inscricaoEventoRepository.findAll().stream()
            .anyMatch(i -> i.getUsuario().getIdUsuario().equals(idUsuario) 
                        && i.getEvento().getIdEvento().equals(idEvento));
    }
}
```

---

## Arquivo: src/main/java/com/escola/api/service/NotificacaoService.java

```java
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
```

---

## Arquivo: src/main/java/com/escola/api/service/ProfessorMateriaService.java

```java
package com.escola.api.service;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.ProfessorMateria;
import com.escola.api.repository.ProfessorMateriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfessorMateriaService {
    
    private final ProfessorMateriaRepository professorMateriaRepository;
    
    public ProfessorMateria criar(ProfessorMateria professorMateria) {
        return professorMateriaRepository.save(professorMateria);
    }
    
    public List<ProfessorMateria> listarTodos() {
        return professorMateriaRepository.findAll();
    }
    
    public ProfessorMateria buscarPorId(UUID id) {
        return professorMateriaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("ProfessorMateria não encontrado com id: " + id));
    }
    
    public ProfessorMateria atualizar(UUID id, ProfessorMateria professorMateriaAtualizado) {
        ProfessorMateria professorMateria = buscarPorId(id);
        professorMateria.setMateria(professorMateriaAtualizado.getMateria());
        professorMateria.setProfessor(professorMateriaAtualizado.getProfessor());
        return professorMateriaRepository.save(professorMateria);
    }
    
    public void deletar(UUID id) {
        ProfessorMateria professorMateria = buscarPorId(id);
        professorMateriaRepository.delete(professorMateria);
    }
}
```

---


## Arquivo: src/main/java/com/escola/api/controller/UsuarioController.java

```java
package com.escola.api.controller;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Usuario;
import com.escola.api.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    
    private final UsuarioService usuarioService;
    
    @PostMapping
    public ResponseEntity<Usuario> criar(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.criar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }
    
    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        List<Usuario> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable UUID id) {
        Usuario usuario = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable UUID id, @RequestBody Usuario usuario) {
        Usuario usuarioAtualizado = usuarioService.atualizar(id, usuario);
        return ResponseEntity.ok(usuarioAtualizado);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
```

---

## Arquivo: src/main/java/com/escola/api/controller/AlunoController.java

```java
package com.escola.api.controller;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Aluno;
import com.escola.api.service.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/alunos")
@RequiredArgsConstructor
public class AlunoController {
    
    private final AlunoService alunoService;
    
    @PostMapping
    public ResponseEntity<Aluno> criar(@RequestBody Aluno aluno) {
        Aluno novoAluno = alunoService.criar(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAluno);
    }
    
    @GetMapping
    public ResponseEntity<List<Aluno>> listarTodos() {
        List<Aluno> alunos = alunoService.listarTodos();
        return ResponseEntity.ok(alunos);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarPorId(@PathVariable UUID id) {
        Aluno aluno = alunoService.buscarPorId(id);
        return ResponseEntity.ok(aluno);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(@PathVariable UUID id, @RequestBody Aluno aluno) {
        Aluno alunoAtualizado = alunoService.atualizar(id, aluno);
        return ResponseEntity.ok(alunoAtualizado);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        alunoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
```

---

---

## Arquivo: src/main/java/com/escola/api/controller/ProfessorController.java

```java
package com.escola.api.controller;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Professor;
import com.escola.api.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/professores")
@RequiredArgsConstructor
public class ProfessorController {
    
    private final ProfessorService professorService;
    
    @PostMapping
    public ResponseEntity<Professor> criar(@RequestBody Professor professor) {
        Professor novoProfessor = professorService.criar(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProfessor);
    }
    
    @GetMapping
    public ResponseEntity<List<Professor>> listarTodos() {
        List<Professor> professores = professorService.listarTodos();
        return ResponseEntity.ok(professores);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscarPorId(@PathVariable UUID id) {
        Professor professor = professorService.buscarPorId(id);
        return ResponseEntity.ok(professor);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizar(@PathVariable UUID id, @RequestBody Professor professor) {
        Professor professorAtualizado = professorService.atualizar(id, professor);
        return ResponseEntity.ok(professorAtualizado);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        professorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
```

---

## Arquivo: src/main/java/com/escola/api/controller/MateriaController.java

```java
package com.escola.api.controller;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Materia;
import com.escola.api.service.MateriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/materias")
@RequiredArgsConstructor
public class MateriaController {
    
    private final MateriaService materiaService;
    
    @PostMapping
    public ResponseEntity<Materia> criar(@RequestBody Materia materia) {
        Materia novaMateria = materiaService.criar(materia);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMateria);
    }
    
    @GetMapping
    public ResponseEntity<List<Materia>> listarTodos() {
        List<Materia> materias = materiaService.listarTodos();
        return ResponseEntity.ok(materias);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Materia> buscarPorId(@PathVariable UUID id) {
        Materia materia = materiaService.buscarPorId(id);
        return ResponseEntity.ok(materia);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Materia> atualizar(@PathVariable UUID id, @RequestBody Materia materia) {
        Materia materiaAtualizada = materiaService.atualizar(id, materia);
        return ResponseEntity.ok(materiaAtualizada);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        materiaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
```

---

## Arquivo: src/main/java/com/escola/api/controller/TurmaController.java

```java
package com.escola.api.controller;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Turma;
import com.escola.api.service.TurmaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/turmas")
@RequiredArgsConstructor
public class TurmaController {
    
    private final TurmaService turmaService;
    
    @PostMapping
    public ResponseEntity<Turma> criar(@RequestBody Turma turma) {
        Turma novaTurma = turmaService.criar(turma);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTurma);
    }
    
    @GetMapping
    public ResponseEntity<List<Turma>> listarTodos() {
        List<Turma> turmas = turmaService.listarTodos();
        return ResponseEntity.ok(turmas);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Turma> buscarPorId(@PathVariable UUID id) {
        Turma turma = turmaService.buscarPorId(id);
        return ResponseEntity.ok(turma);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Turma> atualizar(@PathVariable UUID id, @RequestBody Turma turma) {
        Turma turmaAtualizada = turmaService.atualizar(id, turma);
        return ResponseEntity.ok(turmaAtualizada);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        turmaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
```

---

## Arquivo: src/main/java/com/escola/api/controller/EventoController.java

```java
package com.escola.api.controller;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Evento;
import com.escola.api.service.EventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/eventos")
@RequiredArgsConstructor
public class EventoController {
    
    private final EventoService eventoService;
    
    @PostMapping
    public ResponseEntity<Evento> criar(@RequestBody Evento evento) {
        Evento novoEvento = eventoService.criar(evento);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEvento);
    }
    
    @GetMapping
    public ResponseEntity<List<Evento>> listarTodos() {
        List<Evento> eventos = eventoService.listarTodos();
        return ResponseEntity.ok(eventos);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Evento> buscarPorId(@PathVariable UUID id) {
        Evento evento = eventoService.buscarPorId(id);
        return ResponseEntity.ok(evento);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Evento> atualizar(@PathVariable UUID id, @RequestBody Evento evento) {
        Evento eventoAtualizado = eventoService.atualizar(id, evento);
        return ResponseEntity.ok(eventoAtualizado);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        eventoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
```

---

## Arquivo: src/main/java/com/escola/api/controller/MatriculaController.java

```java
package com.escola.api.controller;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Matricula;
import com.escola.api.service.MatriculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/matriculas")
@RequiredArgsConstructor
public class MatriculaController {
    
    private final MatriculaService matriculaService;
    
    @PostMapping
    public ResponseEntity<Matricula> criar(@RequestBody Matricula matricula) {
        Matricula novaMatricula = matriculaService.criar(matricula);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMatricula);
    }
    
    @GetMapping
    public ResponseEntity<List<Matricula>> listarTodos() {
        List<Matricula> matriculas = matriculaService.listarTodos();
        return ResponseEntity.ok(matriculas);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Matricula> buscarPorId(@PathVariable UUID id) {
        Matricula matricula = matriculaService.buscarPorId(id);
        return ResponseEntity.ok(matricula);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Matricula> atualizar(@PathVariable UUID id, @RequestBody Matricula matricula) {
        Matricula matriculaAtualizada = matriculaService.atualizar(id, matricula);
        return ResponseEntity.ok(matriculaAtualizada);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        matriculaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
```

---

## Arquivo: src/main/java/com/escola/api/controller/InscricaoEventoController.java

```java
package com.escola.api.controller;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.InscricaoEvento;
import com.escola.api.service.InscricaoEventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/inscricoes-evento")
@RequiredArgsConstructor
public class InscricaoEventoController {
    
    private final InscricaoEventoService inscricaoEventoService;
    
    @PostMapping
    public ResponseEntity<InscricaoEvento> criar(@RequestBody InscricaoEvento inscricaoEvento) {
        InscricaoEvento novaInscricaoEvento = inscricaoEventoService.criar(inscricaoEvento);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaInscricaoEvento);
    }
    
    @GetMapping
    public ResponseEntity<List<InscricaoEvento>> listarTodos() {
        List<InscricaoEvento> inscricoesEvento = inscricaoEventoService.listarTodos();
        return ResponseEntity.ok(inscricoesEvento);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<InscricaoEvento> buscarPorId(@PathVariable UUID id) {
        InscricaoEvento inscricaoEvento = inscricaoEventoService.buscarPorId(id);
        return ResponseEntity.ok(inscricaoEvento);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<InscricaoEvento> atualizar(@PathVariable UUID id, @RequestBody InscricaoEvento inscricaoEvento) {
        InscricaoEvento inscricaoEventoAtualizada = inscricaoEventoService.atualizar(id, inscricaoEvento);
        return ResponseEntity.ok(inscricaoEventoAtualizada);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        inscricaoEventoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
```

---

## Arquivo: src/main/java/com/escola/api/controller/NotificacaoController.java

```java
package com.escola.api.controller;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Notificacao;
import com.escola.api.service.NotificacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notificacoes")
@RequiredArgsConstructor
public class NotificacaoController {
    
    private final NotificacaoService notificacaoService;
    
    @PostMapping
    public ResponseEntity<Notificacao> criar(@RequestBody Notificacao notificacao) {
        Notificacao novaNotificacao = notificacaoService.criar(notificacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaNotificacao);
    }
    
    @GetMapping
    public ResponseEntity<List<Notificacao>> listarTodos() {
        List<Notificacao> notificacoes = notificacaoService.listarTodos();
        return ResponseEntity.ok(notificacoes);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Notificacao> buscarPorId(@PathVariable UUID id) {
        Notificacao notificacao = notificacaoService.buscarPorId(id);
        return ResponseEntity.ok(notificacao);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Notificacao> atualizar(@PathVariable UUID id, @RequestBody Notificacao notificacao) {
        Notificacao notificacaoAtualizada = notificacaoService.atualizar(id, notificacao);
        return ResponseEntity.ok(notificacaoAtualizada);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        notificacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
```

---

## Arquivo: src/main/java/com/escola/api/controller/ProfessorMateriaController.java

```java
package com.escola.api.controller;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.ProfessorMateria;
import com.escola.api.service.ProfessorMateriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/professor-materias")
@RequiredArgsConstructor
public class ProfessorMateriaController {
    
    private final ProfessorMateriaService professorMateriaService;
    
    @PostMapping
    public ResponseEntity<ProfessorMateria> criar(@RequestBody ProfessorMateria professorMateria) {
        ProfessorMateria novoProfessorMateria = professorMateriaService.criar(professorMateria);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProfessorMateria);
    }
    
    @GetMapping
    public ResponseEntity<List<ProfessorMateria>> listarTodos() {
        List<ProfessorMateria> professorMaterias = professorMateriaService.listarTodos();
        return ResponseEntity.ok(professorMaterias);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProfessorMateria> buscarPorId(@PathVariable UUID id) {
        ProfessorMateria professorMateria = professorMateriaService.buscarPorId(id);
        return ResponseEntity.ok(professorMateria);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProfessorMateria> atualizar(@PathVariable UUID id, @RequestBody ProfessorMateria professorMateria) {
        ProfessorMateria professorMateriaAtualizada = professorMateriaService.atualizar(id, professorMateria);
        return ResponseEntity.ok(professorMateriaAtualizada);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        professorMateriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
```

---


## Arquivo: src/main/java/com/escola/api/controller/ViewController.java

_**NOTA:** Este é o controller MVC principal que gerencia todas as views JSP do sistema. Ele contém métodos para login, dashboards de aluno e professor, e todas as páginas de navegação._

```java
package com.escola.api.controller;

import com.escola.api.model.*;
import com.escola.api.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ViewController {
    
    private final AlunoService alunoService;
    private final UsuarioService usuarioService;
    private final TurmaService turmaService;
    private final MatriculaService matriculaService;
    private final EventoService eventoService;
    private final NotificacaoService notificacaoService;
    private final InscricaoEventoService inscricaoEventoService;
    
    // ========== LOGIN ==========
    @GetMapping("/")
    public String home() {
        return "login";
    }
    
    @GetMapping("/login")
    public String loginPage(HttpSession session) {
        if (session.getAttribute("usuarioLogado") != null) {
            return "redirect:/aluno/dashboard";
        }
        return "login";
    }
    
    @PostMapping("/login")
    public String loginSubmit(@RequestParam String email, @RequestParam String senha, 
                             HttpSession session, Model model) {
        Usuario usuario = usuarioService.autenticar(email, senha);
        
        if (usuario == null) {
            model.addAttribute("erro", "Email ou senha inválidos");
            return "login";
        }
        
        session.setAttribute("usuarioLogado", usuario);
        
        if (usuario.getAluno() != null) {
            session.setAttribute("alunoLogado", usuario.getAluno());
            return "redirect:/aluno/dashboard";
        } else if (usuario.getProfessor() != null) {
            session.setAttribute("professorLogado", usuario.getProfessor());
            return "redirect:/professor/dashboard";
        } else {
            return "redirect:/admin/dashboard";
        }
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
    
    // ========== ALUNO - DASHBOARD ==========
    @GetMapping("/aluno/dashboard")
    public String alunoDashboard(HttpSession session, Model model) {
        try {
            Aluno alunoSessao = (Aluno) session.getAttribute("alunoLogado");
            if (alunoSessao == null) {
                return "redirect:/login";
            }
            
            Aluno aluno = alunoService.buscarPorId(alunoSessao.getIdAluno());
            Usuario usuario = aluno.getUsuario();
            
            String primeiroNome = usuario.getNome().split(" ")[0];
            String iniciais = usuario.getNome().substring(0, Math.min(2, usuario.getNome().length())).toUpperCase();
            
            model.addAttribute("pageTitle", "Dashboard");
            model.addAttribute("pageActive", "dashboard");
            model.addAttribute("contentPage", "aluno/dashboard.jsp");
            model.addAttribute("alunoNome", primeiroNome);
            model.addAttribute("alunoNomeCompleto", usuario.getNome());
            model.addAttribute("alunoInicial", iniciais);
            
            int totalTurmas = aluno.getMatriculas() != null ? aluno.getMatriculas().size() : 0;
            model.addAttribute("totalTurmas", totalTurmas);
            model.addAttribute("proximasTurmas", aluno.getMatriculas() != null ? aluno.getMatriculas() : java.util.Collections.emptyList());
            model.addAttribute("totalEventos", 0);
            model.addAttribute("notificacoesNaoLidas", 0);
            model.addAttribute("proximosEventos", eventoService.listarTodos());
            model.addAttribute("ultimasNotificacoes", notificacaoService.listarTodos());
            
            return "layout-aluno";
            
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao carregar dashboard: " + e.getMessage());
            return "error";
        }
    }
    
    // ========== ALUNO - TURMAS ==========
    @GetMapping("/aluno/turmas")
    public String alunoTurmas(HttpSession session, Model model) {
        // Similar ao dashboard, carrega as turmas do aluno
        // ... (código completo no arquivo original)
        return "layout-aluno";
    }
    
    // ========== ALUNO - EVENTOS ==========
    @GetMapping("/aluno/eventos")
    public String alunoEventos(HttpSession session, Model model) {
        // Carrega eventos disponíveis e eventos inscritos
        // ... (código completo no arquivo original)
        return "layout-aluno";
    }
    
    // ========== ALUNO - INSCRIÇÃO EM EVENTO ==========
    @PostMapping("/aluno/eventos/{idEvento}/inscrever")
    public String inscreverEvento(@PathVariable UUID idEvento, HttpSession session, Model model) {
        // Inscreve o aluno no evento
        // ... (código completo no arquivo original)
        return "redirect:/aluno/eventos?sucesso=inscrito";
    }
    
    // ========== PROFESSOR - DASHBOARD ==========
    @GetMapping("/professor/dashboard")
    public String professorDashboardView(HttpSession session, Model model) {
        // Dashboard do professor com estatísticas
        // ... (código completo no arquivo original)
        return "layout-professor";
    }
    
    // ... Demais métodos para professor e admin
}
```

---

## Arquivo: src/main/resources/static/css/style.css

_**NOTA:** Arquivo CSS completo com mais de 800 linhas contendo todo o design system da aplicação, incluindo variáveis CSS, componentes, layout responsivo, sidebar, cards, formulários, tabelas, badges, etc._

```css
/* ============================================
   SISTEMA DE GESTÃO ESCOLAR - MEU CAMPUS
   CSS Global - Integrado com Spring Boot + JSP
   ============================================ */

:root {
  /* Cores Primárias */
  --color-primary: #B22D4E;
  --color-primary-dark: #8B1F3A;
  --color-primary-light: #D84567;
  
  /* Cores Neutras */
  --color-white: #FFFFFF;
  --color-gray-50: #F9FAFB;
  --color-gray-100: #F3F4F6;
  /* ... demais variáveis ... */
}

/* Reset, Tipografia, Layout, Componentes, etc. */
/* ... (código completo no arquivo original) ... */
```

---

## Arquivo: src/main/resources/static/js/alunos.js

```javascript
function editarAluno(id) {
    window.location.href = `/alunos/editar/${id}`;
}

function deletarAluno(id) {
    if (confirm('Tem certeza que deseja deletar este aluno?')) {
        fetch(`/api/alunos/${id}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (response.ok) {
                alert('Aluno deletado com sucesso!');
                window.location.reload();
            } else {
                alert('Erro ao deletar aluno');
            }
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Erro ao deletar aluno');
        });
    }
}
```

---

## Arquivo: src/main/webapp/WEB-INF/views/login.jsp

_**NOTA:** Página de login com formulário de autenticação._

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Meu Campus</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="login-wrapper">
        <div class="login-card">
            <div class="login-header">
                <div class="login-logo">MEU CAMPUS</div>
                <p class="login-subtitle">Sistema de Gestão Escolar</p>
            </div>

            <div class="login-body">
                <c:if test="${not empty erro}">
                    <div style="background-color: #FEE2E2; color: #991B1B; padding: 0.75rem; border-radius: 0.375rem; margin-bottom: 1rem;">
                        ${erro}
                    </div>
                </c:if>
                
                <form method="POST" action="${pageContext.request.contextPath}/login">
                    <div class="form-group">
                        <label for="email" class="form-label">E-mail</label>
                        <input type="email" id="email" name="email" class="form-control" required />
                    </div>

                    <div class="form-group">
                        <label for="senha" class="form-label">Senha</label>
                        <input type="password" id="senha" name="senha" class="form-control" required />
                    </div>

                    <button type="submit" class="btn btn-primary btn-block btn-lg">
                        Entrar
                    </button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
```

---

## Arquivo: src/main/webapp/WEB-INF/views/layout-aluno.jsp

_**NOTA:** Layout principal para páginas do aluno com sidebar, topbar e área de conteúdo dinâmico._

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>${pageTitle} - Meu Campus</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="main-wrapper">
        <!-- Sidebar com navegação -->
        <aside class="sidebar" id="sidebar">
            <div class="sidebar-header">
                <div class="sidebar-logo">MEU CAMPUS</div>
            </div>
            <nav>
                <ul class="sidebar-nav">
                    <li><a href="/aluno/dashboard" class="${pageActive == 'dashboard' ? 'active' : ''}">Dashboard</a></li>
                    <li><a href="/aluno/turmas" class="${pageActive == 'turmas' ? 'active' : ''}">Minhas Turmas</a></li>
                    <li><a href="/aluno/eventos" class="${pageActive == 'eventos' ? 'active' : ''}">Eventos</a></li>
                    <li><a href="/aluno/notificacoes" class="${pageActive == 'notificacoes' ? 'active' : ''}">Notificações</a></li>
                    <li><a href="/aluno/perfil" class="${pageActive == 'perfil' ? 'active' : ''}">Meu Perfil</a></li>
                    <li><a href="/logout">Sair</a></li>
                </ul>
            </nav>
        </aside>

        <!-- Main Content -->
        <main class="main-content">
            <div class="topbar">
                <div class="topbar-user">
                    <div class="topbar-avatar">${alunoInicial}</div>
                    <span>${alunoNome}</span>
                </div>
            </div>

            <div class="container-fluid" style="padding: 2rem;">
                <jsp:include page="${contentPage}"/>
            </div>
        </main>
    </div>
</body>
</html>
```

---

## Arquivo: src/main/webapp/WEB-INF/views/aluno/dashboard.jsp

_**NOTA:** Dashboard do aluno com cards de estatísticas, próximas aulas, eventos e notificações._

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="page-header">
    <h1 class="page-title">Bem-vindo, ${alunoNome}!</h1>
    <p class="text-muted">Aqui está um resumo das suas atividades</p>
</div>

<div class="stats-grid">
    <div class="stat-card">
        <div class="stat-icon primary">📚</div>
        <div class="stat-content">
            <div class="stat-label">Turmas Matriculadas</div>
            <div class="stat-value">${totalTurmas}</div>
        </div>
    </div>
    <!-- Mais cards de estatísticas -->
</div>

<div class="row">
    <div class="col-6">
        <div class="card">
            <div class="card-header"><h3>Próximas Aulas</h3></div>
            <div class="card-body">
                <ul class="list-group">
                    <c:forEach var="matricula" items="${proximasTurmas}">
                        <li class="list-group-item">
                            <strong>${matricula.turma.materia.nome}</strong>
                            <div class="text-muted">Prof. ${matricula.turma.professor.usuario.nome}</div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
    <!-- Mais seções -->
</div>
```

---

## Arquivo: src/main/webapp/WEB-INF/views/aluno/eventos.jsp

_**NOTA:** Página de eventos com listagem e opção de inscrição._

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="page-header">
    <h1 class="page-title">Eventos</h1>
    <p class="text-muted">Eventos disponíveis para inscrição</p>
</div>

<div class="row">
    <c:forEach var="evento" items="${eventos}">
        <div class="col-4">
            <div class="card">
                <div class="card-header"><h3>${evento.nome}</h3></div>
                <div class="card-body">
                    <p>${evento.informacoes}</p>
                    <div>📅 ${evento.data}</div>
                    <div>📍 ${evento.local}</div>
                </div>
                <div class="card-footer">
                    <c:choose>
                        <c:when test="${eventosInscritos.contains(evento.idEvento)}">
                            <span class="badge badge-success">Inscrito</span>
                        </c:when>
                        <c:otherwise>
                            <form action="/aluno/eventos/${evento.idEvento}/inscrever" method="post">
                                <button type="submit" class="btn btn-primary btn-sm">Inscrever-se</button>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
```

---

_**NOTA:** Existem mais arquivos JSP para turmas, notificações, perfil do aluno, e páginas do professor (dashboard, turmas, eventos). Todos seguem estrutura similar com layout responsivo e integração com o backend Spring Boot._

---

# RESUMO DA ESTRUTURA DO PROJETO

## Backend (Java/Spring Boot)

### Camada de Modelo (Entidades JPA)
- **Usuario**: Entidade base com dados de login
- **Aluno**: Estende usuário com matrícula e situação
- **Professor**: Estende usuário com formação
- **Materia**: Disciplinas do sistema
- **Turma**: Relaciona professor, matéria e alunos
- **Matricula**: Relacionamento aluno-turma
- **Evento**: Eventos acadêmicos
- **InscricaoEvento**: Inscrições de usuários em eventos
- **Notificacao**: Sistema de notificações
- **ProfessorMateria**: Relacionamento professor-matéria

### Camada de Repositório
- Interfaces JPA Repository para cada entidade
- Métodos customizados em UsuarioRepository (findByEmail, findByEmailAndSenha)

### Camada de Serviço
- Services com lógica de negócio CRUD para cada entidade
- Tratamento de exceções
- Transações com @Transactional

### Camada de Controller
- **Controllers REST** (/api/*): APIs RESTful para operações CRUD
- **ViewController**: Controller MVC para renderizar páginas JSP
  - Login e autenticação
  - Dashboards (aluno, professor, admin)
  - Páginas de turmas, eventos, notificações, perfil

### Configuração
- **CorsConfig**: Configuração CORS
- **GlobalExceptionHandler**: Tratamento global de exceções
- **application.properties**: Configurações do banco MySQL, JPA, servidor

## Frontend (JSP + CSS + JavaScript)

### Layouts
- **layout-aluno.jsp**: Layout com sidebar para alunos
- **layout-professor.jsp**: Layout com sidebar para professores
- **login.jsp**: Página de autenticação

### Páginas do Aluno
- dashboard.jsp: Visão geral com estatísticas
- turmas.jsp: Lista de turmas matriculadas
- eventos.jsp: Eventos disponíveis e inscrições
- notificacoes.jsp: Notificações recebidas
- perfil.jsp: Dados pessoais e acadêmicos

### Páginas do Professor
- dashboard.jsp: Estatísticas e ações rápidas
- turmas.jsp: Turmas lecionadas
- eventos.jsp: Eventos criados

### Estilos
- **style.css**: Design system completo com:
  - Variáveis CSS (cores, espaçamentos, tipografia)
  - Componentes (cards, botões, formulários, tabelas)
  - Layout responsivo
  - Sidebar navigation
  - Stats cards
  - Badges e utilidades

### JavaScript
- **alunos.js**: Funções para editar/deletar alunos

## Banco de Dados (MySQL)

### Tabelas Principais
- tb_usuario
- tb_aluno
- tb_professor
- tb_materia
- tb_turma
- tb_matricula
- tb_evento
- tb_inscricao_evento
- tb_notificacao
- tb_professor_materia

### Relacionamentos
- Usuario 1:1 Aluno/Professor
- Professor 1:N Turma
- Materia 1:N Turma
- Turma N:M Aluno (através de Matricula)
- Evento N:M Usuario (através de InscricaoEvento)

## Tecnologias Utilizadas

- **Backend**: Java 17, Spring Boot 3.2.0, Spring Data JPA, Lombok
- **Frontend**: JSP, JSTL, CSS3, JavaScript
- **Banco de Dados**: MySQL 8.0
- **Build**: Maven
- **Servidor**: Tomcat Embedded (porta 8082)

---

# FIM DO DOCUMENTO

**Total de Arquivos Documentados**: 50+
**Linhas de Código**: ~10.000+
**Autores**: Anthony, Eduardo, Gustavo Gabriel Souza, Matheus Pegoraro, Mateus Matos

