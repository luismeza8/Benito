CREATE DATABASE Benitodb;
USE Benitodb;

CREATE TABLE Proveedor(
	id INT PRIMARY KEY AUTO_INCREMENT,
	nombre VARCHAR(20),
	direccion VARCHAR(50),
	telefono VARCHAR(10),
	email VARCHAR(30),
	contacto VARCHAR(30)
);

CREATE TABLE Articulo(
	id INT PRIMARY KEY AUTO_INCREMENT,
	nombre VARCHAR(20),
	precio DECIMAL(6, 2),
	folio VARCHAR(10),
	proveedorId INT,
	FOREIGN KEY (proveedorId) REFERENCES Proveedor(id)
);

CREATE TABLE Pedido(
	id INT PRIMARY KEY AUTO_INCREMENT,
	articuloId INT,
	ordenId INT,
	cantidad INT,
	FOREIGN KEY (articuloId) REFERENCES Articulo(id)
	FOREIGN KEY (ordenId) REFERENCES Orden(id)
);

CREATE TABLE Orden(
	id INT PRIMARY KEY AUTO_INCREMENT,
	total DECIMAL(6, 2),
	subtotal DECIMAL(6, 2),
	comentario VARCHAR(100),
	fecha DATE,
	folio VARCHAR(10)
);
