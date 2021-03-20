# meucarrovelho
Projeto da Disciplina WEB2

# Executando o Projeto
Digite um dos comandos abaixo dentro da pasta do projeto:

No terminal do Linux:

`./gradlew bootRun`

No cmd do Windows:(não testado!)

`./gradlew.bat bootRun`

# Estorias de Usuario

## Vendedor

1. Como Vendedor quero anunciar as peças novas e usadas que estou vendendo.

2. Como Vendedor quero saber se meu anúncio foi visto.

3. Como Vendedor quero poder comparar o preço do meu anúncio com os do mesmo tipo.

## Comprador

1. Como comprador quero ver as peças disponíveis para o meu carro.

2. Como comprador quero saber se a peça ainda está disponível.

3. Como comprador quero saber qual o tipo da peça para saber se é original ou réplica.

4. Como comprador quero poder fazer uma oferta para poder negociar um preço menor ou incluir outras peças que tenho com parte do pagamento. 

# API EndPoints

## Peça

Rotas:

`GET    meucarrovelho.com.br/api/carro/MODELO-D0-CARRO/peças`

`GET    meucarrovelho.com.br/api/carro/MODELO-D0-CARRO/peçaTipo`

## Anuncios

Rotas:

`GET      meucarrovelho.com.br/api/anuncio/MODELO-D0-CARRO/peças`

`GET      meucarrovelho.com.br/api/anuncio/MODELO-D0-CARRO/peçasTipo`

`POST     meucarrovelho.com.br/api/anuncio`

`PUT      meucarrovelho.com.br/api/anuncio/anuncioId`

`PUT      meucarrovelho.com.br/api/anuncio/visto/anuncioId`

`PUT      meucarrovelho.com.br/api/anuncio/disponivel/anuncioId`

`DELETE   meucarrovelho.com.br/api/anuncio/anuncioId`