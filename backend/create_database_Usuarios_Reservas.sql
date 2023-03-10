


CREATE TABLE IF NOT EXISTS `0521PTC6N2db_GRUPO8`.`reservas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `hora_inicio` TIME,
  `fecha_checkin` date NOT NULL,
  `fecha_checkout` date NOT NULL,
  `id_producto` INT NOT NULL,
  `id_usuario` INT NOT NULL, 
    PRIMARY KEY (`id`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `0521PTC6N2db_GRUPO8`.`roles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
 
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `0521PTC6N2db_GRUPO8`.`usuarios` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `apellido` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(50) NOT NULL, 
  `id_rol` INT NOT NULL,
    PRIMARY KEY (`id`))
ENGINE = InnoDB;

ALTER TABLE 0521PTC6N2db_GRUPO8.usuarios
ADD FOREIGN KEY (id_rol)
REFERENCES 0521PTC6N2db_GRUPO8.roles(id);


ALTER TABLE 0521PTC6N2db_GRUPO8.reservas
ADD COLUMN id_producto INT NOT NULL;

ALTER TABLE 0521PTC6N2db_GRUPO8.reservas
ADD FOREIGN KEY (id_producto)
REFERENCES 0521PTC6N2db_GRUPO8.productos(id);

ALTER TABLE 0521PTC6N2db_GRUPO8.reservas
ADD COLUMN id_usuario INT NOT NULL;

ALTER TABLE 0521PTC6N2db_GRUPO8.reservas
ADD FOREIGN KEY (id_usuario)
REFERENCES 0521PTC6N2db_GRUPO8.usuarios(id);






