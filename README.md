# Left Rotation (Rotación a la izquierda)

**Español**

## Enunciado resuelto (resumen)
Se implementa la rotación a la izquierda de un arreglo de enteros. Cada rotación mueve los elementos una posición hacia la izquierda y el primero pasa al final. Se normaliza el número de rotaciones `d` para manejar valores mayores que el tamaño y valores negativos. Se valida el input y se cubren casos borde con TDD.

## Solución
Se usa la técnica de **tres reversas** (reverse trick):
1. Reverse del segmento izquierdo `0..(d-1)`
2. Reverse del segmento derecho `(d)..(n-1)`
3. Reverse de todo el arreglo `0..(n-1)`

Esto rota en sitio con swaps, sin estructuras extra.

### API principal
- `rotateLeft(int[] numbers, int d)`
  - Retorna un **nuevo arreglo** rotado (no muta el original).
- `rotateLeftInPlace(int[] numbers, int d)`
  - Rota **en el mismo arreglo** y devuelve la misma referencia.
- `rotateLeftInPlaceWithLog(int[] numbers, int d)`
  - Igual que in-place, pero imprime pasos clave de la rotación.
- `parseChapinNumbers(String commaSeparated)`
  - Parsea un `String` separado por comas a `int[]`, con validaciones.

### Complejidad
- Tiempo: **O(n)**
- Memoria extra:
  - `rotateLeftInPlace`: **O(1)**
  - `rotateLeft`: **O(n)** por la copia

## Pruebas (TDD)
Incluye casos:
- Caso base
- `d = 0`, `d = n`, `d > n`, `d < 0`
- Arreglo vacío, de 1 elemento
- Duplicados y negativos
- Parsing con valores inválidos

## Comandos
Ejecutar tests:
```
mvn test
```

---

**English**

## Solved statement (summary)
Implement left rotation of an integer array. Each rotation moves elements one position to the left and the first element goes to the end. The number of rotations `d` is normalized to handle values larger than the array size and negative values. Input is validated and edge cases are covered with TDD.

## Solution
Uses the **three-reverse** technique:
1. Reverse left segment `0..(d-1)`
2. Reverse right segment `(d)..(n-1)`
3. Reverse the whole array `0..(n-1)`

This rotates in place with swaps and no extra data structures.

### Main API
- `rotateLeft(int[] numbers, int d)`
  - Returns a **new rotated array** (does not mutate input).
- `rotateLeftInPlace(int[] numbers, int d)`
  - Rotates **in place** and returns the same reference.
- `rotateLeftInPlaceWithLog(int[] numbers, int d)`
  - Same as in-place, prints key steps.
- `parseChapinNumbers(String commaSeparated)`
  - Parses a comma-separated string into `int[]` with validation.

### Complexity
- Time: **O(n)**
- Extra memory:
  - `rotateLeftInPlace`: **O(1)**
  - `rotateLeft`: **O(n)** due to the copy

## Tests (TDD)
Covers:
- Base case
- `d = 0`, `d = n`, `d > n`, `d < 0`
- Empty array, single element
- Duplicates and negatives
- Parsing with invalid values

## Commands
Run tests:
```
mvn test
```
