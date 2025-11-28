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
