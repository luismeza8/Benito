CREATE DATABASE Benitodb;
USE Benitodb;

CREATE TABLE Proveedor(
	id INT PRIMARY KEY AUTO_INCREMENT,
	nombre VARCHAR(20),
	direccion VARCHAR(50),
	telefono VARCHAR(10),
	email VARCHAR(30),
	contacto VARCHAR(30)
) ENGINE=InnoDB;

CREATE TABLE Articulo(
	id INT PRIMARY KEY AUTO_INCREMENT,
	nombre VARCHAR(20),
	precio DECIMAL(6, 2),
	folio VARCHAR(10),
	proveedorId INT,
	FOREIGN KEY (proveedorId) REFERENCES Proveedor(id) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE Orden(
	id INT PRIMARY KEY AUTO_INCREMENT,
	total DECIMAL(6, 2),
	subtotal DECIMAL(6, 2),
	comentario VARCHAR(100),
	fecha DATE,
	folio VARCHAR(10)
) ENGINE=InnoDB;

CREATE TABLE Pedido(
	id INT PRIMARY KEY AUTO_INCREMENT,
	articuloId INT,
	ordenId INT,
	cantidad INT,
	FOREIGN KEY (articuloId) REFERENCES Articulo(id) ON DELETE CASCADE,
	FOREIGN KEY (ordenId) REFERENCES Orden(id) ON DELETE CASCADE
) ENGINE=InnoDB;

INSERT INTO Proveedor (nombre, direccion, telefono, email, contacto)
VALUES ("Tienda", "Gaspar", "6221469791", "tienda@gmail.com", "Luis");

INSERT INTO Articulo (nombre, precio, folio, proveedorId)
VALUES ("chicle", 1.50, "00001", 1);

INSERT INTO Orden (total, subtotal, comentario, fecha, folio)
VALUES (1.5, 2.0, "Bien", '2018-05-12', "00001");

INSERT INTO Pedido (articuloId, ordenId, cantidad)
VALUES (1, 1, 2);
