package com.itau.carros.application.core.validations;

import com.itau.carros.application.core.exception.CarroJaCadastradoException;
import com.itau.carros.application.core.model.Carro;
import com.itau.carros.application.ports.out.CarroRepositoryPort;
import org.springframework.stereotype.Component;


@Component
public class ValidacaoCarroJaCadastrado implements ValidacaoCadastroCarro{

    private final CarroRepositoryPort carroRepositoryPort;

    public ValidacaoCarroJaCadastrado(CarroRepositoryPort carroRepositoryPort) {
        this.carroRepositoryPort = carroRepositoryPort;
    }


    @Override
    public void validar(Carro carro) {
        boolean dadosJaCadastrados = carroRepositoryPort.existsByChassiAndPlacaAndAtivoTrue(carro.getChassi(), carro.getPlaca());
        if (dadosJaCadastrados){
            throw new CarroJaCadastradoException("Carro ja cadastrado em nosso banco de dados.");
        }
    }
}
