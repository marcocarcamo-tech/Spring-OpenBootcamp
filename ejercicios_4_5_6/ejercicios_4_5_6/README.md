Este proyecto corresponde a los ejercicios 4, 5 y 6 del curso de Spring de Opent Bootcamp

## Ejercicio 1

Crear un proyecto Spring Boot con las dependencias:

Starters para persistencia:

H2

Spring Data JPA

Starters para web:

Spring Web

Spring Boot dev tools

Crear una clase HelloController que sea un controlador REST. Dentro de la clase crear un método que retorne un saludo. Probar que retorna el saludo desde el navegador y desde Postman.

Desde navegador
![img.png](img.png)

Desde Insomnia (alternativa a postman)
![img_1.png](img_1.png)

Ejercicio 2

Dentro de la misma app crear las clases necesarias para trabajar con "ordenadores":

Laptop (entidad)

LaptopRepository (repositorio)

LaptopController (controlador)

Desde LaptopController crear un método que devuelva una lista de objetos Laptop.

Probar que funciona desde Postman.

Los objetos Laptop se pueden insertar desde el método main de la clase principal.

Creando dos objetos desde el main, obtenemos la siguiente response en insomnia
![img_2.png](img_2.png)

Ejercicio 3

Crear un método en LaptopController que reciba un objeto Laptop enviado en formato JSON desde Postman y persistirlo en la base de datos.

Comprobar que al obtener de nuevo los laptops aparece el nuevo ordenador creado.

Utilizamos el endpoint para guardar una laptop

![img_3.png](img_3.png)

Comprobamos que al consultar los registros nos devuelve también el último que registramos

![img_4.png](img_4.png)