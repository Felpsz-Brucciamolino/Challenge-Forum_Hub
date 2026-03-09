package br.com.alura.ForumHub.domain.curso;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoCurso(
        @NotNull Long id,
        String nome,
        String categoria
) {}