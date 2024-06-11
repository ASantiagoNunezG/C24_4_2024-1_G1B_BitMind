CREATE DATABASE VETERINARIA2
-------------------------------
USE VETERINARIA2
-------------------------------
CREATE TABLE categoria(
id_categoria INT PRIMARY KEY,
nombre VARCHAR(20) CHECK (nombre REGEXP '^[a-zA-Z ]+$')
)
-------------------------------
CREATE TABLE venta_des(
id_venta_des INT PRIMARY KEY,
id_cliente INT,
id_empleado INT,
metodo_pago VARCHAR(15) NOT NULL CHECK (metodo_pago LIKE '%Efectivo%' OR metodo_pago LIKE '%Cheque%' OR metodo_pago LIKE '%Yape%'),
fecha DATE,
total DOUBLE NOT NULL CHECK (total > 0),
FOREIGN KEY (id_cliente) REFERENCES usuarios(id_cliente),
FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado)
)
------------------------------
CREATE TABLE venta_pro(
id_venta_pro INT,
id_producto INT,
cantidad INT NOT NULL CHECK (cantidad > 0),
precio DOUBLE NOT NULL CHECK (precio > 0),
subtotal DOUBLE NOT NULL CHECK (subtotal > 0),
PRIMARY KEY (id_venta_pro, id_producto),
FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
)
-------------------------------
CREATE TABLE producto(
id_producto INT PRIMARY KEY,
id_categoria INT,
nombre_pro VARCHAR(50) CHECK (nombre_pro REGEXP '^[a-zA-Z ]+$'),
precio DOUBLE NOT NULL CHECK (precio > 0),
stock INT NOT NULL CHECK (stock > 0),
FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
)
-------------------------------------------------
CREATE TABLE clasificacion(
id_clasificacion INT PRIMARY KEY,
nombre_cla VARCHAR(20) CHECK (nombre_cla REGEXP '^[a-zA-Z ]+$')
)
-------------------------------------------------
CREATE TABLE empleado(
id_empleado INT PRIMARY KEY,
nom_empleado VARCHAR(25) CHECK (nom_empleado REGEXP '^[a-zA-Z ]+$')
)
INSERT INTO empleado VALUES (1,"Por fin")
INSERT INTO empleado VALUES (2,"Look at me")
-------------------------------------------------
CREATE TABLE mascota(
id_mascota INT PRIMARY KEY,
id_clasificacion INT,
tipo_mascota VARCHAR(10) CHECK (tipo_mascota REGEXP '^[a-zA-Z ]+$'),
nombre VARCHAR(30) CHECK (nombre REGEXP '^[a-zA-Z ]+$'),
raza VARCHAR(10) CHECK (raza REGEXP '^[a-zA-Z ]+$'),
sexo VARCHAR(10) CHECK (sexo REGEXP '^[a-zA-Z ]+$'),
color VARCHAR(30) CHECK (color REGEXP '^[a-zA-Z ]+$'),
peso VARCHAR(10) CHECK (peso REGEXP '^[a-zA-Z0-9. ]+$'),
edad VARCHAR(15) CHECK (edad REGEXP '^[a-zA-Z0-9. ]+$'),
observaciones VARCHAR(100) CHECK (observaciones REGEXP '^[a-zA-Z0-9. ]+$'),
fecha_llegada DATE,
FOREIGN KEY (id_clasificacion) REFERENCES clasificacion(id_clasificacion)
)
INSERT INTO mascota VALUES(2,2,'Gato','Conan','Abisinio','Hembra','Negro y blanco','3 kg','3 meses','Sin observaciones','2023-04-02')
INSERT INTO mascota VALUES(3,3,'Perro','Estrella','Pastor Al','Macho','Negro y blanco','6 kg','3 meses','Sin observaciones','2023-04-02')
INSERT INTO mascota VALUES(4,1,'Gato','White','Elfo','Macho','Blanco','2 kg','2 meses','Muy adorable','2023-04-05')
INSERT INTO mascota VALUES(3,3,'Perro','Estrella','Pastor Al','Macho','Negro y blanco','6 kg','3 meses','','2023-04-01')
usuariosdocumento
ALTER TABLE mascota
ADD CONSTRAINT chk_sexo CHECK (sexo IN ('Macho', 'Hembra'));
---------------------------------------------
CREATE TABLE documento(
id_documento INT PRIMARY KEY,
id_cliente INT,
id_mascota INT,
desparacitado VARCHAR(2) CHECK (desparacitado LIKE '%NO%' OR desparacitado LIKE '%SI%'),
estado VARCHAR(10) CHECK (estado REGEXP '^[a-zA-Z ]+$'),
fecha_inicio DATE,
fecha_fin DATE,
id_empleado INT,
FOREIGN KEY (id_cliente) REFERENCES usuarios(id_cliente),
FOREIGN KEY (id_mascota) REFERENCES mascota(id_mascota)
)
ALTER TABLE documento ADD CONSTRAINT uk_id_mascota UNIQUE (id_mascota)
-------------------
----------------------------------------------
CREATE TABLE usuarios(
id_cliente INT AUTO_INCREMENT PRIMARY KEY,
nombre_cli VARCHAR(50) CHECK (nombre_cli REGEXP '^[a-zA-Z ]+$'),
apellido_cli VARCHAR(50) CHECK (apellido_cli REGEXP '^[a-zA-Z ]+$'),
dni_cli VARCHAR(8) CHECK (dni_cli REGEXP '^[0-9]+$'),
edad INT NOT NULL CHECK (edad >= 18 AND edad <= 100),
direccion_cli VARCHAR(50) CHECK (direccion_cli REGEXP '^[a-zA-Z0-9. ]+$'),
celular VARCHAR(15) CHECK (celular REGEXP '^[0-9+ ]+$'),
correo VARCHAR(50) CHECK (correo REGEXP '^[a-zA-Z0-9.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$'),
contraseña VARCHAR(50)
)
INSERT INTO usuarios (nombre_cli, apellido_cli, dni_cli, edad, direccion_cli, celular, correo, contraseña) VALUES ('John', 'Doe', '12345678', 25, 'Calle Principal', '1234567890', 'john@example.com', '123');
INSERT INTO usuarios (nombre_cli, apellido_cli, dni_cli, edad, direccion_cli, celular, correo, contraseña) VALUES ('Abraham', 'Nuez', '12345678', 20, 'Calle calle', '76328569', 'otro@example.com', '231');

---------------------------------------------
CREATE TABLE proveedor(
id_proveedor INT PRIMARY KEY,
id_producto INT,
nombre_prove VARCHAR(25) CHECK (nombre_prove REGEXP '^[a-zA-Z ]+$'),
ruc_prov VARCHAR(11) CHECK (ruc_prov REGEXP '^[0-9]+$'),
FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
)

INSERT INTO clasificacion VALUES(1,"Rescatado")
INSERT INTO clasificacion VALUES(2,"Nacido")
INSERT INTO clasificacion VALUES(3,"Donado")

SELECT m.nombre, m.edad, m.tipo_mascota, m.sexo, u.nombre_cli, u.apellido_cli, u.dni_cli, e.nom_empleado
FROM mascota m
JOIN usuarios u ON u.id_cliente = id_cliente
JOIN empleado e ON e.id_empleado = id_empleado
WHERE m.id_mascota = 1 AND u.id_cliente = 1 AND e.id_empleado = 2;sys


SHOW VARIABLES LIKE 'datadir';