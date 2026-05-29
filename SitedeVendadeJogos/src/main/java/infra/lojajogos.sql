create database lojajogos;

use lojajogos;

create table usuarios(
	id int primary key auto_increment,
	nome varchar(250) not null,
	email varchar(200) not null,
	senha varchar(200) not null
);

create table jogos(
    id integer primary key auto_increment,
    nome varchar(200) NOT NULL,
    preco double NOT NULL
);

create table imagens(
id int primary key auto_increment,
nome_arquivo varchar(200),
id_jogo int,
dados_imagem varchar(200),
tipo_mime varchar(200)
);