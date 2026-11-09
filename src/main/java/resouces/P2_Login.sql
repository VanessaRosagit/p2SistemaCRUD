
#CRIAÇÃO DO BANCO DE DADOS BD_LOGIN
create database bd_login;

#SELECIONANDO O BANCO DE DADOS
use bd_login;

#CRIANDO A TABELA DE LOGIN
create table usuario(
    usuario varchar(10) primary key,
    nome    varchar(30) default '',
    senha   char(32) default ''
);


insert into usuario (usuario, nome, senha)
values ('Vanessa', 'Vanessa Rosa', md5('123'));

#CONFERINDO
select * from bd_login.usuario;