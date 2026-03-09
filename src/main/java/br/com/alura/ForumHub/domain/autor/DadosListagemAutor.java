package br.com.alura.ForumHub.domain.autor;

public record DadosListagemAutor(

        Long id,
        String nome,
        String email

) {

    public DadosListagemAutor(Autor autor){
        this(
                autor.getId(),
                autor.getNome(),
                autor.getEmail()
        );
    }
}