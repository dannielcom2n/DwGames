create database dwGames;
use dwGames;

CREATE TABLE Cliente(
	id INT NOT NULL AUTO_INCREMENT,
	nome VARCHAR(45),
	telefone INT(8),
	email VARCHAR(25),
	senha VARCHAR(25),
	sexo VARCHAR(25),
	PRIMARY KEY(id)
	);
	
    CREATE TABLE Produto(
	id Int NOT NULL AUTO_INCREMENT,
	codigoDeBarras INT(25),
	nome VARCHAR(45),
	valor double(7,1),
    marca VARCHAR(45),
	quantidade INT(10),
	descricao VARCHAR(45),
	
	PRIMARY KEY(id)
	
	);
 
 CREATE TABLE User(
 id Int NOT NULL AUTO_INCREMENT,
 email VARCHAR(45),
 senha VARCHAR(45),
 
 PRIMARY KEY(id)
 );
