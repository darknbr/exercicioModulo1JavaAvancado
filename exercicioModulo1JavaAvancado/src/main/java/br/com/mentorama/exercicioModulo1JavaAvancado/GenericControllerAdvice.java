package br.com.mentorama.exercicioModulo1JavaAvancado;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GenericControllerAdvice {

    @ExceptionHandler({AlunoNaoExistenteException.class})
    public ResponseEntity<String> handle(final AlunoNaoExistenteException e){
        return new ResponseEntity<>("Aluno não encontrado", HttpStatus.NOT_FOUND);
    }

}
