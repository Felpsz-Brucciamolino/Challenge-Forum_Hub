package br.com.alura.ForumHub.controller;

import br.com.alura.ForumHub.domain.autor.AutorRepository;
import br.com.alura.ForumHub.domain.curso.CursoRepository;
import br.com.alura.ForumHub.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroTopico dados){

        var autor = autorRepository.getReferenceById(dados.autorId());
        var curso = cursoRepository.getReferenceById(dados.cursoId());

        Topico topico = new Topico(
                null,
                dados.titulo(),
                dados.mensagem(),
                autor,
                curso
        );

        repository.save(topico);
    }
    @GetMapping
    public List<DadosListagemTopico> listar(){
        return repository.findAll()
                .stream()
                .map(DadosListagemTopico::new)
                .toList();
    }

    @GetMapping("/{id}")
    public DadosDetalhamentoTopico detalhar(@PathVariable Long id){
        var topico = repository.getReferenceById(id);
        return new DadosDetalhamentoTopico(topico);
    }


    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoTopico dados){
        var topico = repository.getReferenceById(dados.id());
        topico.atualizar(dados);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        repository.deleteById(id);
    }

}