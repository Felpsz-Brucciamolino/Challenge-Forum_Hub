package br.com.alura.ForumHub.domain.topico;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoTopico(

        @NotNull
        Long id,

        String titulo,
        String mensagem,
        StatusTopico status

) {}