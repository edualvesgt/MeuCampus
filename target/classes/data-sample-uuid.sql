-- ============================================
-- SCRIPT DE POPULAÇÃO DO BANCO DE DADOS COM UUID AUTOMÁTICO
-- Sistema Meu Campus
-- ============================================

-- Limpar dados existentes (cuidado em produção!)
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE tb_inscricao_evento;
TRUNCATE TABLE tb_notificacao;
TRUNCATE TABLE tb_matricula;
TRUNCATE TABLE tb_evento;
TRUNCATE TABLE tb_turma;
TRUNCATE TABLE tb_professor_materia;
TRUNCATE TABLE tb_aluno;
TRUNCATE TABLE tb_professor;
TRUNCATE TABLE tb_materia;
TRUNCATE TABLE tb_usuario;
SET FOREIGN_KEY_CHECKS = 1;

-- ============================================
-- 1. INSERIR USUÁRIOS
-- ============================================

-- Usuário Admin
INSERT INTO tb_usuario (id_usuario, nome, email, senha, celular) 
VALUES (UUID_TO_BIN(UUID()), 'Administrador Sistema', 'admin@meucampus.com', 'admin123', '(11) 99999-0000');

-- Usuários Professores
INSERT INTO tb_usuario (id_usuario, nome, email, senha, celular) VALUES
(UUID_TO_BIN(UUID()), 'João Silva Santos', 'joao.silva@meucampus.com', 'prof123', '(11) 98765-4321'),
(UUID_TO_BIN(UUID()), 'Maria Oliveira Costa', 'maria.oliveira@meucampus.com', 'prof123', '(11) 98765-4322'),
(UUID_TO_BIN(UUID()), 'Carlos Eduardo Souza', 'carlos.souza@meucampus.com', 'prof123', '(11) 98765-4323'),
(UUID_TO_BIN(UUID()), 'Ana Paula Ferreira', 'ana.ferreira@meucampus.com', 'prof123', '(11) 98765-4324');

-- Usuários Alunos
INSERT INTO tb_usuario (id_usuario, nome, email, senha, celular) VALUES
(UUID_TO_BIN(UUID()), 'Pedro Henrique Alves', 'pedro.alves@aluno.com', 'aluno123', '(11) 91234-5671'),
(UUID_TO_BIN(UUID()), 'Julia Martins Lima', 'julia.lima@aluno.com', 'aluno123', '(11) 91234-5672'),
(UUID_TO_BIN(UUID()), 'Lucas Gabriel Santos', 'lucas.santos@aluno.com', 'aluno123', '(11) 91234-5673'),
(UUID_TO_BIN(UUID()), 'Beatriz Costa Silva', 'beatriz.silva@aluno.com', 'aluno123', '(11) 91234-5674'),
(UUID_TO_BIN(UUID()), 'Rafael Oliveira Souza', 'rafael.souza@aluno.com', 'aluno123', '(11) 91234-5675'),
(UUID_TO_BIN(UUID()), 'Fernanda Rodrigues', 'fernanda.rodrigues@aluno.com', 'aluno123', '(11) 91234-5676');

-- ============================================
-- 2. INSERIR PROFESSORES
-- ============================================

INSERT INTO tb_professor (id_professor, formacao, id_usuario) 
SELECT UUID_TO_BIN(UUID()), 'Mestrado em Ciência da Computação', id_usuario 
FROM tb_usuario WHERE email = 'joao.silva@meucampus.com';

INSERT INTO tb_professor (id_professor, formacao, id_usuario) 
SELECT UUID_TO_BIN(UUID()), 'Doutorado em Matemática Aplicada', id_usuario 
FROM tb_usuario WHERE email = 'maria.oliveira@meucampus.com';

INSERT INTO tb_professor (id_professor, formacao, id_usuario) 
SELECT UUID_TO_BIN(UUID()), 'Mestrado em Engenharia de Software', id_usuario 
FROM tb_usuario WHERE email = 'carlos.souza@meucampus.com';

