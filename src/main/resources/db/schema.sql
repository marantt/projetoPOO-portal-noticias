-- Schema SQL (core mínimo) para portal-noticias
-- Cria/ajusta apenas o necessário: autores, noticias e usuarios (admin)

-- Tabela autores (se não existir)
CREATE TABLE IF NOT EXISTS autores (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(255) NOT NULL,
  email VARCHAR(255) UNIQUE NOT NULL
);

-- Tabela noticias (se não existir)
CREATE TABLE IF NOT EXISTS noticias (
  id SERIAL PRIMARY KEY,
  titulo VARCHAR(255) NOT NULL,
  conteudo TEXT NOT NULL,
  data_publicacao TIMESTAMP WITH TIME ZONE DEFAULT now(),
  autor_id INTEGER NOT NULL,
  tipo_noticia VARCHAR(50) NOT NULL,
  url_foto TEXT,
  url_video TEXT,
  fonte_materia VARCHAR(255),
  CONSTRAINT fk_noticia_autor FOREIGN KEY (autor_id) REFERENCES autores(id) ON DELETE RESTRICT
);

-- Se a tabela ja existia mas não tem alguma coluna, adiciona (não destrutivo)
ALTER TABLE autores
  ADD COLUMN IF NOT EXISTS created_at TIMESTAMP WITH TIME ZONE DEFAULT now();

ALTER TABLE autores
  ADD COLUMN IF NOT EXISTS updated_at TIMESTAMP WITH TIME ZONE DEFAULT now();

ALTER TABLE noticias
  ADD COLUMN IF NOT EXISTS status VARCHAR(20) DEFAULT 'draft';

ALTER TABLE noticias
  ADD COLUMN IF NOT EXISTS published_at TIMESTAMP WITH TIME ZONE;

ALTER TABLE noticias
  ADD COLUMN IF NOT EXISTS created_at TIMESTAMP WITH TIME ZONE DEFAULT now();

ALTER TABLE noticias
  ADD COLUMN IF NOT EXISTS updated_at TIMESTAMP WITH TIME ZONE DEFAULT now();

-- Tabela usuarios (admin) - para gerenciar acesso ao painel
CREATE TABLE IF NOT EXISTS usuarios (
  id SERIAL PRIMARY KEY,
  username VARCHAR(100) NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  email VARCHAR(255) UNIQUE,
  active BOOLEAN NOT NULL DEFAULT true,
  created_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
  updated_at TIMESTAMP WITH TIME ZONE DEFAULT now()
);

-- Índices simples para performance de leitura
CREATE INDEX IF NOT EXISTS idx_noticias_data ON noticias(data_publicacao DESC);
CREATE INDEX IF NOT EXISTS idx_noticias_status ON noticias(status);
CREATE INDEX IF NOT EXISTS idx_autores_email ON autores(email);

-- Exemplo de insert de usuário admin (password_hash deve ser gerada com bcrypt/argon2 fora do DB)
-- INSERT INTO usuarios (username, password_hash, email) VALUES ('admin', '<bcrypt-hash-aqui>', 'admin@example.com');
