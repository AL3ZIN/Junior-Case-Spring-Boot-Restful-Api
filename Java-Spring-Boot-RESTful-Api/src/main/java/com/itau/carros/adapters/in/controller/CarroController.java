package com.itau.carros.adapters.in.controller;

import com.itau.carros.adapters.in.dto.*;
import com.itau.carros.adapters.in.manager.CarroDataManager;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("api/carro")
@Tag(name = "Carro", description = "Endpoints for Managing Carro")
public class CarroController {

    private final CarroDataManager dataManager;

    public CarroController(CarroDataManager dataManager) {
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
