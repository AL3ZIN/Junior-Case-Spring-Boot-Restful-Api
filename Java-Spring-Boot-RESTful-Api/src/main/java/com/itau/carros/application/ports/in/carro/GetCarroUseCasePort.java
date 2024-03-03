package com.itau.carros.application.ports.in.carro;

import com.itau.carros.application.core.model.Carro;
import com.itau.carros.application.core.vo.CriteriosDeBusca;

import java.util.List;

public interface GetCarroUseCasePort {
    List<Carro> listar();
    Carro detalhar(Long id);
    List<Carro> filtrar(CriteriosDeBusca criteriosDeBusca);
}
