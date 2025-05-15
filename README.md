# POOBKEMON
PoobKemon es una Simulación del juego Pokemon Esmeralda,encontraremos diferentes modos de juego donde interactuaremos con la maquina o un rival enfrentadonos en una batalla pokemon con diferentes y gran variedad de pokemones.


## Prerrequisitos
- Java 17 o superior
- `javac` y `java` configurados en el PATH
- Terminal (PowerShell, CMD o IntelliJ Terminal)

## librerías  necesarias(incluidas en la carpeta lib)

- `junit-jupiter-api-5.10.0.jar`
- `junit-jupiter-engine-5.10.0.jar`
- `junit-platform-commons-1.10.0.jar`
- `junit-platform-engine-1.10.0.jar`
- `junit-platform-launcher-1.10.0.jar`
- `junit-jupiter-params-5.10.0.jar`
- `opentest4j-1.3.0.jar`
- `apiguardian-api-1.1.2.jar`
- `junit-platform-console-standalone-1.10.0.jar`



##  Como ejecutar desde consola

- Primero debemos descomprimir nuestro archivo.zip
- Abrimos nuestro cmd(consola)
- Nuestro primer comando sera la ruta de acceso  (`C:\Users\Jasua\Downloads\PoobKemon.2`)en este caso no seria jasua ya que es depende del nombre de nuestro usuario 
- Siguiente comando (`javac -encoding UTF-8 -d out -cp "lib/*;." src/Domain/*.java src/Presentation/*.java src/Tests/*.java)`debe copilar exitosamente nuestro proyecto
- Siguiente comando (`java -cp "lib/*;out" Presentation.InicioPoobkemon`
  )ejecuta nuestra clase IncioPoobkemon que es la que tiene el main y llama la carpeta lib que es la que tiene las importaciones necesarias.jar para que nuestro proyecto ejecute 
- Ejecución(Debes tener en cuenta las rutas de acceso con los .gif y .png)


## Ejecución de pruebas

- Siguiente comando despues de la Ejecución del main de nuestro proyecto `(java -jar lib/junit-platform-console-standalone-1.10.0.jar --class-path out --scan-class-path
  )`este hara los test de cada prueba de nuestro paquete Domain


Las pruebas incluidas aseguran que los elementos centrales del juego funcionen correctamente:

-  **Combate entre Pokémon**: ataques básicos, especiales y personalizados; cálculo de daño y efectividad por tipo.
- **Uso de ítems**: comportamiento correcto de Pociones, Superpociones, Hiperpociones y Revivir en distintos estados del Pokémon.
- **Lógica de progreso**: aumento de nivel, curación, límites de salud.
-  **Gestión de entrenadores**: validación de atributos (nombre, nivel, especialidad) y su representación textual.
- **Integración completa**: interacción correcta entre clases en situaciones reales de combate y recuperación.

## Autores
 
Juan Andres Suarez Fonseca - Maria Paula Rodriguez Muñoz 
