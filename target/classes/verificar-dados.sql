-- ============================================
-- SCRIPT DE VERIFICAÇÃO DOS DADOS
-- Execute para verificar se os dados foram inseridos corretamente
-- ============================================

-- 1. Verificar usuários
SELECT 'USUÁRIOS' AS Tabela;
SELECT BIN_TO_UUID(id_usuario) as id, nome, email FROM tb_usuario;

-- 2. Verificar professores
SELECT 'PROFESSORES' AS Tabela;
SELECT BIN_TO_UUID(p.id_professor) as id_professor, 
       u.nome, u.email, p.formacao
FROM tb_professor p
JOIN tb_usuario u ON p.id_usuario = u.id_usuario;

-- 3. Verificar alunos
SELECT 'ALUNOS' AS Tabela;
SELECT BIN_TO_UUID(a.id_aluno) as id_aluno,
       u.nome, u.email, a.matricula, a.situacao
FROM tb_aluno a
JOIN tb_usuario u ON a.id_usuario = u.id_usuario;

-- 4. Verificar turmas
SELECT 'TURMAS' AS Tabela;
SELECT BIN_TO_UUID(t.id_turma) as id_turma,
       t.nome, t.codigo, t.periodo, t.horario, t.sala,
       u.nome as professor
FROM tb_turma t
JOIN tb_professor p ON t.id_professor = p.id_professor
JOIN tb_usuario u ON p.id_usuario = u.id_usuario;

-- 5. Verificar matrículas
SELECT 'MATRÍCULAS' AS Tabela;
SELECT u.nome as aluno, t.nome as turma, t.codigo
FROM tb_matricula m
JOIN tb_aluno a ON m.id_aluno = a.id_aluno
JOIN tb_usuario u ON a.id_usuario = u.id_usuario
JOIN tb_turma t ON m.id_turma = t.id_turma
ORDER BY u.nome;

-- 6. Verificar eventos
SELECT 'EVENTOS' AS Tabela;
SELECT e.nome, e.data, e.local, u.nome as professor
FROM tb_evento e
JOIN tb_professor p ON e.id_professor = p.id_professor
JOIN tb_usuario u ON p.id_usuario = u.id_usuario;

-- 7. Teste de login - Professor João
SELECT 'TESTE LOGIN PROFESSOR' AS Teste;
SELECT u.nome, u.email, 
       CASE WHEN p.id_professor IS NOT NULL THEN 'SIM' ELSE 'NÃO' END as eh_professor,
       CASE WHEN a.id_aluno IS NOT NULL THEN 'SIM' ELSE 'NÃO' END as eh_aluno
FROM tb_usuario u
LEFT JOIN tb_professor p ON u.id_usuario = p.id_usuario
LEFT JOIN tb_aluno a ON u.id_usuario = a.id_usuario
WHERE u.email = 'joao.silva@meucampus.com' AND u.senha = 'prof123';

-- 8. Teste de login - Aluno Pedro
SELECT 'TESTE LOGIN ALUNO' AS Teste;
SELECT u.nome, u.email,
       CASE WHEN p.id_professor IS NOT NULL THEN 'SIM' ELSE 'NÃO' END as eh_professor,
       CASE WHEN a.id_aluno IS NOT NULL THEN 'SIM' ELSE 'NÃO' END as eh_aluno
FROM tb_usuario u
LEFT JOIN tb_professor p ON u.id_usuario = p.id_usuario
LEFT JOIN tb_aluno a ON u.id_usuario = a.id_usuario
WHERE u.email = 'pedro.alves@aluno.com' AND u.senha = 'aluno123';

-- ============================================
-- RESUMO
-- ============================================
SELECT 'RESUMO' AS Info;
SELECT 
    (SELECT COUNT(*) FROM tb_usuario) as total_usuarios,
    (SELECT COUNT(*) FROM tb_professor) as total_professores,
    (SELECT COUNT(*) FROM tb_aluno) as total_alunos,
    (SELECT COUNT(*) FROM tb_turma) as total_turmas,
    (SELECT COUNT(*) FROM tb_matricula) as total_matriculas,
    (SELECT COUNT(*) FROM tb_evento) as total_eventos;
