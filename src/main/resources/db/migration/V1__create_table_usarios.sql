create table usuarios(
    id serial primary key unique not null,
    login varchar(11) unique not null ,
    senha varchar not null,
    acesso varchar not null
);