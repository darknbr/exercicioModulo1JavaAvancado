# Exercício Módulo 1

### Exercício
Ultilizando a mesma estratégia que vimos nas aulas dese módulo,
crie uma nova API Rest ultilizando Spring Boot com os seguintes requerimentos:

### --> A API deve gerenciar um cadastro de alunos onde cada aluno possui as seguintes informações:
001-> Id
002-> Nome
003-> Idade

### --> A API deve possuir os seguintes endpoints
001-> Listar todos os alunos
001.1-> Podendo filtrar por nome(ultilizando contains)
001.2-> Podendo filtrar por idade
002-> Buscar aluno por id
003-> Cadastrar novo aluno
004-> Atualizar aluno existente
005-> Remover registro de aluno

### --> Módulo 2
refatoração
006->Crie uma classe de service para conter toda a lógica de,
adição e busca dos alunos em uma lista que estava na controller
007->Ultilize os métodos da classe service que você criou na claase controller
para manter o comportamento anterior
######Exceptions#########
008-> Criação de uma claase de exception customizada para representar um aluno
não existente
009-> Na classe service de alunos que você criou, na busca de aluno deve ser lançada
a exception que você criou quando o aluno não for encontrado tanto ao ser buscado
por nome quanto a ao ser buscado por ID
 010-> Faça um tratamento de exception na sua aplicação para retornar o status 404
com uma mensagem de aluno não encontrado sempre que essa exception for lançada
PODE SER ULTILIZADA QUALQUER ESTRATÉGIA DE EXCEPTION
