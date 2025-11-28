-- Adicionar campos faltantes na tabela tb_turma
-- Execute este script no seu banco de dados MySQL

-- Verificar se as colunas já existem antes de adicionar
ALTER TABLE tb_turma 
ADD COLUMN IF NOT EXISTS codigo VARCHAR(50),
ADD COLUMN IF NOT EXISTS periodo VARCHAR(50),
ADD COLUMN IF NOT EXISTS horario VARCHAR(100),
ADD COLUMN IF NOT EXISTS sala VARCHAR(50);

-- Atualizar dados existentes com valores padrão
UPDATE tb_turma SET 
    codigo = CONCAT('TURMA-', SUBSTRING(id_turma, 1, 8)),
    periodo = '2024/2',
    horario = 'A definir',
    sala = 'A definir'
WHERE codigo IS NULL;
