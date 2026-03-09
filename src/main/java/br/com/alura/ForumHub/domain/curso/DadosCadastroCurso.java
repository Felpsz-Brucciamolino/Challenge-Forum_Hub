package br.com.alura.ForumHub.domain.curso;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroCurso(
        @NotBlank String nome,
        @NotBlank String categoria
) {}