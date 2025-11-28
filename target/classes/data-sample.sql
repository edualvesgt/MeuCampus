-- ============================================
-- SCRIPT DE POPULAÇÃO DO BANCO DE DADOS
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
INSERT INTO tb_usuario (id_usuario, nome, email, senha, celular) VALUES
(UNHEX(REPLACE('11111111-1111-1111-1111-111111111111', '-', '')), 'Administrador Sistema', 'admin@meucampus.com', 'admin123', '(11) 99999-0000');

-- Usuários Professores
INSERT INTO tb_usuario (id_usuario, nome, email, senha, celular) VALUES
(UNHEX(REPLACE('22222222-2222-2222-2222-222222222221', '-', '')), 'João Silva Santos', 'joao.silva@meucampus.com', 'prof123', '(11) 98765-4321'),
(UNHEX(REPLACE('22222222-2222-2222-2222-222222222222', '-', '')), 'Maria Oliveira Costa', 'maria.oliveira@meucampus.com', 'prof123', '(11) 98765-4322'),
(UNHEX(REPLACE('22222222-2222-2222-2222-222222222223', '-', '')), 'Carlos Eduardo Souza', 'carlos.souza@meucampus.com', 'prof123', '(11) 98765-4323'),
(UNHEX(REPLACE('22222222-2222-2222-2222-222222222224', '-', '')), 'Ana Paula Ferreira', 'ana.ferreira@meucampus.com', 'prof123', '(11) 98765-4324');

-- Usuários Alunos
INSERT INTO tb_usuario (id_usuario, nome, email, senha, celular) VALUES
(UNHEX(REPLACE('33333333-3333-3333-3333-333333333331', '-', '')), 'Pedro Henrique Alves', 'pedro.alves@aluno.com', 'aluno123', '(11) 91234-5671'),
(UNHEX(REPLACE('33333333-3333-3333-3333-333333333332', '-', '')), 'Julia Martins Lima', 'julia.lima@aluno.com', 'aluno123', '(11) 91234-5672'),
(UNHEX(REPLACE('33333333-3333-3333-3333-333333333333', '-', '')), 'Lucas Gabriel Santos', 'lucas.santos@aluno.com', 'aluno123', '(11) 91234-5673'),
(UNHEX(REPLACE('33333333-3333-3333-3333-333333333334', '-', '')), 'Beatriz Costa Silva', 'beatriz.silva@aluno.com', 'aluno123', '(11) 91234-5674'),
(UNHEX(REPLACE('33333333-3333-3333-3333-333333333335', '-', '')), 'Rafael Oliveira Souza', 'rafael.souza@aluno.com', 'aluno123', '(11) 91234-5675'),
(UNHEX(REPLACE('33333333-3333-3333-3333-333333333336', '-', '')), 'Fernanda Rodrigues', 'fernanda.rodrigues@aluno.com', 'aluno123', '(11) 91234-5676');

-- ============================================
-- 2. INSERIR PROFESSORES
-- ============================================

INSERT INTO tb_professor (id_professor, formacao, id_usuario) VALUES
(UNHEX(REPLACE('44444444-4444-4444-4444-444444444441', '-', '')), 'Mestrado em Ciência da Computação', UNHEX(REPLACE('22222222-2222-2222-2222-222222222221', '-', ''))),
(UNHEX(REPLACE('44444444-4444-4444-4444-444444444442', '-', '')), 'Doutorado em Matemática Aplicada', UNHEX(REPLACE('22222222-2222-2222-2222-222222222222', '-', ''))),
(UNHEX(REPLACE('44444444-4444-4444-4444-444444444443', '-', '')), 'Mestrado em Engenharia de Software', UNHEX(REPLACE('22222222-2222-2222-2222-222222222223', '-', ''))),
(UNHEX(REPLACE('44444444-4444-4444-4444-444444444444', '-', '')), 'Doutorado em Banco de Dados', UNHEX(REPLACE('22222222-2222-2222-2222-222222222224', '-', '')));

-- ============================================
-- 3. INSERIR ALUNOS
-- ============================================

