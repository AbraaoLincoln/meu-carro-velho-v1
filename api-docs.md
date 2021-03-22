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

Request:

`DELETE http://localhost:8080/api/anuncio/42`

Response:

...