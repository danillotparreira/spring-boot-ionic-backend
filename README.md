# cursomc
Projeto realizado através do curso da udemy com o professor Nelio Alves

Neste projeto é disponibilizado os seguintes recursos REST

## Recursos de Categorias 
ENDPOINT | METODO | DESCRIÇÃO | PARÂMETROS
---------|--------|-----------|-----------
/categorias|GET| Busca todas categorias existentes | ---
/categorias/page | GET | Busca as categorias com paginação, por padrão inicia na página 0, com 24 dados, ordenado por nome e ordenado de forma crescente | <b>page</b> - página atual iniciando com 0, padrão 0; <b>linesPerPage</b> - resultado da página minimo 1, padrão 24; <b>orderBy</b> - ordenação do resultado, padrão por nome; <b>direction</b> - Crescente ou decrescente (ASC ou DESC), padrão ASC.
/categorias/{id} | GET | Busca a categoria pelo id | ---
/categorias | POST | Insere uma nova categoria, necessário enviar JSON no corpo com os dados | ---
/categorias/{id} | PUT | Atualiza uma categoria já existente, necessário enviar JSON no corpo com os dados | ---
/categorias/{id} | DELETE | Deleta uma categoria já existente com base no ID | ---

## Recursos de Cliente 
ENDPOINT | METODO | DESCRIÇÃO | PARÂMETROS
---------|--------|-----------|-----------
/clientes | GET| |
/clientes/{id} | GET | |

## Recursos de Pedidos 
ENDPOINT | METODO | DESCRIÇÃO | PARÂMETROS
---------|--------|-----------|-----------
/pedidos/{id} | GET ||

## Diagrama UML do projeto.

![uml](https://user-images.githubusercontent.com/4616631/98959616-38422580-24e2-11eb-8fc5-e754c17de4f3.png)
