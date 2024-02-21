package com.itau.carros.application.core.usecase;

import com.itau.carros.application.core.enums.Status;
import com.itau.carros.application.core.exception.ConsultaNulaException;
import com.itau.carros.application.core.model.Carro;
import com.itau.carros.application.ports.in.UpdateCarroUseCasePort;
import com.itau.carros.application.ports.out.CarroRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class UpdateCarroUseCaseImpl implements UpdateCarroUseCasePort {

    private final CarroRepositoryPort carroRepositoryPort;

    public UpdateCarroUseCaseImpl(CarroRepositoryPort carroRepositoryPort) {
        this.carroRepositoryPort = carroRepositoryPort;
    }

    @Override
    public Carro atualizarStatus(Long id, Status status) {
        Carro carro = carroRepositoryPort.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new ConsultaNulaException(String.format("Nenhum carro de id: %d encontrado em nosso banco de dados.",id)));
        carro.setStatus(status);
        return carro;
    }
}
