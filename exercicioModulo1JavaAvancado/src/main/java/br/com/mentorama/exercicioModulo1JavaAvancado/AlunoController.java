package br.com.mentorama.exercicioModulo1JavaAvancado;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Módulo I
//--> A API deve possuir os seguintes endpoints
//001-> Listar todos os alunos
//001.1-> Podendo filtrar por nome(ultilizando contains)
//001.2-> Podendo filtrar por idade
//002-> Buscar aluno por id
//003-> Cadastrar novo aluno
//004-> Atualizar aluno existente
//005-> Remover registro de aluno

//Modulo II --> refatoração
// 006->Crie uma classe de service para conter toda a lógica de,
// adição e busca dos alunos em uma lista que estava na controller
//--> AlunoService.java -->

// 007->Ultilize os métodos da classe service que você criou na claase controller
// para manter o comportamento anterior --> OK

// ######Exceptions#########
// 008-> Criação de uma classe de exception customizada para representar um aluno não existente
//-->AlunoNaoExistenteException.Java

// 009-> Na classe service de alunos que você criou, na busca de aluno deve ser lançada
// a exception que você criou quando o aluno não for encontrado tanto ao ser buscado
// por nome quanto a ao ser buscado por ID
//GetMapping --> aluno/id={id} ### aluno/nome={nome}

// 010-> Faça um tratamento de exception na sua aplicação para retornar o status 404
// com uma mensagem de aluno não encontrado sempre que essa exception for lançada
// ##Feito na Classe -->> GenericContollerAdvice.java

@RestController
@RequestMapping("/aluno")
public class AlunoController{

    private final AlunoService alunoService; // Instância de AlunoService

    // Injeção de dependência do AlunoService no construtor
    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    //get body para retorno o corpo como resposta assim que entendi a documentacao
     @GetMapping(value = "/id={id}")
     public Aluno procurarPorId(@PathVariable("id") Integer id) {
        return alunoService.procurarPorId(id).getBody();
    }

    @GetMapping(value = "/nome={nome}")
    public Aluno procurarPorNome(@PathVariable("nome") String nome) {
        return alunoService.procurarPorNome(nome).getBody();
    }

    @GetMapping
    public List<Aluno> buscaNomeOuIdade(@RequestParam(required = false) String aluno) {
        return (List<Aluno>) alunoService.buscaNomeOuIdade(aluno);
    }

    @PostMapping
    public ResponseEntity<Integer> novo(@RequestBody final Aluno aluno) {
        return alunoService.novo(aluno);
    }

    @PutMapping
    public ResponseEntity atualizar(@RequestBody final Aluno aluno) {
        return alunoService.atualizar(aluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        return alunoService.delete(id);
    }

}
