package com.jmauriciordelima.canil_api.service;

import com.jmauriciordelima.canil_api.dto.CachorroRequestDTO;
import com.jmauriciordelima.canil_api.dto.CachorroResponseDTO;
import com.jmauriciordelima.canil_api.model.Cachorro;
import com.jmauriciordelima.canil_api.repository.CachorroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CachorroService {

    private final CachorroRepository repository;

    public CachorroService(CachorroRepository repository) {
        this.repository = repository;
    }

    public CachorroResponseDTO salvar(CachorroRequestDTO dto) {
        Cachorro criarNovoCachorro = new Cachorro(dto.nome(), dto.raca(), dto.idade());
        Cachorro cachorroSalvo = repository.save(criarNovoCachorro);
        CachorroResponseDTO responseDTO = new CachorroResponseDTO(
                cachorroSalvo.getId(),
                cachorroSalvo.getNome(),
                cachorroSalvo.getRaca(),
                cachorroSalvo.getIdade());
        return responseDTO;
    }

    public List<CachorroResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(cachorro -> new CachorroResponseDTO(
                        cachorro.getId(),
                        cachorro.getNome(),
                        cachorro.getRaca(),
                        cachorro.getIdade()
                ))
                .collect(Collectors.toList());
    }

    public Optional<CachorroResponseDTO> buscarPorId(UUID id) {
        return repository.findById(id)
                .map(cachorro ->
                        new CachorroResponseDTO(
                                cachorro.getId(),
                                cachorro.getNome(),
                                cachorro.getRaca(),
                                cachorro.getIdade()

                        ));
    }

    public CachorroResponseDTO atualizar(UUID id, CachorroRequestDTO requestDTO) {
        return repository.findById(id)
                .map(cachorro -> {
                    cachorro.setNome(requestDTO.nome());
                    cachorro.setRaca(requestDTO.raca());
                    cachorro.setIdade(requestDTO.idade());
                    Cachorro cachorroSalvo = repository.save(cachorro);
                    return new CachorroResponseDTO(
                            cachorroSalvo.getId(),
                            cachorroSalvo.getNome(),
                            cachorroSalvo.getRaca(),
                            cachorroSalvo.getIdade()
                    );
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
