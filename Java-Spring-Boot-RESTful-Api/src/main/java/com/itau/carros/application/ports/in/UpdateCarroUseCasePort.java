package com.itau.carros.application.ports.in;

import com.itau.carros.application.core.enums.Status;
import com.itau.carros.application.core.model.Carro;

public interface UpdateCarroUseCasePort {
    Carro atualizarStatus(Long id, Status status);
}
