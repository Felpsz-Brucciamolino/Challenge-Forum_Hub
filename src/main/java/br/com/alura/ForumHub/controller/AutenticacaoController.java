package br.com.alura.ForumHub.controller;

import br.com.alura.ForumHub.domain.usuario.DadosLogin;
import br.com.alura.ForumHub.domain.usuario.DadosToken;
import br.com.alura.ForumHub.domain.usuario.UsuarioRepository;
import br.com.alura.ForumHub.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public DadosToken login(@RequestBody DadosLogin dados){

        var usuario = repository.findByLogin(dados.login())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!!"));

        if(!usuario.getSenha().equals(dados.senha())){
            throw new RuntimeException("Senha inválida!");
        }

        var token = tokenService.gerarToken(usuario.getLogin());

        return new DadosToken(token);
    }
}