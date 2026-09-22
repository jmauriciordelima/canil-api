package com.jmauriciordelima.canil_api.exception.handler;

import com.jmauriciordelima.canil_api.dto.ErroResponseDTO;
import com.jmauriciordelima.canil_api.exception.CachorroNaoEncontradoException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Trata erros de validação (HTTP 400) com múltiplas mensagens por campo
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErroResponseDTO tratarExcecoesValidacao(
            MethodArgumentNotValidException excecao,
            HttpServletRequest requisicao) {
        Map<String, List<String>> errosCampos = new HashMap<>();

        excecao.getBindingResult().getAllErrors().forEach((erro) -> {
            String nomeCampo = ((FieldError) erro).getField();
            String mensagemErro = erro.getDefaultMessage();

            // Adiciona a mensagem na lista do campo existente ou cria uma nova lista
            errosCampos.computeIfAbsent(nomeCampo, k -> new ArrayList<>()).add(mensagemErro);
        });

        return new ErroResponseDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                requisicao.getRequestURI(),
                "Erro de validação nos dados enviados",
                errosCampos
        );
    }
}