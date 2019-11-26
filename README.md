# Quanto custa o meu carro?
![AUR Language](https://img.shields.io/badge/Made%20with-Java-critical?logo=Java&style=flat-square)
![AUR Ask](https://img.shields.io/badge/Ask%20Me-Anything-orange?style=flat-square)

Este projeto foi desenvolvido na Disciplina de Des. de Software no IFRS.

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
| /lancamentos      | http://localhost:8080/lancamentos?{property}={search_string} | GET    | 200 Ok          | Apenas os resultados que a propriedade passada corresponda à pesquisa paginados |
| /lancamentos/{id} | http://localhost:8080/lancamentos/{id}                       | GET    | 200 Ok          | O lançamento chamado                                                            |
| /lancamentos/{id} | http://localhost:8080/lancamentos/{id}                       | PUT    | 200 Ok          | O recurso atualizado                                                            |
| /lancamentos/{id} | http://localhost:8080/lancamentos/{id}                       | DELETE | 204 No Content  | Nada                                                                            |
| /usuario          | http://localhost:8080/usuario                                | POST   | 201 Created     | Registra um novo usuário                                                        |

