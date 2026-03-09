package br.com.alura.ForumHub.domain.curso;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cursos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String categoria;

    public Curso(DadosCadastroCurso dados){
        this.nome = dados.nome();
        this.categoria = dados.categoria();
    }

    public void atualizar(DadosAtualizacaoCurso dados){
        if(dados.nome() != null){
            this.nome = dados.nome();
        }

        if(dados.categoria() != null){
            this.categoria = dados.categoria();
        }
    }
}