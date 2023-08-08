package br.com.mentorama.exercicioModulo1JavaAvancado;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
--> A API deve possuir os seguintes endpoints
001-> Listar todos os alunos
001.1-> Podendo filtrar por nome(ultilizando contains)
001.2-> Podendo filtrar por idade
002-> Buscar aluno por id
003-> Cadastrar novo aluno
004-> Atualizar aluno existente
005-> Remover registro de aluno
*/

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    private final List<Aluno> alunos;

    public AlunoController() {
        this.alunos = new ArrayList<>();
        alunos.add(new Aluno(1, 20, "João"));
        alunos.add(new Aluno(2, 22, "Maria"));
        alunos.add(new Aluno(3, 21, "Marcio"));
        alunos.add(new Aluno(4, 22, "Marcio"));
        alunos.add(new Aluno(5, 20, "João"));
        alunos.add(new Aluno(6, 22, "Danillo"));
        alunos.add(new Aluno(6, 20, "Daniel"));
        alunos.add(new Aluno(7, 22, "Danillo"));
        alunos.add(new Aluno(7, 25, "Aline"));
        alunos.add(new Aluno(7, 22, "Elidiane"));
    }

    @GetMapping
    public List<Aluno> findAll(@RequestParam(required = false) String aluno) {
        if (aluno != null) {
            return alunos.stream()
                    .filter(a -> a.getNome().contains(aluno) ||
                            String.valueOf(a.getId()).contains(aluno) ||
                            String.valueOf(a.getIdade()).contains(aluno))
                    .collect(Collectors.toList());
        }
        return alunos;
    }
}
