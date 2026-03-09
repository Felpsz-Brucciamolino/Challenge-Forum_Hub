package br.com.alura.ForumHub.controller;

import br.com.alura.ForumHub.domain.curso.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroCurso dados){

        if(repository.existsByNome(dados.nome())){
            throw new RuntimeException("Curso com esse nome já existe");
        }

        repository.save(new Curso(dados));
    }

    @GetMapping
    public List<DadosListagemCurso> listar(){
        return repository.findAll().stream()
                .map(DadosListagemCurso::new)
                .toList();
    }

    @GetMapping("/{id}")
    public DadosListagemCurso buscar(@PathVariable Long id){
        var curso = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        return new DadosListagemCurso(curso);
    }


    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoCurso dados){
        var curso = repository.getReferenceById(dados.id());
        curso.atualizar(dados);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        repository.deleteById(id);
    }
}