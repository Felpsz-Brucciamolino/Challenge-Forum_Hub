package br.com.alura.ForumHub.domain.curso;

public record DadosListagemCurso(
        Long id,
        String nome,
        String categoria
) {

    public DadosListagemCurso(Curso curso){
        this(
                curso.getId(),
                curso.getNome(),
                curso.getCategoria()
        );
    }

}