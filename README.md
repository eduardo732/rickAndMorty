# Título del Proyecto

Desafío Rick and Morty [https://rickandmortyapi.com/documentation]

## Comenzando 🚀

Api de prueba, realizada para poner en práctica los conocimientos aprendidos en Java
con Springboot, Patrones de Diseño, Arquitectura, etc.


### Instalación 🔧

Para correr la api, basta con clonar el proyecto desde github, tener en su máquina
Java versión 1.8. En mi caso utilicé Intellij IDEA como editor para crear y correr el proyecto.

### Explicación 🔧

    La arquitectura está formada de la siguiente manera:
    -domain: Se encuentran las clases de dominio como las entidades
    -infraestructure: Será la responsable del funcionamiento del framework
    ya sea para la gestion de errores, respuestas, crear beans y configuraciones
    -application: La capa responsable de la lógica de negocio (Service, Repository, etc)

-En cuanto a la arquitectura, me basé en mis conocimientos previos de Clean architecture y decidí 
utilizar una arquitectura hexagonal ya que se ve muy robusta en cuanto a un mantenimiento prolongado y testing.

-Dentro del código se podrán encontrar patrones de diseño tales como DTO, ID.
Al momento de ejecutar o probar el proyecto, se puede utilizar postman o incluso el navegador web
apuntando a la siguiente url: http://localhost:8080/v1/character/{id} donde id es el numero identificador
del personaje que va dentro del rango 1-826 incluyendo los limites según la documentación.

-El formato de respuesta, cambia un poco con lo solicitado, ya que para un correcto manejo de errores
tuve que crear una clase que pudiera manejar las respuestas obtenidas dentro de la api.

Quedando de la siguiente manera:
    
    {
    "errors": [],
    "status": "",
    "payload": ""
    }

Donde errors, trae consigo una lista de posibles errores que pueden ocurrir dentro de la api.
status, donde lanza el mensaje de error correspondiente,
y payload donde lanza la respuesta esperada por MobDev.


### TESTING 🛠️

En cuanto al testing, se realizó con JUnit y Mockito, bajo los conocimientos adquiridos autodidacticamente.
Se testeó la capa de infraestructura especificamente el controller con su respectivo endpoint. 
También se hizo un intentó de testing en la capa application especificamtene el service
en donde por falta de tiempo y de conocimientos, no se pudo realizar de manera exitosa.

## Construido con 🛠️

_Menciona las herramientas que utilizaste para crear tu proyecto_

* [Springboot] - El framework web usado para el back
* [Java] - El lenguaje de programación utilizado en su verión 1.8


## Autores ✒️

* **Eduardo Fuentes**  - [github](https://github.com/eduardo732) - [linkedin](https://www.linkedin.com/in/eduardofuentesreyes/)
