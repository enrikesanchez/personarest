## Ejecutar la aplicación con Maven

En una terminal con JAVA_HOME y el path correctamente configurados

```bash
mvn clean package
```

### Si las variables de entorno no están configuradas ejecutar


**Windows**
```shell
SET JAVA_HOME="C:\Program Files\apache\maven-3.8.3"
SET JAVA_HOME="C:\Program Files\Eclipse Adoptium\jdk-17.0.5.8-hotspot"
SET PATH=%PATH%;%JAVA_HOME%\bin;%MVN_HOME%\bin
mvn clean package
```



## Inicializar la aplicación con Docker

Ejecutar la aplicación y la base de datos utilizar el comando:

```bash
docker-compose up
```


Levantar únicamente el contenedor de MySQL

```bash
docker-compose up db
```