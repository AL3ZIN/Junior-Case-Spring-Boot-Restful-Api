CREATE TABLE carro (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    chassi VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    manufacturer VARCHAR(255),
    year INT,
    color VARCHAR(50),
    status VARCHAR(50),
    placa VARCHAR(50) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE
);