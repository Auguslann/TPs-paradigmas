# BuqueTruck

Este proyecto modela el funcionamiento de un buque de una compañía naviera de contenedores en Haskell. El buque tiene una ruta establecida y una cantidad de bahías para estiba, todas con una capacidad de carga específica.

## Funcionamiento

El buque sigue una ruta predefinida y puede cargar y descargar contenedores en cada puerto de escala según las siguientes reglas:

- Al llegar a uno de los destinos de su ruta, el buque puede descargar los contenedores con ese destino simplemente desapilando. No puede apilarse un contenedor con destino posterior al que le queda debajo.
- La carga de un contenedor se realiza solo si hay una bahía disponible que acepte su carga. Cada bahía tiene una capacidad máxima de carga de 20 toneladas.
- Los contenedores tienen un destino específico y un peso en toneladas.

## Uso del Código

El proyecto proporciona funciones y tipos de datos que simulan el funcionamiento descrito anteriormente. A continuación, se presentan los principales módulos y su funcionalidad:

- **Buque.hs:** Contiene las definiciones y funciones relacionadas con el buque de la compañía naviera.
- **Bahia.hs:** Contiene las definiciones y funciones relacionadas con las bahías del buque.
- **Contenedor.hs:** Contiene las definiciones y funciones relacionadas con los contenedores de carga.

## Ejemplo de Uso

```haskell
-- Crear una Ruta para el buque
route1 = newR ["Buenos Aires", "Rosario", "Cordoba"]

-- Crear un buque con una ruta y una cantidad de bahías
vessel0 = newV 2 1 route1

-- Crear contenedores con destino y peso
contenedor2 = newC "Buenos Aires" 10
contenedor1 = newC "Rosario" 20
contenedor0 = newC "Cordoba" 20

-- Cargar los contenedores en el buque
vessel1 = foldr (flip loadV)  vessel0 [contenedor0, contenedor1, contenedor2]

-- Descargar los contenedores en un puerto de destino
vessel2 = unloadV vessel1 "Rosario" -- Puede ingresar cualquier ciudad para borrarla de las bahias del barco

-- Cargar más contenedores en el buque
vessel3 = loadV vessel2 (Con "Buenos Aires" 10)
vessel4 = loadV vessel3 (Con "Aarhus" 12)
