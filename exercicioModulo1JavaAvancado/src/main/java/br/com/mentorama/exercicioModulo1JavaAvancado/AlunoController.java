package br.com.mentorama.exercicioModulo1JavaAvancado;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


//--> A API deve possuir os seguintes endpoints
//001-> Listar todos os alunos
//001.1-> Podendo filtrar por nome(ultilizando contains)
//001.2-> Podendo filtrar por idade
//002-> Buscar aluno por id
//003-> Cadastrar novo aluno
//004-> Atualizar aluno existente
//005-> Remover registro de aluno


@RestController
@RequestMapping("/aluno")
public class AlunoController {

    private final List<Aluno> alunos;

    public AlunoController() {
        this.alunos = new ArrayList<>();
        //adicionando alguns alunos para testes
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
    public List<Aluno> buscaNomeOuIdade(@RequestParam(required = false) String aluno) {
        if (aluno != null) {
            return alunos.stream()
                    //001.1-> Podendo filtrar por nome(ultilizando contains)
                    .filter(comp -> comp.getNome().contains(aluno) ||
                            //001.2-> Podendo filtrar por idade
                            String.valueOf(comp.getIdade()).contains(aluno))
                    //001-> Listar todos os alunos caso tenha procurado por algum dos parametros acima
                    .collect(Collectors.toList());
        }
        //001-> Listar todos os alunos caso nenhum parametro tenha sido enviado
        return alunos;
    }

    @GetMapping(value = "/{id}") //o id deve ser exatamente igual ao definido em @PathVariable
    //002-> Buscar aluno por id
    public Aluno procurarPorId(@PathVariable("id") Integer id){
        return this.alunos.stream()
                .filter(comp -> comp.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    //003-> Cadastrar novo aluno
    public ResponseEntity<Integer> novo(@RequestBody final Aluno aluno){
        if(aluno.getId() == null){
            aluno.setId(alunos.size() + 1);
        }
        alunos.add(aluno);
        return new ResponseEntity<>(aluno.getId(), HttpStatus.CREATED);//aqui retonar o id e o status CREATED
    }

    @PutMapping
    //004-> Atualizar aluno existente
    public ResponseEntity atualizar(@RequestBody final Aluno aluno){
        alunos.stream()
                .filter(comp -> comp.getId().equals(aluno.getId()))
                .forEach(comp -> {
                    comp.setIdade(aluno.getIdade());
                    comp.setNome(aluno.getNome());
                });
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    //005-> Remover registro de aluno
    public ResponseEntity delete(@PathVariable("id")Integer id){
        alunos.removeIf(comp -> comp.getId().equals(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
