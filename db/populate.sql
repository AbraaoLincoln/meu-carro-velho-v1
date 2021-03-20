use meuCarroVelho;

insert into usuario (nome, senha, email) values ('Maya Daniela Márcia Moura', '123', 'mayadanielamarciamoura_@rmsolutions.inf.br'),
('Ayla Carla Daniela Sales', '321', 'aylacarladanielasales@wsiconsultores.com.br');

insert into anuncio (data, preco, estado, descricao, usuario, visualizacoes) values ('2021-03-15', 150,'disponivel', 'peca nova zerada', 1, 0),
('2021-03-10', 200, 'vendido', 'so vendo', 1, 0),
('2021-03-20', 50, 'disponivel', 'tem um pequeno defeito', 2, 0);

insert into imagens values ('/anuncio1/imgs', 1), ('/anuncio2/imgs', 2), ('/anuncio3/imgs', 3);

insert into carro (modelo) values 
('Chevette'),
('Fiat 147'),
('Maverick'),
('Gol g1'),
('Fusca'),
('Kombi'),
('Belina'),
('Passat'),
('Opala'),
('Kadett'),
('escort');

insert into peca (tipo , estado , anuncio, carro) values 
('volante', 'novo', 1, 1),
('freio de mao', 'usado', 2, 2), 
('para choque', 'novo', 3, 3);

insert into conta_ofertas values ('troca por um par de farois', 1, 3), ('faz por 50 a menos', 2, 2);