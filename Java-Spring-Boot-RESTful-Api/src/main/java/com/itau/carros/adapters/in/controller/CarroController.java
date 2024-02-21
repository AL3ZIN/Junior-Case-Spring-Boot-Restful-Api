package com.itau.carros.adapters.in.controller;

import com.itau.carros.adapters.in.dto.CarroDto;
import com.itau.carros.adapters.in.dto.CarroFiltroDto;
import com.itau.carros.adapters.in.dto.CarroListagemDto;
import com.itau.carros.adapters.in.dto.CarroUpdateStatusDto;
import com.itau.carros.adapters.in.manager.CarroDataManager;
import com.itau.carros.application.core.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/carro")
public class CarroController {

    private final CarroDataManager dataManager;

    public CarroController(CarroDataManager dataManager) {
        this.dataManager = dataManager;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CarroDto> cadastrar(@RequestBody @Valid CarroDto dto, UriComponentsBuilder uriBuilder){
        var uri = uriBuilder.path("api/carro/{id}")
                .buildAndExpand(dataManager.cadastrar(dto))
                .toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<CarroListagemDto>> listar(){
       return ResponseEntity.ok(dataManager.listar());
    }

    @GetMapping("{id}")
    public ResponseEntity<CarroDto> detalhar(@PathVariable Long id){
            return ResponseEntity.ok(dataManager.detalhar(id));
    }

    @GetMapping("filtrar")
    public ResponseEntity<List<CarroDto>> filtrar(@ModelAttribute CarroFiltroDto dto){
       return ResponseEntity.ok(dataManager.filtrar(dto));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<CarroDto> atualizarStatus(@RequestBody @Valid CarroUpdateStatusDto dto){
        return ResponseEntity.ok(dataManager.atualizarStatus(dto));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        dataManager.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
