package br.com.mentorama.exercicioModulo1JavaAvancado;

public class Aluno {
    private Integer id, idade;
    private String nome;

    public Aluno(Integer id, Integer idade, String nome) {
        this.id = id;
        this.idade = idade;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
