# Jump2Digital 2022 | Backend

Reto previo al evento Jump2Digital de Nuwe. Este repositorio contiene una spring API con varios endpoints que te filtran una lista de empresas dada (https://challenges-asset-files.s3.us-east-2.amazonaws.com/jobMadrid/companies.json).

## Instalación

El proyecto puede ser descargado en un archivo .zip y hacerlo funcionar en tu IDE.

  #Importante!: tengo instalado y uso lombok en mi IDE. Si no tienes lombok tendrás que crear setters, getters y constructores de las clases.
La API funciona con MongoDB, es necesario tener instalado previamente MongoDb porque utiliza el localHost por defecto. Una vez instalado, crear una base de datos con nombre "jump2digital" que es la indicada en aplications.properties de la API. Subir a jump2digital el json de las compañías del ejercicio en una colección con el nombre "companies". Todo esto se puede hacer fácilmente desde MongoDBCompass que puedes instalar con la instalación de Mongo.

## Uso
El ejercicio exigia tres endpoints y los puedes probar en tu navegador o Postman (recomendado) mientras la API se esté ejecutando en tu IDE.

El primer endpoint te devuelve la lista filtrada según el tamaño de las compañias> http://localhost:8080/orderBySize

El segundo endpoint te devuelve las compañías ordenadas según cuando fueron fundadas> http://localhost:8080/orderByFounded

El tercer endpoint te devuelve los siguientes datos: Número de empresas que hay en cada industria, Número de empresas que  hay por cada rango de tamaños, Número de empresas que hay en cada año de creación> http://localhost:8080/IndustrySizeFounded


## API Design

La estructura de los paquetes es la siguiente:

- Controller: aporta los endpoints con la inyección del repositorio de compañías.
- Domain: aquí se encuentra la clase Company que refiere a la colección de compañías. Además tengo 3 DTO que nos sirven para ejecutar el tercer endpoint.
- Repository: interfaz con MongoRepository importado y implementado. Nos sirve para comunicar el controller con la colección "companies".
- Config: contiene un swagger con el que puedes hacer el Test de los diferentes GET del Controller sin necesidad de Postman y tienes un poco de información de más sobre el proyecto.

## Resultados del Postman

http://localhost:8080/orderBySize
>> Lista ordenada de pequeño a mayor Size:
![image](https://user-images.githubusercontent.com/107991714/198871426-5aba813f-ceac-40ee-bd5f-b6aea97279b9.png)

http://localhost:8080/orderByFounded
>> Lista ordenada de empresas más nuevas a más viejas:
![image](https://user-images.githubusercontent.com/107991714/198871529-0af59a8d-3e60-471f-a1cc-68931f8f63b8.png)


http://localhost:8080/IndustrySizeFounded
>> Cantidad de compañías por industria, tamaño y año de inaguración:
![image](https://user-images.githubusercontent.com/107991714/198871574-3d544184-313c-475c-ad5f-e19902c361c4.png)
