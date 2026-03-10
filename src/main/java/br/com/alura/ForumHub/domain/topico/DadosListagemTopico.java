package br.com.alura.ForumHub.domain.topico;

import java.time.LocalDateTime;

public record DadosListagemTopico(

        Long id,
        String titulo,
        String mensagem,
        String autor,
        String curso,
        StatusTopico status,
        LocalDateTime dataCriacao
) {

    public DadosListagemTopico(Topico topico){
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getAutor().getNome(),
                topico.getCurso().getNome(),
                topico.getStatus(),
                topico.getDataCriacao()
                );
    }
}