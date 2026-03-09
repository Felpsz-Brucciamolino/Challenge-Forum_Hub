package br.com.alura.ForumHub.domain.topico;

public record DadosDetalhamentoTopico(
        Long id,
        String titulo,
        String mensagem,
        Long autorId,
        Long cursoId
) {

    public DadosDetalhamentoTopico(Topico topico){
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getAutor().getId(),
                topico.getCurso().getId()
        );
    }

}