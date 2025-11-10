# ProtectoraProjectRafaReinaFerrandez

Para empezar con la estructuración del dominio seleccionado (Protectora de Animales), serán 5 tablas (que se han ido haciendo pequeñas modificaciones durante la creación del proyecto).

Animal (tabla principal)

<img width="822" height="333" alt="image" src="https://github.com/user-attachments/assets/0d20db0f-748c-42b4-8783-6eeb1229b27f" />

Adopción

<img width="788" height="352" alt="image" src="https://github.com/user-attachments/assets/24fe690a-8e62-4031-b3e0-bdf231e471f6" />

Voluntario

<img width="769" height="589" alt="image" src="https://github.com/user-attachments/assets/00d31988-e4b1-4057-8c77-7a9547147452" />

Grupo

<img width="783" height="282" alt="image" src="https://github.com/user-attachments/assets/3f9e4bc6-f25c-4628-ab19-b0771776c812" />


La útlima tabla (Voluntario_Grupo) se genera a partir de la relación N:M entre Voluntario y Grupo ya que en un grupo pueden haber 1 o muchos voluntarios y 1 Voluntario puede estar en 1 o muchos Grupos.

<img width="811" height="170" alt="image" src="https://github.com/user-attachments/assets/9ac07f4f-479f-43e8-b137-6cd41730bf4f" />




<img width="926" height="686" alt="image" src="https://github.com/user-attachments/assets/1c9095b2-3724-4b3c-aacc-090446ecbbcf" />



Elección del Dominio

Dominio Seleccionado: Protectora de Animales.
La razón por la que se ha seleccionado dicho dominio es debido a que formo parte de un voluntariado de una protectora de Elche y como futuro personal de desarrollador de software me comprometí a desarrollar, de manera altruista, una aplicación que les permitiese la comunicación y el manejo de los animales de la protectora de una manera más sencilla, rápida y efectiva.
Además, en otras asignaturas (Desarrollo de Interfaces o Programación Móvil) ya empecé a tener cierta estructura de diseño y de código.


Monitorización del Proceso

1-	Realización de esquema básico del funcionamiento de la protectora (14/10/2025)
2-	Creación de las tablas en MySQL Workbench (16/10/2025)

<img width="911" height="809" alt="image" src="https://github.com/user-attachments/assets/2fc0f848-19ef-4613-8446-776b474edca1" />
<img width="886" height="353" alt="image" src="https://github.com/user-attachments/assets/4b443c7e-ff0d-4303-9efc-a8d94306bb2e" />
<img width="886" height="231" alt="image" src="https://github.com/user-attachments/assets/1ab67014-5111-48e0-8971-c743d80fddd3" />


3-  Creación del proyecto en IntelliJ, estructuración de carpetas, y creación de archivos POJO con sus atributos (18/10/2025)

<img width="1065" height="262" alt="image" src="https://github.com/user-attachments/assets/f66db552-2777-4290-bac3-4279f62d0ba1" />


<img width="1891" height="990" alt="image" src="https://github.com/user-attachments/assets/4f53a62c-5cc7-40c7-9a8c-6774dca7b878" />

4- Introducción clase VoluntarioGrupo representa la tabla intermedia voluntario_grupo que resuelve la relación N:M entre las entidades Voluntario y Grupo.
En JDBC se define como un modelo simple (POJO) con dos atributos (dniVoluntario e idGrupo).
Se creo la conexión DBConnection que extraía los valores del config.properties para establecer una única conexión para todo el programa mediante un Singleton y se le incorporó un método close () para cerrar la conexión.

5- Debido a un error al tratar de testear la conexión, pude ver que era por falta de una Libería no instalada de Maven. Además, se creó un compartimento Docker para alojar la base de datos y se estableció conexión con ella (23/10/2025)

<img width="1261" height="329" alt="image" src="https://github.com/user-attachments/assets/9f3afb7c-e9ab-4a3c-b6fb-80f5602f290f" />

<img width="1601" height="810" alt="image" src="https://github.com/user-attachments/assets/432ca99d-8e59-443f-83c5-f507faa69a75" />

  - Creación del docker
    <img width="1481" height="760" alt="image" src="https://github.com/user-attachments/assets/96036357-b296-4d1b-8d6f-7acfd2b66ffb" />
  - Prueba de conexión
    <img width="1314" height="788" alt="image" src="https://github.com/user-attachments/assets/8d3ccbdb-1863-48c7-b045-27d5dd8487dd" />

    <img width="1839" height="552" alt="image" src="https://github.com/user-attachments/assets/add76c4a-46be-445a-ad6f-0876f598cb55" />
  
