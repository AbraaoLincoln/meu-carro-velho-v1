create database meuCarroVelho;
use meuCarroVelho;

create table usuario (
	id int auto_increment,
    nome varchar(45),
    senha varchar(255),
    email varchar(255),
    
    primary key(id)
);

create table anuncio(
	id int auto_increment,
    data date,
    preco decimal(7,2),
    estado varchar(10),
    descricao varchar(255),
    usuario int,
    visualizacoes int,
    
    primary key(id),
    
    foreign key(usuario)
		references usuario(id)
	on update cascade
    on delete cascade
);

create table imagens(
	src varchar(255),
    anuncio int,
    
    primary key(src),
    
    foreign key(anuncio)
		references anuncio(id)
	on update cascade
    on delete cascade
);

create table conta_ofertas(
	descricao varchar(255),
    usuario int,
    anuncio int,
    
    primary key(usuario, anuncio),
    
    foreign key(usuario)
		references usuario(id)
	on update cascade
    on delete cascade,
    
    foreign key(anuncio)
		references anuncio(id)
	on update cascade
    on delete cascade
);

create table carro(
	id int auto_increment,
    modelo varchar(99),
    
    primary key(id)
);

create table peca(
	id int auto_increment,
    tipo varchar(255),
    estado varchar(5),
    anuncio int,
    carro int,
    
    primary key(id),
    
    foreign key(anuncio)
		references anuncio(id)
	on update cascade
    on delete cascade,
    
    foreign key(carro)
		references carro(id)
	on update cascade
    on delete cascade
);