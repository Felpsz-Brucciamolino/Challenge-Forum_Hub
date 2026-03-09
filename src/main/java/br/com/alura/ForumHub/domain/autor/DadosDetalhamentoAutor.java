package br.com.alura.ForumHub.domain.autor;

public record DadosDetalhamentoAutor(
        Long id,
        String nome,
        String email
) {

    public DadosDetalhamentoAutor(Autor autor){
        this(
                autor.getId(),
                autor.getNome(),
                autor.getEmail()
        );
    }

}