Por otro lado, se procedió con la creación de la clase ProtectoraLoginHandleDB en la que se creo al completo el menú CRUD.


<img width="1105" height="840" alt="image" src="https://github.com/user-attachments/assets/4b969072-a48a-42af-960b-8cba12038c96" />

<img width="642" height="308" alt="image" src="https://github.com/user-attachments/assets/155bc017-27b7-45f1-a76e-3740dcad2352" />

Opté por emplear el "PreparedStatement" ya que es una versión mejorada del "Statement" el cual permite parametrizar las sentencias SQL con el uso de "?" evitando, de tal forma, errores, facilitando la reutilización de la sentencia y protegiendo contra inyecciones SQL.

6- Introducción del rollback, modificación de base de datos y introducción del autocommit().
<img width="981" height="514" alt="image" src="https://github.com/user-attachments/assets/0464f5ea-d7cc-471d-9395-23c198262b6c" />

7- Integración de los métodos getTotalByEstado y getAdopciónByTipo en el que se hace dos querys, la primera con COUNT y JOIN y la segunda con JOIN y ORDER BY (28/10/2025).

<img width="1429" height="816" alt="image" src="https://github.com/user-attachments/assets/def60999-70bc-4ce8-81a2-c5101c10b966" />

Se creó, por otro lado, la clase AdopcionDTO que me permitiría devolver una lista con parametros personalizados en el método getAdopcionesByTipo () de la clase ProtectoraLoginHandleDB.

<img width="1366" height="521" alt="image" src="https://github.com/user-attachments/assets/304bf845-010d-4168-9c8d-c37f7fff892b" />


8- Modificación método update y cambio del método getTotalByEstado por getEdadMediaAdopcion, comentarios en código y Pruebas realizadas (03/11/2025)

  a. Inserts:
  <img width="1433" height="341" alt="image" src="https://github.com/user-attachments/assets/26a4bb27-a5a1-4f2e-ad3b-8ba6e5022ecb" />

  <img width="1765" height="431" alt="image" src="https://github.com/user-  attachments/assets/1c2f3ed7-610b-4dd5-8f22-c3dd82f9f645" />
  
  <img width="1186" height="314" alt="image" src="https://github.com/user-attachments/assets/51c37f7a-6ae3-4710-a400-4fe1362c7612" />

  b. Delete:
  <img width="1649" height="942" alt="image" src="https://github.com/user-attachments/assets/8344c872-ef02-47bc-93de-8fe6c6b8dcec" />

  <img width="1051" height="289" alt="image" src="https://github.com/user-attachments/assets/75ac362c-b27a-4d84-9f7a-fd32a674bd6f" />

  c. Updates (Elena pasa de ser "En refugio" --> "Adoptado"):
<img width="1872" height="694" alt="image" src="https://github.com/user-attachments/assets/78d9ef3f-3f71-483d-be60-86446696325f" />

<img width="1049" height="285" alt="image" src="https://github.com/user-attachments/assets/50dedd58-7c81-4c26-871c-44fa16b37d23" />

  d. Creación de la clase MediaEdadDTO, modificación del método getEdadMediaAdopción y pruebas (04/11/2025)
      -->Clase MediaEdadDTO
      <img width="1289" height="415" alt="image" src="https://github.com/user-attachments/assets/11b6cafe-4225-4a65-b285-73e9bd9a78ff" />
      --> Modificación método getEdadMediaAdopcion
      <img width="1044" height="380" alt="image" src="https://github.com/user-attachments/assets/ee58bae9-9407-4f9a-9210-71fe52becdbf" />
      -->Pruebas
      <img width="1624" height="638" alt="image" src="https://github.com/user-attachments/assets/ced0bd56-b327-45de-a6c2-1b98c9c00c69" />
      <img width="945" height="446" alt="image" src="https://github.com/user-attachments/assets/fc8191f2-bbaf-4c95-b707-aa68b0eb694d" />
e. Pruebas del método getAdopcionesByTipo (04/11/2025)
    -->"Gato"
    <img width="1403" height="675" alt="image" src="https://github.com/user-attachments/assets/ad360ae2-4fa2-45f4-9e8a-2d445cc1fca1" />

  <img width="1234" height="385" alt="image" src="https://github.com/user-attachments/assets/d87715ea-6a42-4758-9ce4-a6f7c0506135" />

  -->"Perro"
  <img width="1756" height="719" alt="image" src="https://github.com/user-attachments/assets/eb20fe8b-5cb1-46e8-b39d-c573fc91c050" />