INSERT INTO tb_professor (id_professor, formacao, id_usuario) 
SELECT UUID_TO_BIN(UUID()), 'Doutorado em Banco de Dados', id_usuario 
FROM tb_usuario WHERE email = 'ana.ferreira@meucampus.com';

-- ============================================
-- 3. INSERIR ALUNOS
-- ============================================

INSERT INTO tb_aluno (id_aluno, matricula, data_ingresso, situacao, id_usuario)
SELECT UUID_TO_BIN(UUID()), '2024001', '2024-02-01', 'Ativo', id_usuario 
FROM tb_usuario WHERE email = 'pedro.alves@aluno.com';

INSERT INTO tb_aluno (id_aluno, matricula, data_ingresso, situacao, id_usuario)
SELECT UUID_TO_BIN(UUID()), '2024002', '2024-02-01', 'Ativo', id_usuario 
FROM tb_usuario WHERE email = 'julia.lima@aluno.com';

INSERT INTO tb_aluno (id_aluno, matricula, data_ingresso, situacao, id_usuario)
SELECT UUID_TO_BIN(UUID()), '2024003', '2024-02-01', 'Ativo', id_usuario 
FROM tb_usuario WHERE email = 'lucas.santos@aluno.com';

INSERT INTO tb_aluno (id_aluno, matricula, data_ingresso, situacao, id_usuario)
SELECT UUID_TO_BIN(UUID()), '2024004', '2024-02-01', 'Ativo', id_usuario 
FROM tb_usuario WHERE email = 'beatriz.silva@aluno.com';

INSERT INTO tb_aluno (id_aluno, matricula, data_ingresso, situacao, id_usuario)
SELECT UUID_TO_BIN(UUID()), '2024005', '2024-02-01', 'Ativo', id_usuario 
FROM tb_usuario WHERE email = 'rafael.souza@aluno.com';

INSERT INTO tb_aluno (id_aluno, matricula, data_ingresso, situacao, id_usuario)
SELECT UUID_TO_BIN(UUID()), '2024006', '2024-02-01', 'Ativo', id_usuario 
FROM tb_usuario WHERE email = 'fernanda.rodrigues@aluno.com';

-- ============================================
-- 4. INSERIR MATÉRIAS
-- ============================================

INSERT INTO tb_materia (id_materia, nome, descricao) VALUES
(UUID_TO_BIN(UUID()), 'Programação Orientada a Objetos', 'Conceitos fundamentais de POO, classes, objetos, herança e polimorfismo'),
(UUID_TO_BIN(UUID()), 'Banco de Dados', 'Modelagem, SQL, normalização e administração de bancos de dados'),
(UUID_TO_BIN(UUID()), 'Desenvolvimento Web', 'HTML, CSS, JavaScript, frameworks modernos e APIs REST'),
(UUID_TO_BIN(UUID()), 'Estrutura de Dados', 'Listas, pilhas, filas, árvores, grafos e algoritmos'),
(UUID_TO_BIN(UUID()), 'Engenharia de Software', 'Metodologias ágeis, padrões de projeto e boas práticas');

-- ============================================
-- 5. INSERIR TURMAS
-- ============================================

-- Turmas do Professor João Silva
INSERT INTO tb_turma (id_turma, nome, codigo, periodo, horario, sala, id_professor, id_materia)
SELECT UUID_TO_BIN(UUID()), 'POO - Turma A', 'POO-2024-A', '2024/2', 'Segunda e Quarta 14h-16h', 'Lab 101',
       p.id_professor, m.id_materia
FROM tb_professor p
JOIN tb_usuario u ON p.id_usuario = u.id_usuario
JOIN tb_materia m ON m.nome = 'Programação Orientada a Objetos'
WHERE u.email = 'joao.silva@meucampus.com';

INSERT INTO tb_turma (id_turma, nome, codigo, periodo, horario, sala, id_professor, id_materia)
SELECT UUID_TO_BIN(UUID()), 'POO - Turma B', 'POO-2024-B', '2024/2', 'Terça e Quinta 16h-18h', 'Lab 102',
       p.id_professor, m.id_materia
