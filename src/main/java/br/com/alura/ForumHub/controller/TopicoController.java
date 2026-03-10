package br.com.alura.ForumHub.controller;

import br.com.alura.ForumHub.domain.curso.CursoRepository;
import br.com.alura.ForumHub.domain.topico.*;
import br.com.alura.ForumHub.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dados){

        var loginUsuario = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()
                .toString();

        var autor = usuarioRepository.findByLogin(loginUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

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

        return ResponseEntity.status(201).build();
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
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoTopico dados){

        var topico = repository.getReferenceById(dados.id());

        var loginUsuario = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()
                .toString();

        if(!topico.getAutor().getLogin().equals(loginUsuario)){
            throw new RuntimeException("Você não pode alterar esse tópico");
        }

        topico.atualizar(dados);

        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){

        var topico = repository.getReferenceById(id);

        var loginUsuario = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()
                .toString();

        if(!topico.getAutor().getLogin().equals(loginUsuario)){
            throw new RuntimeException("Você não pode apagar esse tópico");
        }

        repository.delete(topico);

        return ResponseEntity.ok().build();
    }

}