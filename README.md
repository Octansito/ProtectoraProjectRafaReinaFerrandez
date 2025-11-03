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


8- Pruebas realizadas (03/11/2025)

  a. Inserts:
  <img width="1433" height="341" alt="image" src="https://github.com/user-attachments/assets/26a4bb27-a5a1-4f2e-ad3b-8ba6e5022ecb" />

