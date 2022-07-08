# desafio-falabella
Proyecto para dar solución a desafío técnico planteado por Falabella para el puesto de desarrollador backend
## Detalles del sistema
- Spring-boot - version 2.5.2
- Swagger - version 3.0
- Lombok - version 1.18.20
- JDK 11
## Instrucciones de compilacion
Para copilar el proyecto se requiere Java y Maven instalado.

- Desde el directorio raiz del repositorio (products-api) ejecutar el siguiente comando Maven
```bash
  mvn package
```
Luego ejecutar el siguiente comando java
```bash
  java -jar .\target\desafio_Uno.war
```
## Visualizar Documentación y consumir la API
La documentación swagger del API (una vez que se levanta el API) queda disponible en 
  http://localhost:8080/swagger-ui/
En esta vista se pueden observar los diversos endpoint existentes.

## Observaciones
- Se opta por utilizar spring MVC dado el conocimiento previo con spring WebFlux se podria obtener mejor rendimiento aplicando programacion reactiva 
- Se utiliza loombok para obtener codigo mas limpio
- Se utiliza BD H2 por conocimiento previo de esta y su comportamiento mediante jpa 

### Puntos de mejora
- estructurar paquetes aplicando arquitectura hexagonal aislando de mejor forma el dominio 
- Pasar a usar bd no relacional como mongo podria mejorar los rendimientos de lectura


## Contact
Creado por r.alexander.riquelme@gmail.com