INSERT INTO tb_aluno (id_aluno, matricula, data_ingresso, situacao, id_usuario) VALUES
(UNHEX(REPLACE('55555555-5555-5555-5555-555555555551', '-', '')), '2024001', '2024-02-01', 'Ativo', UNHEX(REPLACE('33333333-3333-3333-3333-333333333331', '-', ''))),
(UNHEX(REPLACE('55555555-5555-5555-5555-555555555552', '-', '')), '2024002', '2024-02-01', 'Ativo', UNHEX(REPLACE('33333333-3333-3333-3333-333333333332', '-', ''))),
(UNHEX(REPLACE('55555555-5555-5555-5555-555555555553', '-', '')), '2024003', '2024-02-01', 'Ativo', UNHEX(REPLACE('33333333-3333-3333-3333-333333333333', '-', ''))),
(UNHEX(REPLACE('55555555-5555-5555-5555-555555555554', '-', '')), '2024004', '2024-02-01', 'Ativo', UNHEX(REPLACE('33333333-3333-3333-3333-333333333334', '-', ''))),
(UNHEX(REPLACE('55555555-5555-5555-5555-555555555555', '-', '')), '2024005', '2024-02-01', 'Ativo', UNHEX(REPLACE('33333333-3333-3333-3333-333333333335', '-', ''))),
(UNHEX(REPLACE('55555555-5555-5555-5555-555555555556', '-', '')), '2024006', '2024-02-01', 'Ativo', UNHEX(REPLACE('33333333-3333-3333-3333-333333333336', '-', '')));

-- ============================================
-- 4. INSERIR MATÉRIAS
-- ============================================

INSERT INTO tb_materia (id_materia, nome, descricao) VALUES
(UNHEX(REPLACE('66666666-6666-6666-6666-666666666661', '-', '')), 'Programação Orientada a Objetos', 'Conceitos fundamentais de POO, classes, objetos, herança e polimorfismo'),
(UNHEX(REPLACE('66666666-6666-6666-6666-666666666662', '-', '')), 'Banco de Dados', 'Modelagem, SQL, normalização e administração de bancos de dados'),
(UNHEX(REPLACE('66666666-6666-6666-6666-666666666663', '-', '')), 'Desenvolvimento Web', 'HTML, CSS, JavaScript, frameworks modernos e APIs REST'),
(UNHEX(REPLACE('66666666-6666-6666-6666-666666666664', '-', '')), 'Estrutura de Dados', 'Listas, pilhas, filas, árvores, grafos e algoritmos'),
(UNHEX(REPLACE('66666666-6666-6666-6666-666666666665', '-', '')), 'Engenharia de Software', 'Metodologias ágeis, padrões de projeto e boas práticas');

-- ============================================
-- 5. INSERIR TURMAS
-- ============================================

INSERT INTO tb_turma (id_turma, nome, codigo, periodo, horario, sala, id_professor, id_materia) VALUES
-- Turmas do Professor João Silva
(UNHEX(REPLACE('77777777-7777-7777-7777-777777777771', '-', '')), 'POO - Turma A', 'POO-2024-A', '2024/2', 'Segunda e Quarta 14h-16h', 'Lab 101', UNHEX(REPLACE('44444444-4444-4444-4444-444444444441', '-', '')), UNHEX(REPLACE('66666666-6666-6666-6666-666666666661', '-', ''))),
(UNHEX(REPLACE('77777777-7777-7777-7777-777777777772', '-', '')), 'POO - Turma B', 'POO-2024-B', '2024/2', 'Terça e Quinta 16h-18h', 'Lab 102', UNHEX(REPLACE('44444444-4444-4444-4444-444444444441', '-', '')), UNHEX(REPLACE('66666666-6666-6666-6666-666666666661', '-', ''))),

-- Turmas da Professora Maria Oliveira
(UNHEX(REPLACE('77777777-7777-7777-7777-777777777773', '-', '')), 'Banco de Dados - Turma A', 'BD-2024-A', '2024/2', 'Segunda e Quarta 16h-18h', 'Lab 201', UNHEX(REPLACE('44444444-4444-4444-4444-444444444442', '-', '')), UNHEX(REPLACE('66666666-6666-6666-6666-666666666662', '-', ''))),

-- Turmas do Professor Carlos Eduardo
(UNHEX(REPLACE('77777777-7777-7777-7777-777777777774', '-', '')), 'Desenvolvimento Web - Turma A', 'WEB-2024-A', '2024/2', 'Terça e Quinta 14h-16h', 'Lab 103', UNHEX(REPLACE('44444444-4444-4444-4444-444444444443', '-', '')), UNHEX(REPLACE('66666666-6666-6666-6666-666666666663', '-', ''))),
(UNHEX(REPLACE('77777777-7777-7777-7777-777777777775', '-', '')), 'Engenharia de Software - Turma A', 'ES-2024-A', '2024/2', 'Sexta 14h-18h', 'Sala 301', UNHEX(REPLACE('44444444-4444-4444-4444-444444444443', '-', '')), UNHEX(REPLACE('66666666-6666-6666-6666-666666666665', '-', ''))),

-- Turmas da Professora Ana Paula
(UNHEX(REPLACE('77777777-7777-7777-7777-777777777776', '-', '')), 'Estrutura de Dados - Turma A', 'ED-2024-A', '2024/2', 'Segunda e Quarta 10h-12h', 'Lab 104', UNHEX(REPLACE('44444444-4444-4444-4444-444444444444', '-', '')), UNHEX(REPLACE('66666666-6666-6666-6666-666666666664', '-', '')));

