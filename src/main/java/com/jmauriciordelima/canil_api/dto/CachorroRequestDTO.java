package com.jmauriciordelima.canil_api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CachorroRequestDTO(
        @NotBlank(message = "O campo [Nome] é obrigatório, não deve estar em branco")
        @Size(min = 3, max = 50, message = "O campo [Nome] deve ter entre 3 e 50 caracteres")
        String nome,

        @NotBlank(message = "O campo [Raça] é obrigatório, não deve estar em branco")
        @Size(min = 3, max = 50, message = "O campo [Raça] deve ter entre 3 e 50 caracteres")
        String raca,

        @Min(value = 0, message = "O campo [Idade] não pode ser menor que 0")
        int idade
) {}