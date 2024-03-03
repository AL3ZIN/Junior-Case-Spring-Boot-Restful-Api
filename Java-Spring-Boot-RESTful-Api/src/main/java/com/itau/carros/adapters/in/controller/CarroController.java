package com.itau.carros.adapters.in.controller;

import com.itau.carros.adapters.in.dto.carro.*;
import com.itau.carros.adapters.in.service.CarroService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/carro")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Carro", description = "Endpoints para gerenciamento de carros.")
public class CarroController {

    private final CarroService dataManager;

    public CarroController(CarroService dataManager) {
        this.dataManager = dataManager;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<EntityModel<CarroListagemResponseDto>> cadastrar(@RequestBody @Valid CarroRequestDto dto, UriComponentsBuilder uriBuilder){
        var dtoListagem = dataManager.cadastrar(dto);
        var uri = uriBuilder.path("api/carro")
                .buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(dtoListagem);
    }

    @GetMapping
    public ResponseEntity<List<CarroListagemAgrupadaResponseDto>> listar(){
       return ResponseEntity.ok(dataManager.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<EntityModel<CarroListagemResponseDto>>> detalhar(@PathVariable Long id){
            return ResponseEntity.ok(dataManager.detalhar(id));
    }

    @GetMapping("filtrar")
    public ResponseEntity<List<EntityModel<CarroListagemResponseDto>>> filtrar(@ModelAttribute CarroFiltroRequestDto dto){
       return ResponseEntity.ok(dataManager.filtrar(dto));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<EntityModel<CarroListagemResponseDto>> atualizarStatus(@RequestBody @Valid CarroUpdateStatusRequestDto dto){
        return ResponseEntity.ok(dataManager.atualizarStatus(dto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        dataManager.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
