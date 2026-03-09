package br.com.alura.ForumHub.domain.autor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "autores",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email"),
                @UniqueConstraint(columnNames = "nome")
        })
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    public Autor(DadosCadastroAutor dados){
        this.nome = dados.nome();
        this.email = dados.email();
    }

    public void atualizar(DadosAtualizacaoAutor dados){
        if(dados.nome() != null){
            this.nome = dados.nome();
        }

        if(dados.email() != null){
            this.email = dados.email();
        }
    }


}