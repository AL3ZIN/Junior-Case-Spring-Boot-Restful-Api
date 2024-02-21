package com.itau.carros.application.core.validations;

import com.itau.carros.application.core.exception.CarroJaCadastradoException;
import com.itau.carros.application.core.model.Carro;
import com.itau.carros.application.ports.out.CarroRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.ValidationException;

@Component
public class ValidacaoCarroJaCadastrado implements ValidacaoCadastroCarro{

    private final CarroRepositoryPort carroRepositoryPort;

    public ValidacaoCarroJaCadastrado(CarroRepositoryPort carroRepositoryPort) {
        this.carroRepositoryPort = carroRepositoryPort;
    }


    @Override
    public void validar(Carro carro) {
        boolean dadosJaCadastrados = carroRepositoryPort.existsByChassiOrPlacaAndAtivoIsTrue(carro.getChassi(), carro.getPlaca());
        if (dadosJaCadastrados){
            throw new CarroJaCadastradoException("Carro ja cadastrado em nosso banco de dados.");
        }
    }
}
