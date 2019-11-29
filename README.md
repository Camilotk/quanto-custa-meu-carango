# Quanto custa o meu carro?
![AUR Language](https://img.shields.io/badge/Made%20with-Java-critical?logo=Java&style=flat-square)
![AUR Ask](https://img.shields.io/badge/Ask%20Me-Anything-orange?style=flat-square)

Este projeto foi desenvolvido na Disciplina de Des. de Software no IFRS. Seu objetivo foi desenvolver uma API RESTful para o
Controle de Gastos de um veículo próprio utilizando Spring Boot. Para que isso fosse atingido buscou-se ao mínimo atingir
um nível de maturidade 2 à mesma, utilizando Métodos, Convenções, Códigos Http, Headers e Retornos de acordo com o que o padrão REST
propõe.

## Índice
1. [Como rodar o projeto](https://github.com/Camilotk/quanto-custa-meu-carango#como-rodar-o-projeto)
2. [Banco de Dados e Migrations](https://github.com/Camilotk/quanto-custa-meu-carango#banco-de-dados-e-migrations)
3. [Autenticação](http://github.com/Camilotk/quanto-custa-meu-carango#autentica%C3%A7%C3%A3o)
4. [Padronização de Métodos / Resources ao invés de Controllers](https://github.com/Camilotk/quanto-custa-meu-carango#padroniza%C3%A7%C3%A3o-de-m%C3%A9todos--resources-ao-inv%C3%A9s-de-controllers)
5. [Documentação das Rotas](https://github.com/Camilotk/quanto-custa-meu-carango#documenta%C3%A7%C3%A3o-das-rotas)
6. [Exemplos de Formato JSON](https://github.com/Camilotk/quanto-custa-meu-carango#exemplos-de-formato-json)

## Como rodar o projeto

Dentro do terminal bash rode
```
./mvnw spring-boot:repackage && ./mvnw spring-boot:run
```
ou use mvnw.cmd no Windows.

## Banco de Dados e Migrations

Para esse projeto foi utilizado o Banco de Dados H2, esta decisão foi tomada a partir de algumas vantagens que o mesmo possui para
o Spring Boot. A primeira é de que ele pode ser rodado in memory podendo ser integrado ao projeto, ou seja, assim que o projeto é iniciado
o Banco de Dados é inicializado junto, sem a necessidade de instalação ou configuração, a segunda, é a de que ele pode trabalhar tanto
*in memory* quanto de forma persistente através de arquivo, o que também permite que seja embutido junto ao executável do servidor e/ou aplicativo.

Para gerenciar o banco de dados basta acessar o recurso **/h2-console** (*localhost:8080/h2-console*) e entrar com as credenciais
**user** *root* e **password** *root*.

Para trabalhar com a criação de tabelas utilizou-se a [Biblioteca Flyway](https://flywaydb.org/) que gerencia a criação das tabelas e *seeding* dos dados em
migrations, uma vez em que a própria [documentação do Hibernate](https://docs.jboss.org/hibernate/orm/5.4/userguide/html_single/Hibernate_User_Guide.html#schema-generation) recomenda que utilize-se um sistema de migrações ao invés de usar o sistema de auto geração de tabelas.
Todas as migrations são automaticamente executadas quando o sistema é iniciado.

Para gerenciar as migrações basta ir em */src/main/resources/db/migrations* e checar como as tabelas, relações e inserts foram implementados.
Foi utilizada a convenção de nomeação do Flyway de iniciar com VXX\_\_ seguida do nome que segue o padrão do laravel (create/update/delete/insert)\_\<nome\_da\_tabela\>\_(table/property).
Ex: V01\_\_create\_carro\_table.sql para criar a tabela 'carro' ou V02\_\_update\_caro\_table.sql para alterações.

## Autenticação

O projeto utiliza o [Auth Basic do Spring Security](https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#servlet-hello-jc) para garantir a segurança das rotas
isso significa que para que qualquer requisição seja atendida ela deve conter um
header HTTP chamado 'Authorization' cujo o valor é a palavra 'Basic' seguida de um espaço
e o login e senha criptografados pelo algoritmo BCrypt.

Caso isso não seja feito irá retornar o código **401 Unauthorized**. Obviamente esses dados
devem trafegar em SSL para que sejam seguros, porém devido ao custo em tempo de implementar
OAuth2 foi decidido pelo Basic como forma de atender a demanda a tempo.

O login padrão é **ADMIN** e a senha **123**, novos usuários podem se registrar através
da rota /usuario com POST que não exige autenticação.

## Padronização de Métodos / Resources ao invés de Controllers

Quando estamos falando em APIs RESTful utilizamos o conceito de Recursos ao invés de Controladores,
Resource é um nome diferente para definir o Controller que 5 métodos (listar, visualizar, cadastrar, editar e excluir)
que normalmente são necessárias para fazer o manejo de informações dentro de um Sistema, essa prática foi adotada nesse projeto.

Como o Java \-ou o Spring Boot\- não possui um padrão de nomeação de métodos do Recurso, foi utilizado o
padrão definido no Framework Laravel, que funciona muito bem para manter a consistência da nomeação dos
métodos. O padrão seguido foi:

| Verbo  | Caminho       | Método  | Ação         |
|--------|---------------|---------|--------------|
| GET    | /recurso      | index   | Listar todos |
| GET    | /recurso/{id} | show    | Listar um    |
| POST   | /recurso      | store   | Salvar       |
| PUT    | /recurso/{id} | update  | Atualiza     |
| DELETE | /recurso/{id} | destroy | Deleta       |

## Documentação das Rotas
| Recurso           | URI                                                          | Método | Código Esperado | Retorno Esperado                                                                |
|-------------------|--------------------------------------------------------------|--------|-----------------|---------------------------------------------------------------------------------|
| /categorias       | http://localhost:8080/categorias                             | GET    | 200 Ok          | Listagem de todos os items do recurso                                           |
| /categorias       | http://localhost:8080/categorias                             | POST   | 201 Created     | A categoria criada                                                              |
| /categorias/{id}  | http://localhost:8080/categorias/{id}                        | GET    | 200 Ok          | A categoria chamada                                                             |
| /categorias/{id}  | http://localhost:8080/categorias/{id}                        | PUT    | 200 Ok          | A categoria atualizada                                                          |
| /categorias/{id}  | http://localhost:8080/categorias/{id}                        | DELETE | 204 No Content  | Nada                                                                            |
| /carros           | http://localhost:8080/carros                                 | POST   | 201 Created     | O Carro criado                                                                  |
| /carros           | http://localhost:8080/carros                                 | GET    | 200 Ok          | Listagens de todos os items do recurso                                          |
| /carros/{id}      | http://localhost:8080/carros/{id}                            | GET    | 200 Ok          | O carro chamado                                                                 |
| /carros/{id}      | http://localhost:8080/carros/{id}                            | PUT    | 200 Ok          | O carro modificado                                                              |
| /carros/{id}      | http://localhost:8080/carros/{id}                            | DELETE | 204 No Content  | Nada                                                                            |
| /lancamentos      | http://localhost:8080/lancamentos                            | POST   | 201 Created     | O lancamento criado com null nas propriedades de carro e categoria              |
| /lancamentos      | http://localhost:8080/lancamentos                            | GET    | 200 Ok          | Todos os lançamentos paginados                                                  |
| /lancamentos      | http://localhost:8080/lancamentos?{property}={searchi\_string} | GET    | 200 Ok          | Apenas os resultados que a propriedade passada corresponda à pesquisa paginados |
| /lancamentos/{id} | http://localhost:8080/lancamentos/{id}                       | GET    | 200 Ok          | O lançamento chamado                                                            |
| /lancamentos/{id} | http://localhost:8080/lancamentos/{id}                       | PUT    | 200 Ok          | O recurso atualizado                                                            |
| /lancamentos/{id} | http://localhost:8080/lancamentos/{id}                       | DELETE | 204 No Content  | Nada                                                                            |
| /usuario          | http://localhost:8080/usuario                                | POST   | 201 Created     | Registra um novo usuário                                                        |

## Exemplos de Formato JSON

### Carro
```Json
{
   "placa": "MDN0777",
   "ano": 2019,
   "marca": {
       "fabricante": "Industrias Wayne",
       "modelo": "Batmóvel"
   },
   "valor": 350000.75,
   "ativo": true
}
```

### Categoria
```Json
{
  "nome": "Danos"
}
```

### Lancamento
```Json
{
  "descricao": "Compra de rodas novas",
  "dataVencimento": "2019-11-03",
  "dataPagamento": "2019-11-03",
  "valor": 100.00,
  "observacao": "Pago na hora em crédito",
  "tipo": "DESPESA",
  "categoria": {
           "id": 2
   },
  "carro": {
           "id": 2
   }
}
```
### Usuário 
```Json
{
  "login": "Batman",
  "senha": "BruceWayne"
}
```
