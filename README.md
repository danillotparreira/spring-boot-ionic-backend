# cursomc
Projeto realizado através do curso da udemy com o professor Nelio Alves

Neste projeto é disponibilizado os seguintes recursos REST

* /categorias
* /categorias/page
  * poderá ter os seguintes parametros:
    * page - página atual iniciando com 0, padrão 0;
    * linesPerPage - resultado da página minimo 1, padrão 24;
    * orderBy - ordenação do resultado, padrão por nome;
    * direction - Crescente ou decrescente (ASC ou DESC), padrão ASC.
* /categorias/{id}

* /clientes
* /clientes/{id}

* /pedidos/{id}

## Diagrama UML do projeto.

![uml](https://user-images.githubusercontent.com/4616631/98959616-38422580-24e2-11eb-8fc5-e754c17de4f3.png)
