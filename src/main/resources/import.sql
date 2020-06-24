
-- Creacion de usuarios con sus roles -- 

INSERT INTO usuarios (username,password,enabled,nombre,apellido,email) VALUES ('daniel','$2a$10$JztpjynRLcysiVWOv6pg3ukHiwtVo5B8AB/v2mUEmZZwdr8QdFTra',1,'Daniel','Alfonso','danielalfonso.29@hotmail.com');
INSERT INTO usuarios (username,password,enabled,nombre,apellido,email) VALUES ('admin','$2a$10$fpcvcBIYRqW7/nlQo20dMOtfVPRv6qbgw7.Er1XEJeK29haTFsPJG',1,'Fonseca','Alfonso','FonsecaAlfo@gmail.com');

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (1,1);
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (2,2);
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (2,1);

--- Poblar vendedores ---
INSERT INTO vendedores(email,fecha_creacion,nombre) VALUES('danie@gmail.com',NOW(),'Panasonic');
INSERT INTO vendedores(email,fecha_creacion,nombre) VALUES('felipe@gmail.com',NOW(),'FredPerry');


--- Poblar compradores--- 
INSERT INTO compradores(apellido,email,fecha_nacimiento,foto,nombre) VALUES('alfonso','danielalfonso@gmail.com','2018-01-01',null,'daniel');

-- Poblar tabla productos -- 
INSERT INTO productos(nombre,precio,fecha_creacion,vendedor_id,descripcion) VALUES ('Panasonic Pantalla LCD',1500000,NOW(),1,'Descripcion');
INSERT INTO productos(nombre,precio,fecha_creacion,vendedor_id,descripcion) VALUES ('Panasonic Radio',500000,NOW(),1,'Descripcion');
INSERT INTO productos(nombre,precio,fecha_creacion,vendedor_id,descripcion) VALUES ('Pc Gamer',4000000,NOW(),1,'Descripcion');
INSERT INTO productos(nombre,precio,fecha_creacion,vendedor_id,descripcion) VALUES ('VideoBeam',250000,NOW(),1,'Descripcion');
INSERT INTO productos(nombre,precio,fecha_creacion,vendedor_id,descripcion) VALUES ('PlayStation 5',3500000,NOW(),1,'Descripcion');
INSERT INTO productos(nombre,precio,fecha_creacion,vendedor_id,descripcion) VALUES ('Mesa Comedor',300000,NOW(),1,'Descripcion');
INSERT INTO productos(nombre,precio,fecha_creacion,vendedor_id,descripcion) VALUES ('Coleccion videojuegos',250000,NOW(),1,'Descripcion');
INSERT INTO productos(nombre,precio,fecha_creacion,vendedor_id,descripcion) VALUES ('Samsung Pantalla LCD',159990,NOW(),1,'Descripcion');
INSERT INTO productos(nombre,precio,fecha_creacion,vendedor_id,descripcion) VALUES ('Samsung A10',159990,NOW(),1,'Descripcion');
INSERT INTO productos(nombre,precio,fecha_creacion,vendedor_id,descripcion) VALUES ('Xbox One',1500000,NOW(),1,'Descripcion');
INSERT INTO productos(nombre,precio,fecha_creacion,vendedor_id,descripcion) VALUES ('Bicicleta bianchi',5000000,NOW(),2,'Descripcion');
INSERT INTO productos(nombre,precio,fecha_creacion,vendedor_id,descripcion) VALUES ('Bicicleta pinarello dogma',10000000,NOW(),2,'Descripcion');
INSERT INTO productos(nombre,precio,fecha_creacion,vendedor_id,descripcion) VALUES ('Camisa fredPerry',100000,NOW(),2,'Descripcion');
INSERT INTO productos(nombre,precio,fecha_creacion,vendedor_id,descripcion) VALUES ('Buso FredPerry',200000,NOW(),2,'Descripcion');
INSERT INTO productos(nombre,precio,fecha_creacion,vendedor_id,descripcion) VALUES ('Zapatos fredperry',250000,NOW(),2,'Descripcion');
INSERT INTO productos(nombre,precio,fecha_creacion,vendedor_id,descripcion) VALUES ('Botas Doctor Martens',100000,NOW(),2,'Descripcion');



