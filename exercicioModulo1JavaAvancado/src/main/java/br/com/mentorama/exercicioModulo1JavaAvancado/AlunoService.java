package br.com.mentorama.exercicioModulo1JavaAvancado;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//anotação para definir como uma classe de serviço
@Service
public class AlunoService {
    private final List<Aluno> alunos;

    public AlunoService(){
        this.alunos = new ArrayList<>();
        //adicionando alguns alunos para testes
        alunos.add(new Aluno(1, 20, "João"));
        alunos.add(new Aluno(2, 22, "Maria"));
        alunos.add(new Aluno(3, 21, "Marcio"));
        alunos.add(new Aluno(4, 22, "Marcio"));
        alunos.add(new Aluno(5, 20, "João"));
        alunos.add(new Aluno(6, 22, "Danillo"));
        alunos.add(new Aluno(7, 20, "Daniel"));
        alunos.add(new Aluno(8, 22, "Danillo"));
        alunos.add(new Aluno(9, 25, "Aline"));
        alunos.add(new Aluno(10, 22, "Elidiane"));
    }

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

    //o id deve ser exatamente igual ao definido em @PathVariable
    //002-> Buscar aluno por id

    public ResponseEntity<Aluno>  procurarPorId(@PathVariable("id") Integer id) {
        //verificar se o aluno existe pelo id informado e salvar em true / false
        boolean alunoExiste = id >= 0 && id <= alunos.size();

        //System.out.println(alunoExiste);

        try {
            if (alunoExiste == false) {
                throw new AlunoNaoExistenteException();
            }else{
                Aluno alunoEncontrado = alunos.stream()
                        .filter(comp -> comp.getId().equals(id))
                        .findFirst()
                        .orElse(null);
                return ResponseEntity.ok(alunoEncontrado);
            }
        }catch (AlunoNaoExistenteException e){
            throw new AlunoNaoExistenteException();
        }
    }
    public ResponseEntity<Aluno>  procurarPorNome(@PathVariable("nome") String nome) {
        //verificar se o aluno existe pelo nome informado e salvar em true / false
        Boolean alunoExiste;

        if(alunos.stream().anyMatch(m -> m.getNome().equals(nome))) {
            alunoExiste = true;
        } else {
            alunoExiste = false;
        }

        //System.out.println(alunoExiste);

        try {
            if (alunoExiste == true) {
                throw new AlunoNaoExistenteException();
            }else{
                Aluno alunoEncontrado = alunos.stream()
                        .filter(comp -> comp.getNome().equals(nome))
                        .findFirst()
                        .orElse(null);
                return ResponseEntity.ok(alunoEncontrado);
            }
        }catch (AlunoNaoExistenteException e){
            throw new AlunoNaoExistenteException();
        }
    }

    //003-> Cadastrar novo aluno
    public ResponseEntity<Integer> novo(@RequestBody final Aluno aluno){
        if(aluno.getId() == null){
            aluno.setId(alunos.size() + 1);
        }
        alunos.add(aluno);
        return new ResponseEntity<>(aluno.getId(), HttpStatus.CREATED);//aqui retonar o id e o status CREATED
    }

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

    //005-> Remover registro de aluno
    public ResponseEntity delete(@PathVariable("id")Integer id){
        alunos.removeIf(comp -> comp.getId().equals(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
