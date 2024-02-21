package com.itau.carros.application.ports.in;

import com.itau.carros.application.core.model.Carro;

public interface CreateCarroUseCasePort {
    Long cadastrar(Carro carro);
}