FROM tb_professor p
JOIN tb_usuario u ON p.id_usuario = u.id_usuario
JOIN tb_materia m ON m.nome = 'Programação Orientada a Objetos'
WHERE u.email = 'joao.silva@meucampus.com';

-- Turma da Professora Maria Oliveira
INSERT INTO tb_turma (id_turma, nome, codigo, periodo, horario, sala, id_professor, id_materia)
SELECT UUID_TO_BIN(UUID()), 'Banco de Dados - Turma A', 'BD-2024-A', '2024/2', 'Segunda e Quarta 16h-18h', 'Lab 201',
       p.id_professor, m.id_materia
FROM tb_professor p
JOIN tb_usuario u ON p.id_usuario = u.id_usuario
JOIN tb_materia m ON m.nome = 'Banco de Dados'
WHERE u.email = 'maria.oliveira@meucampus.com';

-- Turmas do Professor Carlos Eduardo
INSERT INTO tb_turma (id_turma, nome, codigo, periodo, horario, sala, id_professor, id_materia)
SELECT UUID_TO_BIN(UUID()), 'Desenvolvimento Web - Turma A', 'WEB-2024-A', '2024/2', 'Terça e Quinta 14h-16h', 'Lab 103',
       p.id_professor, m.id_materia
FROM tb_professor p
JOIN tb_usuario u ON p.id_usuario = u.id_usuario
JOIN tb_materia m ON m.nome = 'Desenvolvimento Web'
WHERE u.email = 'carlos.souza@meucampus.com';

INSERT INTO tb_turma (id_turma, nome, codigo, periodo, horario, sala, id_professor, id_materia)
SELECT UUID_TO_BIN(UUID()), 'Engenharia de Software - Turma A', 'ES-2024-A', '2024/2', 'Sexta 14h-18h', 'Sala 301',
       p.id_professor, m.id_materia
FROM tb_professor p
JOIN tb_usuario u ON p.id_usuario = u.id_usuario
JOIN tb_materia m ON m.nome = 'Engenharia de Software'
WHERE u.email = 'carlos.souza@meucampus.com';

-- Turma da Professora Ana Paula
INSERT INTO tb_turma (id_turma, nome, codigo, periodo, horario, sala, id_professor, id_materia)
SELECT UUID_TO_BIN(UUID()), 'Estrutura de Dados - Turma A', 'ED-2024-A', '2024/2', 'Segunda e Quarta 10h-12h', 'Lab 104',
       p.id_professor, m.id_materia
FROM tb_professor p
JOIN tb_usuario u ON p.id_usuario = u.id_usuario
JOIN tb_materia m ON m.nome = 'Estrutura de Dados'
WHERE u.email = 'ana.ferreira@meucampus.com';

-- ============================================
-- 6. INSERIR MATRÍCULAS (Alunos nas Turmas)
-- ============================================

-- Aluno Pedro em 3 turmas
INSERT INTO tb_matricula (id_matricula, id_turma, id_aluno)
SELECT UUID_TO_BIN(UUID()), t.id_turma, a.id_aluno
FROM tb_turma t, tb_aluno a
JOIN tb_usuario u ON a.id_usuario = u.id_usuario
WHERE t.codigo = 'POO-2024-A' AND u.email = 'pedro.alves@aluno.com';

INSERT INTO tb_matricula (id_matricula, id_turma, id_aluno)
SELECT UUID_TO_BIN(UUID()), t.id_turma, a.id_aluno
FROM tb_turma t, tb_aluno a
JOIN tb_usuario u ON a.id_usuario = u.id_usuario
WHERE t.codigo = 'BD-2024-A' AND u.email = 'pedro.alves@aluno.com';

INSERT INTO tb_matricula (id_matricula, id_turma, id_aluno)
SELECT UUID_TO_BIN(UUID()), t.id_turma, a.id_aluno
FROM tb_turma t, tb_aluno a
JOIN tb_usuario u ON a.id_usuario = u.id_usuario
WHERE t.codigo = 'WEB-2024-A' AND u.email = 'pedro.alves@aluno.com';

