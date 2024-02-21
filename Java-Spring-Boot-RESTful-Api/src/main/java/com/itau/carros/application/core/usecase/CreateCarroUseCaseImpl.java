package com.itau.carros.application.core.usecase;

import com.itau.carros.application.core.model.Carro;
import com.itau.carros.application.core.validations.ValidacaoCadastroCarro;
import com.itau.carros.application.ports.in.CreateCarroUseCasePort;
import com.itau.carros.application.ports.out.CarroRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateCarroUseCaseImpl implements CreateCarroUseCasePort {

    private final List<ValidacaoCadastroCarro> validacoes;

    @Autowired
    private final CarroRepositoryPort carroRepositoryPort;

    public CreateCarroUseCaseImpl(List<ValidacaoCadastroCarro> validacoes, CarroRepositoryPort carroRepositoryPort) {
        this.validacoes = validacoes;
        this.carroRepositoryPort = carroRepositoryPort;
    }

    @Override
    public Carro cadastrar(Carro carro) {
        validacoes.forEach(v-> v.validar(carro));
        return carroRepositoryPort.save(carro);
    }
}
