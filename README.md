# Quanto custa o meu carro?

Este projeto foi desenvolvido na Disciplina de Des. de Software no IFRS.

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

