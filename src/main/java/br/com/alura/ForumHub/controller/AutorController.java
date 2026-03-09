package br.com.alura.ForumHub.controller;

import br.com.alura.ForumHub.domain.autor.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroAutor dados){

        if(repository.existsByEmail(dados.email())){
            throw new RuntimeException("Email já cadastrado");
        }

        if(repository.existsByNome(dados.nome())){
            throw new RuntimeException("Autor já existe");
        }

        repository.save(new Autor(dados));
    }

    @GetMapping
    public List<DadosListagemAutor> listar(){

        return repository.findAll()
                .stream()
                .map(DadosListagemAutor::new)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var autor = repository.findById(id);

        if(autor.isPresent()){
            return ResponseEntity.ok(new DadosDetalhamentoAutor(autor.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoAutor dados){
        var autor = repository.getReferenceById(dados.id());
        autor.atualizar(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        repository.deleteById(id);
    }

}