-- ============================================
-- 6. INSERIR MATRÍCULAS (Alunos nas Turmas)
-- ============================================

-- Aluno Pedro em 3 turmas
INSERT INTO tb_matricula (id_matricula, id_turma, id_aluno) VALUES
(UNHEX(REPLACE('88888888-8888-8888-8888-888888888881', '-', '')), UNHEX(REPLACE('77777777-7777-7777-7777-777777777771', '-', '')), UNHEX(REPLACE('55555555-5555-5555-5555-555555555551', '-', ''))),
(UNHEX(REPLACE('88888888-8888-8888-8888-888888888882', '-', '')), UNHEX(REPLACE('77777777-7777-7777-7777-777777777773', '-', '')), UNHEX(REPLACE('55555555-5555-5555-5555-555555555551', '-', ''))),
(UNHEX(REPLACE('88888888-8888-8888-8888-888888888883', '-', '')), UNHEX(REPLACE('77777777-7777-7777-7777-777777777774', '-', '')), UNHEX(REPLACE('55555555-5555-5555-5555-555555555551', '-', '')));

-- Aluna Julia em 3 turmas
INSERT INTO tb_matricula (id_matricula, id_turma, id_aluno) VALUES
(UNHEX(REPLACE('88888888-8888-8888-8888-888888888884', '-', '')), UNHEX(REPLACE('77777777-7777-7777-7777-777777777771', '-', '')), UNHEX(REPLACE('55555555-5555-5555-5555-555555555552', '-', ''))),
(UNHEX(REPLACE('88888888-8888-8888-8888-888888888885', '-', '')), UNHEX(REPLACE('77777777-7777-7777-7777-777777777773', '-', '')), UNHEX(REPLACE('55555555-5555-5555-5555-555555555552', '-', ''))),
(UNHEX(REPLACE('88888888-8888-8888-8888-888888888886', '-', '')), UNHEX(REPLACE('77777777-7777-7777-7777-777777777776', '-', '')), UNHEX(REPLACE('55555555-5555-5555-5555-555555555552', '-', '')));

-- Aluno Lucas em 2 turmas
INSERT INTO tb_matricula (id_matricula, id_turma, id_aluno) VALUES
(UNHEX(REPLACE('88888888-8888-8888-8888-888888888887', '-', '')), UNHEX(REPLACE('77777777-7777-7777-7777-777777777772', '-', '')), UNHEX(REPLACE('55555555-5555-5555-5555-555555555553', '-', ''))),
(UNHEX(REPLACE('88888888-8888-8888-8888-888888888888', '-', '')), UNHEX(REPLACE('77777777-7777-7777-7777-777777777774', '-', '')), UNHEX(REPLACE('55555555-5555-5555-5555-555555555553', '-', '')));

-- Aluna Beatriz em 3 turmas
INSERT INTO tb_matricula (id_matricula, id_turma, id_aluno) VALUES
(UNHEX(REPLACE('88888888-8888-8888-8888-888888888889', '-', '')), UNHEX(REPLACE('77777777-7777-7777-7777-777777777772', '-', '')), UNHEX(REPLACE('55555555-5555-5555-5555-555555555554', '-', ''))),
(UNHEX(REPLACE('88888888-8888-8888-8888-888888888890', '-', '')), UNHEX(REPLACE('77777777-7777-7777-7777-777777777775', '-', '')), UNHEX(REPLACE('55555555-5555-5555-5555-555555555554', '-', ''))),
(UNHEX(REPLACE('88888888-8888-8888-8888-888888888891', '-', '')), UNHEX(REPLACE('77777777-7777-7777-7777-777777777776', '-', '')), UNHEX(REPLACE('55555555-5555-5555-5555-555555555554', '-', '')));

-- Aluno Rafael em 2 turmas
INSERT INTO tb_matricula (id_matricula, id_turma, id_aluno) VALUES
(UNHEX(REPLACE('88888888-8888-8888-8888-888888888892', '-', '')), UNHEX(REPLACE('77777777-7777-7777-7777-777777777773', '-', '')), UNHEX(REPLACE('55555555-5555-5555-5555-555555555555', '-', ''))),
(UNHEX(REPLACE('88888888-8888-8888-8888-888888888893', '-', '')), UNHEX(REPLACE('77777777-7777-7777-7777-777777777775', '-', '')), UNHEX(REPLACE('55555555-5555-5555-5555-555555555555', '-', '')));

