package com.itau.carros.application.core.validation;

import com.itau.carros.application.core.exception.UnicidadeException;
import com.itau.carros.application.core.model.Carro;
import com.itau.carros.application.ports.out.CarroRepositoryPort;
import org.springframework.stereotype.Component;


@Component
public class ValidacaoCarroJaCadastrado implements Validator<Carro> {

    private final CarroRepositoryPort carroRepositoryPort;

    public ValidacaoCarroJaCadastrado(CarroRepositoryPort carroRepositoryPort) {
        this.carroRepositoryPort = carroRepositoryPort;
    }

    @Override
    public void validar(Carro model) {
        boolean dadosJaCadastrados = carroRepositoryPort.existsByChassiAndPlacaAndAtivoTrue(model.getChassi(), model.getPlaca());
        if (dadosJaCadastrados) {
            throw new UnicidadeException("Carro ja cadastrado em nosso banco de dados.");
        }
    }
}
