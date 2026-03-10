package br.com.alura.ForumHub.controller;

import br.com.alura.ForumHub.domain.autor.AutorRepository;
import br.com.alura.ForumHub.domain.curso.CursoRepository;
import br.com.alura.ForumHub.domain.topico.*;
import br.com.alura.ForumHub.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @Autowired
    private UsuarioRepository usuarioRepository;


    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroTopico dados){

        if(repository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())){
            throw new RuntimeException("Já existe esse tópico com essa mensagem");
        }

        var autor = autorRepository.getReferenceById(dados.autorId());
        var curso = cursoRepository.getReferenceById(dados.cursoId());

        Topico topico = new Topico(
                null,
                dados.titulo(),
                dados.mensagem(),
                StatusTopico.NAO_RESOLVIDO,
                LocalDateTime.now(),
                autor,
                curso
        );

        repository.save(topico);
    }
    @GetMapping
    public Page<DadosListagemTopico> listar(Pageable paginacao){
        return repository.findAll(paginacao)
                .map(DadosListagemTopico::new);
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