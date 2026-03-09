package br.com.alura.ForumHub.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity tratarErroGlobal(RuntimeException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}