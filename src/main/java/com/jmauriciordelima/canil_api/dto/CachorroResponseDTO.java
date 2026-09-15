package com.jmauriciordelima.canil_api.dto;

import java.util.UUID;

public record CachorroResponseDTO(UUID id, String nome, String raca, int idade) {}
