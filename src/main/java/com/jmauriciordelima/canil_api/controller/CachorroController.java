package com.jmauriciordelima.canil_api.controller;

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

    @GetMapping
    public ResponseEntity<List<Cachorro>> listarTodos(){
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cachorro> buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id)
        .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cachorro> criar(@RequestBody Cachorro cachorro) {
        Cachorro salvarCachorro = service.salvar(cachorro);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvarCachorro);
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