package com.itau.carros.application.ports.in.carro;

import com.itau.carros.application.core.model.Carro;

public interface CreateCarroUseCasePort {
    Carro cadastrar(Carro carro);
}
