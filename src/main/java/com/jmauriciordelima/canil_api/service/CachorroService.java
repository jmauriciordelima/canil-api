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

    public Cachorro salvar(Cachorro cachorro) {
        return repository.save(cachorro);
    }

    public List<Cachorro> listarTodos() {
        return repository.findAll();
    }

    public Optional<Cachorro> buscarPorId(UUID id) {
        return repository.findById(id);
    }

    public Cachorro atualizar(UUID id, Cachorro cachorroAtual) {
        return repository.findById(id)
                .map(cachorro -> {
                    cachorro.setNome(cachorroAtual.getNome());
                    cachorro.setRaca(cachorroAtual.getRaca());
                    cachorro.setIdade(cachorroAtual.getIdade());
                    return repository.save(cachorro);
                })
                .orElseThrow(() -> new RuntimeException("Cachorro não encontrado com o ID: " + id));
    }

    public void deletar(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Cachorro não encontrado com o ID: " + id);
        }

        repository.deleteById(id);

    }

}
