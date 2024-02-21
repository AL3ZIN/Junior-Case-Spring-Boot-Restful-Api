package com.itau.carros.application.core.usecase;

import com.itau.carros.application.core.exception.ConsultaNulaException;
import com.itau.carros.application.core.model.Carro;
import com.itau.carros.application.core.vo.CriteriosDeBusca;
import com.itau.carros.application.ports.in.GetCarroUseCasePort;
import com.itau.carros.application.ports.out.CarroRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetCarroUseCaseImpl implements GetCarroUseCasePort {

    private final CarroRepositoryPort carroRepositoryPort;

    public GetCarroUseCaseImpl(CarroRepositoryPort carroRepositoryPort) {
        this.carroRepositoryPort = carroRepositoryPort;
    }


    @Override
    public List<Carro> listar() {
        var carros = carroRepositoryPort.findAllByAtivoTrue();
        if (carros.isEmpty()){
            throw new ConsultaNulaException("Não foi encontrado nenhum carro cadastrado em nosso banco de dados.");
        }
        return carros;
    }

    @Override
    public Carro detalhar(Long id) {
        return carroRepositoryPort.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new ConsultaNulaException(String.format("Nenhum carro de id: %d encontrado em nosso banco de dados.",id)));
    }

    @Override
    public List<Carro> filtrar(CriteriosDeBusca criteriosDeBusca) {
        var carros = carroRepositoryPort.findAll(criteriosDeBusca);
        if (carros.isEmpty()){
            throw new ConsultaNulaException("Não foi encontrado nenhum carro com esse(s) parametro(s)");
        }
        return carros;
    }
}
