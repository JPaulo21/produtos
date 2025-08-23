CREATE TABLE produtos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(255),
    preco DECIMAL(10, 2) NOT NULL,
    quantidade_estoque INT NOT NULL,
    data_criacao DATETIME NOT NULL
);

ALTER TABLE produtos ADD CONSTRAINT chk_preco CHECK (preco > 0);
ALTER TABLE produtos ADD CONSTRAINT chk_quantidade CHECK (quantidade_estoque >= 0);