<img width="1359" height="464" alt="image" src="https://github.com/user-attachments/assets/ced0b517-5b3d-41b3-a0eb-2869ec8f8b92" />

9- Exportación de la base de datos (04/11/2025)
<img width="1250" height="1029" alt="image" src="https://github.com/user-attachments/assets/8b32136d-3ca7-41af-bd46-7e0bda160367" />

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema protectora
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema protectora
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `protectora` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `protectora` ;

-- -----------------------------------------------------
-- Table `protectora`.`animal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `protectora`.`animal` (
  `id_animal` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  `tipo` ENUM('Perro', 'Gato') NOT NULL,
  `edad` INT NOT NULL,
  `estado` ENUM('En refugio', 'Adoptado') NOT NULL DEFAULT 'En refugio',
  `fecha_ingreso` DATE NOT NULL,
  PRIMARY KEY (`id_animal`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `protectora`.`adopcion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `protectora`.`adopcion` (
  `id_adopcion` INT NOT NULL AUTO_INCREMENT,
  `id_animal` INT NOT NULL,
  `nombre_adoptante` VARCHAR(60) NOT NULL,
  `telefono` VARCHAR(15) NOT NULL,
  `fecha_adopcion` DATE NOT NULL,
  `direccion` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`id_adopcion`),
  INDEX `id_animal` (`id_animal` ASC) VISIBLE,
  CONSTRAINT `adopcion_ibfk_1`
    FOREIGN KEY (`id_animal`)
    REFERENCES `protectora`.`animal` (`id_animal`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `protectora`.`voluntario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `protectora`.`voluntario` (
  `dni` CHAR(9) NOT NULL,
  `nombre` VARCHAR(100) NOT NULL,
  `telefono` VARCHAR(15) NOT NULL,
  `rol` ENUM('Responsable', 'Voluntario') NOT NULL DEFAULT 'Voluntario',
  `antiguedad` INT NOT NULL,
  PRIMARY KEY (`dni`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `protectora`.`grupo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `protectora`.`grupo` (
  `id_grupo` INT NOT NULL AUTO_INCREMENT,
  `dia_semana` ENUM('Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado', 'Domingo') NOT NULL,
  `turno` ENUM('Mañana', 'Tarde') NOT NULL,
  `responsable` CHAR(9) NULL DEFAULT NULL,
  PRIMARY KEY (`id_grupo`),
  INDEX `responsable` (`responsable` ASC) VISIBLE,
  CONSTRAINT `grupo_ibfk_1`
    FOREIGN KEY (`responsable`)
    REFERENCES `protectora`.`voluntario` (`dni`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `protectora`.`voluntario_grupo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `protectora`.`voluntario_grupo` (
  `dni_voluntario` CHAR(9) NOT NULL,
  `id_grupo` INT NOT NULL,
  PRIMARY KEY (`dni_voluntario`, `id_grupo`),
  INDEX `id_grupo` (`id_grupo` ASC) VISIBLE,
  CONSTRAINT `voluntario_grupo_ibfk_1`
    FOREIGN KEY (`dni_voluntario`)
    REFERENCES `protectora`.`voluntario` (`dni`),
  CONSTRAINT `voluntario_grupo_ibfk_2`
    FOREIGN KEY (`id_grupo`)
    REFERENCES `protectora`.`grupo` (`id_grupo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


10- Creación menú opciones Main, modificación del método update y pruebas (10/11/2025):
 a. Parte del menú de opciones
 <img width="1860" height="1080" alt="image" src="https://github.com/user-attachments/assets/f8e3fb98-9fa7-4fd6-941c-9a0a8dc47b42" />
 b. Modificación método update
 <img width="1245" height="969" alt="image" src="https://github.com/user-attachments/assets/f4491bb4-9568-4114-840f-2a980738dbb9" />
 c. Pruebas
 <img width="655" height="694" alt="image" src="https://github.com/user-attachments/assets/9b4bfe7f-7160-4b9f-8e0b-906664098a7c" />
 <img width="715" height="208" alt="image" src="https://github.com/user-attachments/assets/e99d7d22-fbd4-460b-a578-2bfb5d58056e" />

