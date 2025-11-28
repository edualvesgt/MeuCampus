-- ============================================
-- SCRIPT DE CORREÇÃO DO BANCO DE DADOS
-- Execute este script para corrigir os problemas
-- ============================================

-- 1. Adicionar colunas faltantes na tabela tb_turma
ALTER TABLE tb_turma 
ADD COLUMN IF NOT EXISTS codigo VARCHAR(50),
ADD COLUMN IF NOT EXISTS periodo VARCHAR(50),
ADD COLUMN IF NOT EXISTS horario VARCHAR(100),
ADD COLUMN IF NOT EXISTS sala VARCHAR(50);

-- 2. Atualizar dados das turmas existentes
UPDATE tb_turma SET 
    codigo = 'POO-2024-A',
    periodo = '2024/2',
    horario = 'Segunda e Quarta 14h-16h',
    sala = 'Lab 101'
WHERE id_turma = UNHEX(REPLACE('77777777-7777-7777-7777-777777777771', '-', ''));

UPDATE tb_turma SET 
    codigo = 'POO-2024-B',
    periodo = '2024/2',
    horario = 'Terça e Quinta 16h-18h',
    sala = 'Lab 102'
WHERE id_turma = UNHEX(REPLACE('77777777-7777-7777-7777-777777777772', '-', ''));

UPDATE tb_turma SET 
    codigo = 'BD-2024-A',
    periodo = '2024/2',
    horario = 'Segunda e Quarta 16h-18h',
    sala = 'Lab 201'
WHERE id_turma = UNHEX(REPLACE('77777777-7777-7777-7777-777777777773', '-', ''));

UPDATE tb_turma SET 
    codigo = 'WEB-2024-A',
    periodo = '2024/2',
    horario = 'Terça e Quinta 14h-16h',
    sala = 'Lab 103'
WHERE id_turma = UNHEX(REPLACE('77777777-7777-7777-7777-777777777774', '-', ''));

UPDATE tb_turma SET 
    codigo = 'ES-2024-A',
    periodo = '2024/2',
    horario = 'Sexta 14h-18h',
    sala = 'Sala 301'
WHERE id_turma = UNHEX(REPLACE('77777777-7777-7777-7777-777777777775', '-', ''));

UPDATE tb_turma SET 
    codigo = 'ED-2024-A',
    periodo = '2024/2',
    horario = 'Segunda e Quarta 10h-12h',
    sala = 'Lab 104'
WHERE id_turma = UNHEX(REPLACE('77777777-7777-7777-7777-777777777776', '-', ''));

-- 3. Verificar se os eventos existem, se não, inserir
DELETE FROM tb_evento WHERE id_evento IN (
    UNHEX(REPLACE('99999999-9999-9999-9999-999999999991', '-', '')),
    UNHEX(REPLACE('99999999-9999-9999-9999-999999999992', '-', '')),
    UNHEX(REPLACE('99999999-9999-9999-9999-999999999993', '-', ''))
);

INSERT INTO tb_evento (id_evento, nome, data, local, informacoes, id_professor, id_turma) VALUES
(UNHEX(REPLACE('99999999-9999-9999-9999-999999999991', '-', '')), 'Workshop de Spring Boot', '2024-12-15 14:00:00', 'Laboratório 1', 'Workshop prático sobre desenvolvimento com Spring Boot e APIs REST', UNHEX(REPLACE('44444444-4444-4444-4444-444444444441', '-', '')), UNHEX(REPLACE('77777777-7777-7777-7777-777777777771', '-', ''))),
(UNHEX(REPLACE('99999999-9999-9999-9999-999999999992', '-', '')), 'Palestra: Carreira em TI', '2024-12-20 19:00:00', 'Auditório Principal', 'Palestra sobre oportunidades e tendências na área de Tecnologia da Informação', UNHEX(REPLACE('44444444-4444-4444-4444-444444444443', '-', '')), NULL),
(UNHEX(REPLACE('99999999-9999-9999-9999-999999999993', '-', '')), 'Hackathon 2024', '2025-01-10 08:00:00', 'Campus Central', 'Maratona de programação com desafios reais e premiação', UNHEX(REPLACE('44444444-4444-4444-4444-444444444441', '-', '')), NULL);

-- 4. Verificar dados
SELECT 'Turmas com dados completos:' AS Info, COUNT(*) AS Total FROM tb_turma WHERE codigo IS NOT NULL;
SELECT 'Eventos cadastrados:' AS Info, COUNT(*) AS Total FROM tb_evento;

-- ============================================
-- FIM DO SCRIPT DE CORREÇÃO
-- ============================================
