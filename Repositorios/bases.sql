-- Usuarios

CREATE SCHEMA IF NOT EXISTS `usuarios_api` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE IF NOT EXISTS `usuarios_api`.`usuarios` (
  
`idusuario` INT NOT NULL AUTO_INCREMENT,
  
`tipo` VARCHAR(45) NULL,
  
`nombre` VARCHAR(45) NULL,
  
`apellidos` VARCHAR(60) NULL,
  
`edad` INT NULL,
  
`telefono` VARCHAR(45) NULL,
  
`correoElectronico` VARCHAR(60) NULL,
  
`contrasenia` VARCHAR(64) NULL,
  
PRIMARY KEY (`idusuario`));

-- Usuario admin con contrasenia sesamo
INSERT INTO `usuarios_api`.`usuarios` (tipo,nombre,apellidos,edad,telefono,correoElectronico,contrasenia) VALUES ("GERENCIAL","Master","Control",99,"123456","admin@minerio.com","561e9971e9fd2dd999d2e24318e01d91d09aff3291c20c3029f3986358ec2856");
INSERT INTO `usuarios_api`.`usuarios` (tipo,nombre,apellidos,edad,telefono,correoElectronico,contrasenia) VALUES ("OPERADOR","Master","Conductor",99,"123456","conductor@minerio.com","561e9971e9fd2dd999d2e24318e01d91d09aff3291c20c3029f3986358ec2856");


-- Congestiones

CREATE SCHEMA IF NOT EXISTS `congestiones_api` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE IF NOT EXISTS `congestiones_api`.`congestiones` (
  `idcongestiones` INT(11) NOT NULL AUTO_INCREMENT,
  `x` DOUBLE NULL DEFAULT NULL,
  `y` DOUBLE NULL DEFAULT NULL,
  `descripcion` VARCHAR(200) NULL DEFAULT NULL,
  `fecha` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`idcongestiones`))
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8;


-- Transportes

CREATE SCHEMA IF NOT EXISTS `transportes_api` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE IF NOT EXISTS `transportes_api`.`transportes` (
  `idtransporte` INT NOT NULL AUTO_INCREMENT,
  `matriculavehiculo` VARCHAR(50) NOT NULL,
  `nombreentrega` VARCHAR(100) NOT NULL,
  `material` VARCHAR(50) NOT NULL,
  `cantidad` DOUBLE NOT NULL,
  `medida` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idtransporte`))
ENGINE = InnoDB;

-- Usuario y permisos

CREATE USER 'minerio'@'localhost' IDENTIFIED BY 'sesamo';
GRANT ALL PRIVILEGES ON congestiones_api.* TO 'minerio'@'localhost';
GRANT ALL PRIVILEGES ON usuarios_api.* TO 'minerio'@'localhost';
GRANT ALL PRIVILEGES ON transportes_api.* TO 'minerio'@'localhost';
SET GLOBAL time_zone = "-7:00";
SELECT @@global.time_zone;