-- Aluna Julia em 3 turmas
INSERT INTO tb_matricula (id_matricula, id_turma, id_aluno)
SELECT UUID_TO_BIN(UUID()), t.id_turma, a.id_aluno
FROM tb_turma t, tb_aluno a
JOIN tb_usuario u ON a.id_usuario = u.id_usuario
WHERE t.codigo = 'POO-2024-A' AND u.email = 'julia.lima@aluno.com';

INSERT INTO tb_matricula (id_matricula, id_turma, id_aluno)
SELECT UUID_TO_BIN(UUID()), t.id_turma, a.id_aluno
FROM tb_turma t, tb_aluno a
JOIN tb_usuario u ON a.id_usuario = u.id_usuario
WHERE t.codigo = 'BD-2024-A' AND u.email = 'julia.lima@aluno.com';

INSERT INTO tb_matricula (id_matricula, id_turma, id_aluno)
SELECT UUID_TO_BIN(UUID()), t.id_turma, a.id_aluno
FROM tb_turma t, tb_aluno a
JOIN tb_usuario u ON a.id_usuario = u.id_usuario
WHERE t.codigo = 'ED-2024-A' AND u.email = 'julia.lima@aluno.com';

-- Aluno Lucas em 2 turmas
INSERT INTO tb_matricula (id_matricula, id_turma, id_aluno)
SELECT UUID_TO_BIN(UUID()), t.id_turma, a.id_aluno
FROM tb_turma t, tb_aluno a
JOIN tb_usuario u ON a.id_usuario = u.id_usuario
WHERE t.codigo = 'POO-2024-B' AND u.email = 'lucas.santos@aluno.com';

INSERT INTO tb_matricula (id_matricula, id_turma, id_aluno)
SELECT UUID_TO_BIN(UUID()), t.id_turma, a.id_aluno
FROM tb_turma t, tb_aluno a
JOIN tb_usuario u ON a.id_usuario = u.id_usuario
WHERE t.codigo = 'WEB-2024-A' AND u.email = 'lucas.santos@aluno.com';

-- Aluna Beatriz em 3 turmas
INSERT INTO tb_matricula (id_matricula, id_turma, id_aluno)
SELECT UUID_TO_BIN(UUID()), t.id_turma, a.id_aluno
FROM tb_turma t, tb_aluno a
JOIN tb_usuario u ON a.id_usuario = u.id_usuario
WHERE t.codigo = 'POO-2024-B' AND u.email = 'beatriz.silva@aluno.com';

INSERT INTO tb_matricula (id_matricula, id_turma, id_aluno)
SELECT UUID_TO_BIN(UUID()), t.id_turma, a.id_aluno
FROM tb_turma t, tb_aluno a
JOIN tb_usuario u ON a.id_usuario = u.id_usuario
WHERE t.codigo = 'ES-2024-A' AND u.email = 'beatriz.silva@aluno.com';

INSERT INTO tb_matricula (id_matricula, id_turma, id_aluno)
SELECT UUID_TO_BIN(UUID()), t.id_turma, a.id_aluno
FROM tb_turma t, tb_aluno a
JOIN tb_usuario u ON a.id_usuario = u.id_usuario
WHERE t.codigo = 'ED-2024-A' AND u.email = 'beatriz.silva@aluno.com';

-- Aluno Rafael em 2 turmas
INSERT INTO tb_matricula (id_matricula, id_turma, id_aluno)
SELECT UUID_TO_BIN(UUID()), t.id_turma, a.id_aluno
FROM tb_turma t, tb_aluno a
JOIN tb_usuario u ON a.id_usuario = u.id_usuario
WHERE t.codigo = 'BD-2024-A' AND u.email = 'rafael.souza@aluno.com';

