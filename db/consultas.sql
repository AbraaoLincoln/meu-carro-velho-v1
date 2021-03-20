select * from usuario;

select * from anuncio;

select * from imagens;

select * from carro;

select * from peca;

select * from conta_ofertas;

delete from anuncio where id not in (1, 2, 3);

select a.id, a.data, a.preco, a.estado, a.descricao, a.usuario, a.visualizacoes, p.tipo, p.estado, p.carro
from anuncio as a, peca as p 
where a.id = p.anuncio;

select * from anuncio_completo;

select * from peca where carro = 2;