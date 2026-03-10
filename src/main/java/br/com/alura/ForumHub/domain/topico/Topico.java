package br.com.alura.ForumHub.domain.topico;

import br.com.alura.ForumHub.domain.autor.Autor;
import br.com.alura.ForumHub.domain.curso.Curso;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensagem;

    @Enumerated(EnumType.STRING)
    private StatusTopico status;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public void atualizar(DadosAtualizacaoTopico dados){

        if(dados.titulo() != null){
            this.titulo = dados.titulo();
        }

        if(dados.mensagem() != null){
            this.mensagem = dados.mensagem();
        }

        if(dados.status() != null){
            this.status = dados.status();
        }

    }
}