INSERT INTO tb_matricula (id_matricula, id_turma, id_aluno)
SELECT UUID_TO_BIN(UUID()), t.id_turma, a.id_aluno
FROM tb_turma t, tb_aluno a
JOIN tb_usuario u ON a.id_usuario = u.id_usuario
WHERE t.codigo = 'ES-2024-A' AND u.email = 'rafael.souza@aluno.com';

-- Aluna Fernanda em 2 turmas
INSERT INTO tb_matricula (id_matricula, id_turma, id_aluno)
SELECT UUID_TO_BIN(UUID()), t.id_turma, a.id_aluno
FROM tb_turma t, tb_aluno a
JOIN tb_usuario u ON a.id_usuario = u.id_usuario
WHERE t.codigo = 'WEB-2024-A' AND u.email = 'fernanda.rodrigues@aluno.com';

INSERT INTO tb_matricula (id_matricula, id_turma, id_aluno)
SELECT UUID_TO_BIN(UUID()), t.id_turma, a.id_aluno
FROM tb_turma t, tb_aluno a
JOIN tb_usuario u ON a.id_usuario = u.id_usuario
WHERE t.codigo = 'ED-2024-A' AND u.email = 'fernanda.rodrigues@aluno.com';

-- ============================================
-- 7. INSERIR EVENTOS
-- ============================================

INSERT INTO tb_evento (id_evento, nome, data, local, informacoes, id_professor, id_turma)
SELECT UUID_TO_BIN(UUID()), 'Workshop de Spring Boot', '2024-12-15 14:00:00', 'Laboratório 1', 
       'Workshop prático sobre desenvolvimento com Spring Boot e APIs REST',
       p.id_professor, t.id_turma
FROM tb_professor p
JOIN tb_usuario u ON p.id_usuario = u.id_usuario
JOIN tb_turma t ON t.codigo = 'POO-2024-A'
WHERE u.email = 'joao.silva@meucampus.com';

INSERT INTO tb_evento (id_evento, nome, data, local, informacoes, id_professor, id_turma)
SELECT UUID_TO_BIN(UUID()), 'Palestra: Carreira em TI', '2024-12-20 19:00:00', 'Auditório Principal',
       'Palestra sobre oportunidades e tendências na área de Tecnologia da Informação',
       p.id_professor, NULL
FROM tb_professor p
JOIN tb_usuario u ON p.id_usuario = u.id_usuario
WHERE u.email = 'carlos.souza@meucampus.com';

INSERT INTO tb_evento (id_evento, nome, data, local, informacoes, id_professor, id_turma)
SELECT UUID_TO_BIN(UUID()), 'Hackathon 2024', '2025-01-10 08:00:00', 'Campus Central',
       'Maratona de programação com desafios reais e premiação',
       p.id_professor, NULL
FROM tb_professor p
JOIN tb_usuario u ON p.id_usuario = u.id_usuario
WHERE u.email = 'joao.silva@meucampus.com';

-- ============================================
-- 8. INSERIR NOTIFICAÇÕES
-- ============================================

INSERT INTO tb_notificacao (id_notificacao, titulo, conteudo, data, status, id_turma, id_professor)
SELECT UUID_TO_BIN(UUID()), 'Prova Agendada', 
       'A prova de POO está agendada para o dia 18/12/2024 às 14h. Estudem os capítulos 1 a 5.',
       NOW(), 'Nao lida', t.id_turma, p.id_professor
FROM tb_turma t
JOIN tb_professor p ON t.id_professor = p.id_professor
WHERE t.codigo = 'POO-2024-A';

INSERT INTO tb_notificacao (id_notificacao, titulo, conteudo, data, status, id_turma, id_professor)
SELECT UUID_TO_BIN(UUID()), 'Material Disponível',
       'Novo material de estudo disponível na plataforma. Acessem o portal do aluno.',
       NOW(), 'Nao lida', t.id_turma, p.id_professor
FROM tb_turma t
JOIN tb_professor p ON t.id_professor = p.id_professor
WHERE t.codigo = 'BD-2024-A';

