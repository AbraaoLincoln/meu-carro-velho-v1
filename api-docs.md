# Api Docs

## Anuncio

Resquest:

`GET http://localhost:8080/api/anuncio/carro/pecas`

Response:
```
[
  {
    "id": 1,
    "data": "2021-03-15",
    "preco": 150.0,
    "estado": "disponivel",
    "descricao": "peca nova zerada",
    "usuario": 1,
    "visualizacoes": 0,
    "pecas": {
      "carro": 1,
      "pecas": [
        {
          "id": 1,
          "tipo": "volante",
          "estado": "novo",
          "anuncio": 1,
          "carro": 1
        }
      ]
    },
    "imagens": [
      {
        "src": "/anuncio1/imgs",
        "anuncio": 1
      }
    ]
  },
  ...
]
``` 

Request:

`GET http://localhost:8080/api/anuncio/carro/tipoPeca`

Response:

```
[
  {
    "id": 1,
    "data": "2021-03-15",
    "preco": 150.0,
    "estado": "disponivel",
    "descricao": "peca nova zerada",
    "usuario": 1,
    "visualizacoes": 0,
    "disponivel": "2021-04-09",
    "pecas": {
      "carro": 1,
      "pecas": [
        {
          "id": 1,
          "tipo": "volante",
          "estado": "novo",
          "anuncio": 1,
          "carro": 1
        }
      ]
    },
    "imagens": [
      {
        "src": "/anuncio1/imgs",
        "anuncio": 1
      }
    ]
  },
  {
    "id": 4,
    "data": "2021-04-09",
    "preco": 100.0,
    "estado": "disponivel",
    "descricao": "teste do post anuncio",
    "usuario": 2,
    "visualizacoes": 0,
    "disponivel": "2021-04-09",
    "pecas": {
      "carro": 1,
      "pecas": [
        {
          "id": 4,
          "tipo": "volante",
          "estado": "novo",
          "anuncio": 4,
          "carro": 1
        },
        {
          "id": 5,
          "tipo": "banco",
          "estado": "usado",
          "anuncio": 4,
          "carro": 1
        }
      ]
    },
    "imagens": [
      {
        "src": "2/4/img1",
        "anuncio": 4
      },
      {
        "src": "2/4/img2",
        "anuncio": 4
      },
      {
        "src": "2/4/img3",
        "anuncio": 4
      }
    ]
  }
]
```

Request:

`POST http://localhost:8080/api/anuncio`

Body:

```
{
    "preco": "100",
    "descricao": "teste do post anuncio",
    "usuario": 2,
    "imagens": [{"src": "img1"}, {"src": "img2"}, {"src": "img3"}],
    "pecas":{
        "carro": "1",
        "pecas": [
            {"tipo": "volante", "estado": "novo"}, 
            {"tipo": "banco", "estado": "usado"}
        ]
    }
}
```

Response em caso de sucesso:

```
{
  "status": "Anuncio salvo com sucesso",
  "listOfErros": null,
  "anuncioId": 42
}
```

Response em caso de erro:

```
{
  "status": "Error",
  "listOfErros": [
    erros...
  ],
  "anuncioId": 0
}
```

Request:

`PUT http://localhost:8080/api/anuncio/42`

Body:
```
{
    "preco": "100",
    "data": "2021-03-21",
    "descricao": "teste do post anuncio",
    "estado": "vendido",
    "usuario": 2,
    "imagens": [{"src": "img1"}, {"src": "img2"}, {"src": "img3"}],
    "pecas":{
        "carro": "1",
        "pecas": [
            {"tipo": "volante", "estado": "novo"}, 
            {"tipo": "banco", "estado": "usado"}
        ]
    }
}
```

Response:

```
{
  "id": 42,
  "data": "2021-03-21",
  "preco": 100.0,
  "estado": "vendido",
  "descricao": "teste do post anuncio",
  "usuario": 2,
  "visualizacoes": 0,
  "pecas": null,
  "imagens": null
}
```

Resquest:

`PUT http://localhost:8080/api/anuncio/visto/anuncioId`

Response:

Em caso de sucesso:
`visualizao do anuncio atualizada com sucesso`

Em caso do anuncio não existir
`anuncio não existe`

Request:
`PUT http://localhost:8080/api/anuncio/disponivel/anuncioId`

Response:

Em caso de sucesso:
`Anuncio Atualizado`

Em caso do anuncio não existir
`Anuncio não existe`

Request:

`DELETE http://localhost:8080/api/anuncio/42`

Response:

## Pecas

Request:

`GET http://localhost:8080/api/carro`

Response:

```
[
  {
    "id": 1,
    "tipo": "volante",
    "estado": "novo",
    "anuncio": 1,
    "carro": 1
  },
  ...
]
```

Request:

`GET http://localhost:8080/api/carro/tipoPeca`

Response:
```
[
  {
    "id": 1,
    "tipo": "volante",
    "estado": "novo",
    "anuncio": 1,
    "carro": 1
  },
  {
    "id": 4,
    "tipo": "volante",
    "estado": "novo",
    "anuncio": 4,
    "carro": 1
  }
]
```