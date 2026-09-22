package com.jmauriciordelima.canil_api.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public record ErroResponseDTO(
        LocalDateTime dataHora,
        int status,
        String erro,
        String caminho,
        String mensagem,
        Map<String, List<String>> errosCampos) {
}