-- Aluna Fernanda em 2 turmas
INSERT INTO tb_matricula (id_matricula, id_turma, id_aluno) VALUES
(UNHEX(REPLACE('88888888-8888-8888-8888-888888888894', '-', '')), UNHEX(REPLACE('77777777-7777-7777-7777-777777777774', '-', '')), UNHEX(REPLACE('55555555-5555-5555-5555-555555555556', '-', ''))),
(UNHEX(REPLACE('88888888-8888-8888-8888-888888888895', '-', '')), UNHEX(REPLACE('77777777-7777-7777-7777-777777777776', '-', '')), UNHEX(REPLACE('55555555-5555-5555-5555-555555555556', '-', '')));

-- ============================================
-- 7. INSERIR EVENTOS
-- ============================================

INSERT INTO tb_evento (id_evento, nome, data, local, informacoes, id_professor, id_turma) VALUES
(UNHEX(REPLACE('99999999-9999-9999-9999-999999999991', '-', '')), 'Workshop de Spring Boot', '2024-12-15 14:00:00', 'Laboratório 1', 'Workshop prático sobre desenvolvimento com Spring Boot e APIs REST', UNHEX(REPLACE('44444444-4444-4444-4444-444444444441', '-', '')), UNHEX(REPLACE('77777777-7777-7777-7777-777777777771', '-', ''))),
(UNHEX(REPLACE('99999999-9999-9999-9999-999999999992', '-', '')), 'Palestra: Carreira em TI', '2024-12-20 19:00:00', 'Auditório Principal', 'Palestra sobre oportunidades e tendências na área de Tecnologia da Informação', UNHEX(REPLACE('44444444-4444-4444-4444-444444444443', '-', '')), NULL),
(UNHEX(REPLACE('99999999-9999-9999-9999-999999999993', '-', '')), 'Hackathon 2024', '2025-01-10 08:00:00', 'Campus Central', 'Maratona de programação com desafios reais e premiação', UNHEX(REPLACE('44444444-4444-4444-4444-444444444441', '-', '')), NULL);

-- ============================================
-- 8. INSERIR NOTIFICAÇÕES
-- ============================================

INSERT INTO tb_notificacao (id_notificacao, titulo, conteudo, data, status, id_turma, id_professor) VALUES
(UNHEX(REPLACE('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa1', '-', '')), 'Prova Agendada', 'A prova de POO está agendada para o dia 18/12/2024 às 14h. Estudem os capítulos 1 a 5.', NOW(), 'Nao lida', UNHEX(REPLACE('77777777-7777-7777-7777-777777777771', '-', '')), UNHEX(REPLACE('44444444-4444-4444-4444-444444444441', '-', ''))),
(UNHEX(REPLACE('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2', '-', '')), 'Material Disponível', 'Novo material de estudo disponível na plataforma. Acessem o portal do aluno.', NOW(), 'Nao lida', UNHEX(REPLACE('77777777-7777-7777-7777-777777777773', '-', '')), UNHEX(REPLACE('44444444-4444-4444-4444-444444444442', '-', ''))),
(UNHEX(REPLACE('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa3', '-', '')), 'Trabalho em Grupo', 'Formem grupos de até 4 pessoas para o trabalho final. Prazo de entrega: 20/12/2024.', NOW(), 'Nao lida', UNHEX(REPLACE('77777777-7777-7777-7777-777777777774', '-', '')), UNHEX(REPLACE('44444444-4444-4444-4444-444444444443', '-', '')));

-- ============================================
-- 9. INSERIR PROFESSOR-MATÉRIA (Relacionamento)
-- ============================================

INSERT INTO tb_professor_materia (id_professor_materia, id_materia, id_professor) VALUES
(UNHEX(REPLACE('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb1', '-', '')), UNHEX(REPLACE('66666666-6666-6666-6666-666666666661', '-', '')), UNHEX(REPLACE('44444444-4444-4444-4444-444444444441', '-', ''))),
(UNHEX(REPLACE('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb2', '-', '')), UNHEX(REPLACE('66666666-6666-6666-6666-666666666662', '-', '')), UNHEX(REPLACE('44444444-4444-4444-4444-444444444442', '-', ''))),
(UNHEX(REPLACE('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb3', '-', '')), UNHEX(REPLACE('66666666-6666-6666-6666-666666666663', '-', '')), UNHEX(REPLACE('44444444-4444-4444-4444-444444444443', '-', ''))),
(UNHEX(REPLACE('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb4', '-', '')), UNHEX(REPLACE('66666666-6666-6666-6666-666666666665', '-', '')), UNHEX(REPLACE('44444444-4444-4444-4444-444444444443', '-', ''))),
(UNHEX(REPLACE('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb5', '-', '')), UNHEX(REPLACE('66666666-6666-6666-6666-666666666664', '-', '')), UNHEX(REPLACE('44444444-4444-4444-4444-444444444444', '-', '')));

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
