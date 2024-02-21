
CREATE TABLE carro (
    id BIGSERIAL PRIMARY KEY,
    chassi VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    manufacturer VARCHAR(255),
    year INTEGER,
    color VARCHAR(50),
    status VARCHAR(50),
    placa VARCHAR(50) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE UNIQUE INDEX idx_unique_chassi_ativo ON carro (chassi) WHERE ativo = TRUE;
CREATE UNIQUE INDEX idx_unique_placa_ativo ON carro (placa) WHERE ativo = TRUE;
