package com.jmauriciordelima.canil_api.controller;

import com.jmauriciordelima.canil_api.dto.CachorroRequestDTO;
import com.jmauriciordelima.canil_api.dto.CachorroResponseDTO;
import com.jmauriciordelima.canil_api.model.Cachorro;
import com.jmauriciordelima.canil_api.service.CachorroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cachorros")
public class CachorroController {

    private final CachorroService service;

    public CachorroController(CachorroService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CachorroResponseDTO> criar(@RequestBody CachorroRequestDTO dto) {
        CachorroResponseDTO salvarCachorro = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvarCachorro);
    }

    @GetMapping
    public ResponseEntity<List<CachorroResponseDTO>> listarTodos(){
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CachorroResponseDTO> buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id)
        .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CachorroResponseDTO> atualizar(@PathVariable UUID id, @RequestBody CachorroRequestDTO requestDTO) {
        try {
            CachorroResponseDTO cachorroAtualizado = service.atualizar(id, requestDTO);
            return ResponseEntity.ok(cachorroAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable UUID id) {
        try {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}