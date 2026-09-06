package com.jmauriciordelima.canil_api.service;

import com.jmauriciordelima.canil_api.model.Cachorro;
import com.jmauriciordelima.canil_api.repository.CachorroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CachorroService {

    private final CachorroRepository repository;

    public CachorroService(CachorroRepository repository) {
        this.repository = repository;
    }

    public List<Cachorro> listarTodos() {
        return repository.findAll();
    }

    public Optional<Cachorro> buscarPorId(UUID id) {
        return repository.findById(id);
    }

    public Cachorro salvar(Cachorro cachorro) {
        return repository.save(cachorro);
    }

    public void deletar(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Cachorro não encontrado com o ID: " + id);
        }

        repository.deleteById(id);

    }

}
