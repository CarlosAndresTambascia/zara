# Interview application:

[![CircleCI](https://circleci.com/gh/CarlosAndresTambascia/zara/tree/master.svg?style=svg&circle-token=391507a95ace2af3ef1f2fbc54d67bdd0ed13556)](https://circleci.com/gh/CarlosAndresTambascia/zara/tree/master)

Instrucciónes para correr el proyecto:

Tener instalado el jdk 17.

El proyecto utiliza como Maven como gestor de dependencias. Contiene un wrapper, con lo cual se puede correr el siguiente comando parado desde la raíz del proyecto para obtener las dependencias.

``.\mvnw clean install``

y luego 

```.\mvnw spring-boot:start```

La API expone un solo endpoint para consultar los precios de los productos de una dada marca en una fecha dada 
http://localhost:8080/api/v1/prices y requiere 3 parametros. Para almacenar la información se utiliza una base de datos en memoria H2, 
se puede acceder a la consola de la misma de manera local a travez de  http://localhost:8080/h2-console

Asimismo la API tiene integración con Open API y se puede acceder a la misma localmente en el siguiente endpoint http://localhost:8080/swagger-ui.html

En la carpeta raíz hay una colección de Postman con algunas pruebas manuales. (zara.postman_collection.json)