package com.itau.carros.application.core.usecase;

import com.itau.carros.application.ports.in.DeleteCarroUseCasePort;
import com.itau.carros.application.ports.out.CarroRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class DeleteCarroUseCaseImpl implements DeleteCarroUseCasePort {

    private final CarroRepositoryPort carroRepositoryPort;

    public DeleteCarroUseCaseImpl(CarroRepositoryPort carroRepositoryPort) {
        this.carroRepositoryPort = carroRepositoryPort;
    }

    @Override
    public void excluir(Long id) {
        carroRepositoryPort.delete(id);
    }
}
