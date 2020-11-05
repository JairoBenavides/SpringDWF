drop database if exists pruebaSpring;
create database pruebaSpring;
use pruebaSpring;

create table departamento(
	id int primary key auto_increment,
    nombre varchar(255),
    cant_municipios int
);

insert into departamento(nombre, cant_municipios)
values
('Ahuachapán', 12),
('Cabañas', 9),
('Chalatenango', 33),
('Cuscatlán', 16),
('La Libertad', 22),
('La Paz', 22),
('La Unión', 18),
('Morazán', 26),
('San Miguel', 20),
('San Salvador', 19),
('San Vicente', 13),
('Santa Ana', 13),
('Sonsonate', 16),
('Usulután', 22);

create table persona(
	id int primary key auto_increment,
    nombre varchar(255),
    apellido varchar(255),
    edad int,
    id_departamento int,
    foreign key(id_departamento) references departamento(id)
);

create table usuario(
	id int primary key auto_increment,
    nombre varchar(255),
    apellido varchar(255),
    username varchar(255),
    pass varchar(255)
);