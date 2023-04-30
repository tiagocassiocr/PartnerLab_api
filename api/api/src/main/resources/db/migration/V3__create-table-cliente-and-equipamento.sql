create table cliente(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    cnpj varchar(100) not null,
    logradouro varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
    complemento varchar(100),
    numero varchar(20),
    uf char(2) not null,
    cidade varchar(100) not null,

    primary key(id)

);

create table equipamento(

    id bigint not null auto_increment,
    modelo varchar(100) not null,
    fabricante varchar(100) not null,
    numerodeserie varchar(100) not null,

    primary key(id)

);