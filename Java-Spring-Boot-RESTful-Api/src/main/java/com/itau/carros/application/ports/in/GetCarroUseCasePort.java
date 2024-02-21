package com.itau.carros.application.ports.in;

import com.itau.carros.application.core.model.Carro;
import com.itau.carros.application.core.vo.CriteriosDeBusca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface GetCarroUseCasePort {
    List<Carro> listar();
    Carro detalhar(Long id);
    List<Carro> filtrar(CriteriosDeBusca criteriosDeBusca);
}
