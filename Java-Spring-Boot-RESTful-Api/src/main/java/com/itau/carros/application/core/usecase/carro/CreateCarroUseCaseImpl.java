package com.itau.carros.application.core.usecase.carro;

import com.itau.carros.application.core.model.Carro;
import com.itau.carros.application.core.validation.Validator;
import com.itau.carros.application.ports.in.carro.CreateCarroUseCasePort;
import com.itau.carros.application.ports.out.CarroRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateCarroUseCaseImpl implements CreateCarroUseCasePort {

    private final List<Validator<Carro>> validacoes;
    private final CarroRepositoryPort carroRepositoryPort;

    public CreateCarroUseCaseImpl(List<Validator<Carro>> validacoes, CarroRepositoryPort carroRepositoryPort) {
        this.validacoes = validacoes;
        this.carroRepositoryPort = carroRepositoryPort;
    }

    @Override
    public Carro cadastrar(Carro carro) {
        validacoes.forEach(v -> v.validar(carro));
        return carroRepositoryPort.save(carro);
    }
}