INSERT INTO tb_notificacao (id_notificacao, titulo, conteudo, data, status, id_turma, id_professor)
SELECT UUID_TO_BIN(UUID()), 'Trabalho em Grupo',
       'Formem grupos de até 4 pessoas para o trabalho final. Prazo de entrega: 20/12/2024.',
       NOW(), 'Nao lida', t.id_turma, p.id_professor
FROM tb_turma t
JOIN tb_professor p ON t.id_professor = p.id_professor
WHERE t.codigo = 'WEB-2024-A';

-- ============================================
-- 9. INSERIR PROFESSOR-MATÉRIA (Relacionamento)
-- ============================================

INSERT INTO tb_professor_materia (id_professor_materia, id_materia, id_professor)
SELECT UUID_TO_BIN(UUID()), m.id_materia, p.id_professor
FROM tb_materia m, tb_professor p
JOIN tb_usuario u ON p.id_usuario = u.id_usuario
WHERE m.nome = 'Programação Orientada a Objetos' AND u.email = 'joao.silva@meucampus.com';

INSERT INTO tb_professor_materia (id_professor_materia, id_materia, id_professor)
SELECT UUID_TO_BIN(UUID()), m.id_materia, p.id_professor
FROM tb_materia m, tb_professor p
JOIN tb_usuario u ON p.id_usuario = u.id_usuario
WHERE m.nome = 'Banco de Dados' AND u.email = 'maria.oliveira@meucampus.com';

INSERT INTO tb_professor_materia (id_professor_materia, id_materia, id_professor)
SELECT UUID_TO_BIN(UUID()), m.id_materia, p.id_professor
FROM tb_materia m, tb_professor p
JOIN tb_usuario u ON p.id_usuario = u.id_usuario
WHERE m.nome = 'Desenvolvimento Web' AND u.email = 'carlos.souza@meucampus.com';

INSERT INTO tb_professor_materia (id_professor_materia, id_materia, id_professor)
SELECT UUID_TO_BIN(UUID()), m.id_materia, p.id_professor
FROM tb_materia m, tb_professor p
JOIN tb_usuario u ON p.id_usuario = u.id_usuario
WHERE m.nome = 'Engenharia de Software' AND u.email = 'carlos.souza@meucampus.com';

INSERT INTO tb_professor_materia (id_professor_materia, id_materia, id_professor)
SELECT UUID_TO_BIN(UUID()), m.id_materia, p.id_professor
FROM tb_materia m, tb_professor p
JOIN tb_usuario u ON p.id_usuario = u.id_usuario
WHERE m.nome = 'Estrutura de Dados' AND u.email = 'ana.ferreira@meucampus.com';

-- ============================================
-- FIM DO SCRIPT
-- ============================================

-- Verificar dados inseridos
SELECT 'Usuários cadastrados:' AS Info, COUNT(*) AS Total FROM tb_usuario;
SELECT 'Professores cadastrados:' AS Info, COUNT(*) AS Total FROM tb_professor;
SELECT 'Alunos cadastrados:' AS Info, COUNT(*) AS Total FROM tb_aluno;
SELECT 'Matérias cadastradas:' AS Info, COUNT(*) AS Total FROM tb_materia;
SELECT 'Turmas cadastradas:' AS Info, COUNT(*) AS Total FROM tb_turma;
SELECT 'Matrículas cadastradas:' AS Info, COUNT(*) AS Total FROM tb_matricula;
SELECT 'Eventos cadastrados:' AS Info, COUNT(*) AS Total FROM tb_evento;
SELECT 'Notificações cadastradas:' AS Info, COUNT(*) AS Total FROM tb_notificacao;

-- ============================================
-- CREDENCIAIS DE ACESSO
-- ============================================
-- 
-- PROFESSOR:
-- Email: joao.silva@meucampus.com
-- Senha: prof123
-- 
-- ALUNO:
-- Email: pedro.alves@aluno.com
-- Senha: aluno123
-- 
-- ADMIN:
-- Email: admin@meucampus.com
-- Senha: admin123
-- ============================================
