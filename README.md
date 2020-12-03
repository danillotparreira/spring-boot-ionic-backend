# cursomc
Projeto realizado através do curso da udemy com o professor Nelio Alves

Neste projeto é disponibilizado os seguintes recursos REST

## Recursos de Categorias 
ENDPOINT | MÉTODO | DESCRIÇÃO | PARÂMETROS
---------|--------|-----------|-----------
/categorias|GET| Busca todas categorias existentes | ---
/categorias/page | GET | Busca as categorias com paginação, por padrão inicia na página 0, com 24 dados, ordenado por nome e ordenado de forma crescente | <b>page</b> - página atual iniciando com 0, padrão 0; <b>linesPerPage</b> - resultado da página minimo 1, padrão 24; <b>orderBy</b> - ordenação do resultado, padrão por nome; <b>direction</b> - Crescente ou decrescente (ASC ou DESC), padrão ASC.
/categorias/{id} | GET | Busca a categoria pelo id | ---
/categorias | POST | Insere uma nova categoria, necessário enviar JSON no corpo com os dados | ---
/categorias/{id} | PUT | Atualiza uma categoria já existente, necessário enviar JSON no corpo com os dados | ---
/categorias/{id} | DELETE | Remove uma categoria já existente com base no ID | ---

### Exemplo de busca categoria por página completo
URI /categoria/page?page=0&linesPerPage=4&orderBy=id&direction=DESC
Método GET

## Recursos de Cliente 
ENDPOINT | MÉTODO | DESCRIÇÃO | PARÂMETROS
---------|--------|-----------|-----------
/clientes | GET| Busca todos clientes | ---
/clientes/{id} | GET | Busca um cliente pelo id | ---
/clientes | POST | Insere um novo cliente, necessário enviar JSON no corpo com os dados | ---
/clientes/{id} | PUT | Atualiza um cliente já existente, necessário enviar JSON no corpo com dados | ---
/clientes/{id} | DELETE | Remove um cliente que não tenha pedidos vinculados. | ---

### Exemplo de adicionar novo cliente
URI /clientes
MÉTODO POST
```
{
    "nome": "João da Silva",
    "email" : "joao@gmail.com",
    "cpfOuCnpj" : "76026925007",
    "tipo": 1,
    "telefones" : ["996611122", "32364455"],
    "logradouro" : "Praça da Sé",
    "numero" : "345",
    "complemento" : "lado ímpar",
    "cep" : "01001000",
    "bairro": "Sé",
    "cidadeId" : 2
}
```

URI /clientes/1
MÉTODO PUT
```
{
    "nome": "Maria Silva",
    "email": "maria@gmail.com"
}
```

## Recursos de Pedidos 
ENDPOINT | MÉTODO | DESCRIÇÃO | PARÂMETROS
---------|--------|-----------|-----------
/pedidos/{id} | GET | Busca o pedido pelo id | ---
/pedidos | POST | Adiciona um novo pedido

### Exemplo de adicionar novo pedido
URI /pedidos
Method POST
Body
```
{
    "cliente":{"id":1},
    "enderecoDeEntrega" :{"id":1},
    "pagamento": {
        "numeroDeParcelas": 10,
        "@type": "pagamentoComCartao"
    },
    "itens" :[
        {
            "quantidade":3,
            "produto":{"id":3}
        },
        {
            "quantidade":2,
            "produto":{"id":1}
        }
    ]
}
```
<b>@Type</b> pode ser <b>pagamentoComCartao</b> ou <b>pagamentoComBoleto</b>

## Recursos de Produtos 
ENDPOINT | MÉTODO | DESCRIÇÃO | PARÂMETROS
---------|--------|-----------|-----------
/produto/{id} | GET | Busca um produto pelo id | ---
/produto | GET | Busca os produtos com paginação, por padrão inicia na página 0, com 24 dados, ordenado por nome e ordenado de forma crescente | <b>page</b> - página atual iniciando com 0, padrão 0; <b>linesPerPage</b> - resultado da página minimo 1, padrão 24; <b>orderBy</b> - ordenação do resultado, padrão por nome; <b>direction</b> - Crescente ou decrescente (ASC ou DESC), padrão ASC, <b>nome</b> parte do nome do produto ou completo, <b>categorias</b> id das categorias


### Exemplo de busca produtos por página completo
/categoria/page?page=0&linesPerPage=4&orderBy=id&direction=DESC&nome=t&categorias=1,4


## Diagrama UML do projeto.

![uml](https://user-images.githubusercontent.com/4616631/98959616-38422580-24e2-11eb-8fc5-e754c17de4f3.png)
