CREATE DATABASE Benitodb;
USE Benitodb;

CREATE TABLE Proveedor(
	id INT PRIMARY KEY AUTO_INCREMENT,
	nombre VARCHAR(80),
	direccion VARCHAR(100),
	telefono VARCHAR(20),
	email VARCHAR(90),
	contacto VARCHAR(30)
) ENGINE=InnoDB;

CREATE TABLE Articulo(
	id INT PRIMARY KEY AUTO_INCREMENT,
	nombre VARCHAR(80),
	precio DECIMAL(6, 2),
	folio VARCHAR(10),
	proveedorId INT,
	FOREIGN KEY (proveedorId) REFERENCES Proveedor(id) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE Estado (
	id INT PRIMARY KEY AUTO_INCREMENT,
	fecha Date,
	estado INT DEFAULT 1,
	comentario VARCHAR(100)
) ENGINE=InnoDB;

CREATE TABLE Orden(
	id INT PRIMARY KEY AUTO_INCREMENT,
	total DECIMAL(10, 2),
	subtotal DECIMAL(10, 2),
	comentario VARCHAR(100),
	fecha DATE,
	folio VARCHAR(10),
	estado INT DEFAULT 1,
	estadoComentario VARCHAR(100),
	fechaEstado Date
) ENGINE=InnoDB;

CREATE TABLE Pedido(
	id INT PRIMARY KEY AUTO_INCREMENT,
	articuloId INT,
	ordenId INT,
	cantidad INT,
	FOREIGN KEY (articuloId) REFERENCES Articulo(id) ON DELETE CASCADE,
	FOREIGN KEY (ordenId) REFERENCES Orden(id) ON DELETE CASCADE
) ENGINE=InnoDB;


DELIMITER //

CREATE PROCEDURE calculate_order_total(IN order_id INT)
BEGIN
  UPDATE Orden SET 
    total = (
        SELECT SUM(Articulo.precio * Pedido.cantidad)
        FROM Pedido
        INNER JOIN Articulo ON Pedido.articuloId = Articulo.id
        WHERE Pedido.ordenId = order_id
    ),
    subtotal = (
        SELECT SUM(Articulo.precio * Pedido.cantidad)
      FROM Pedido
      INNER JOIN Articulo ON Pedido.articuloId = Articulo.id
      WHERE Pedido.ordenId = order_id
    ) * 1.16
  WHERE id = order_id;
END//

DELIMITER ;

CREATE TRIGGER update_order_total_after_insert
AFTER INSERT ON Pedido
FOR EACH ROW
  CALL calculate_order_total(NEW.ordenId);


CREATE TRIGGER update_order_total_after_update
AFTER UPDATE ON Pedido
FOR EACH ROW
  CALL calculate_order_total(NEW.ordenId);


-- Proveedores --
INSERT INTO Proveedor (nombre, direccion, telefono, email, contacto)
VALUES
    ('Llantas Express', 'Calle de las Llantas #123, Colonia Centro, Ciudad de México', '(55) 1234-5678', 'ventas@llantasexpress.com', 'Juan Pérez'),
    ('Neumáticos S.A.', 'Avenida de los Neumáticos #456, Colonia Industrial, Guadalajara, Jalisco', '(33) 9876-5432', 'ventas@neumaticos.com.mx', 'Laura Gómez'),
    ('Rodazone', 'Calle de las Ruedas #789, Colonia La Paz, Puebla, Puebla', '(222) 555-1234', 'ventas@rodazone.com', 'José Martínez');


-- Articulos --
INSERT INTO Articulo (nombre, precio, folio, proveedorId)
VALUES
    ('Caucho sintético para fabricación de llantas', 2500, 'F11', 1),
    ('Carbono activado para uso en compuestos de llantas', 1800, 'F12', 2),
    ('Aceite para proceso de vulcanización', 1200, 'F13', 3),
    ('Fibra de vidrio para uso en llantas de alta resistencia', 3500, 'F14', 1),
    ('Adhesivo para banda de rodadura', 900, 'F15', 2),
    ('Pigmento negro para llantas', 1000, 'F16', 3),
    ('Polvo de sílice para compuestos de llantas', 1500, 'F17', 1),
    ('Talco para proceso de fabricación', 600, 'F18', 2),
    ('Nylon para refuerzo de llantas', 2200, 'F19', 3),
    ('Alambre de acero para cinturón de llantas', 3000, 'F20', 1);


INSERT INTO Orden (comentario, fecha, folio)
VALUES 
    ("Material necesario", '2022-12-12', "00001"),
    ("Buen servicio", '2022-12-27', "00002"),
    ("Para el nuevo modelo de llantas", '2023-01-17', "00003"),
    ("Falta de pago", '2023-02-18', "00004"),
    ("Gran servicio", '2023-03-08', "00005");

INSERT INTO Pedido (articuloId, ordenId, cantidad)
VALUES
    (1, 1, 5),
    (3, 1, 2),
    (6, 1, 1),
    (9, 1, 3),
    (10, 1, 1),
    (2, 2, 2),
    (4, 2, 1),
    (7, 2, 8),
    (8, 2, 2),
    (5, 3, 4),
    (1, 3, 1),
    (3, 3, 3),
    (9, 3, 5),
    (6, 4, 2),
    (7, 4, 3),
    (8, 4, 1),
    (2, 4, 1),
    (5, 5, 2),
    (10, 5, 2),
    (4, 5, 8);

--INSERT INTO Estado (fecha, estado, comentario) 
--VALUES
