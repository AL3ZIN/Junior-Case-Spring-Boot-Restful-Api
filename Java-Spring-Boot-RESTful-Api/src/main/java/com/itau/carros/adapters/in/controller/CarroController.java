package com.itau.carros.adapters.in.controller;

import com.itau.carros.adapters.in.dto.*;
import com.itau.carros.adapters.in.manager.CarroDataManager;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
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
    public ResponseEntity<CarroListagemDto> cadastrar(@RequestBody @Valid CarroDto dto, UriComponentsBuilder uriBuilder){
        var dtoListagem = dataManager.cadastrar(dto);
        var uri = uriBuilder.path("api/carro")
                .buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(dtoListagem);
    }

    @GetMapping
    public ResponseEntity<List<CarroListagemAgrupadaDto>> listar(){
       return ResponseEntity.ok(dataManager.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<EntityModel<CarroListagemDto>>> detalhar(@PathVariable Long id){
            return ResponseEntity.ok(dataManager.detalhar(id));
    }

    @GetMapping("filtrar")
    public ResponseEntity<List<CarroListagemDto>> filtrar(@ModelAttribute CarroFiltroDto dto){
       return ResponseEntity.ok(dataManager.filtrar(dto));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<CarroListagemDto> atualizarStatus(@RequestBody @Valid CarroUpdateStatusDto dto){
        return ResponseEntity.ok(dataManager.atualizarStatus(dto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        dataManager.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
