```md
## Ejercicio: Rotación a la izquierda en arreglos

Crea la función **"Rotación a la izquierda en arreglos"**.

Una operación de **rotación a la izquierda** sobre un arreglo desplaza cada uno de sus elementos **1 posición hacia la izquierda**. Los elementos que “se salen” por el extremo izquierdo **vuelven a aparecer al final** (extremo derecho).

Dado un entero **d**, rota el arreglo esa cantidad de pasos hacia la izquierda y **devuelve el resultado**.

### Ejemplo

**Entrada:**
- `d = 2`
- `arr = [1, 2, 3, 4, 5]`

**Salida (después de 2 rotaciones):**
- `arr' = [3, 4, 5, 1, 2]`
```

===

---

# Ahora sí: Rotación a la izquierda (TDD + opción eficiente)

## Normalización clave (SIEMPRE)

Antes de cualquier algoritmo, hacé:

* Si `arr` es vacío → devolvés vacío inidicándolo.
* Si `arr` viene en cadena de texto, convertir a numerico cada caracter separando por "," a un nuevo arr_numeric
* `d = d % n` para no rotar de más.
* Si `d == 0` → devolvés una copia o el mismo (de la forma adecuada acorde al enunciado).

---

## Opciones eficientes (nivel mid, sin librerías externas)

### Opción A) **3-reverses in-place** (la más “pro” y óptima en memoria)

**Idea:** rotar `d` es equivalente a:

1. reverse(0..d-1)
2. reverse(d..n-1)
3. reverse(0..n-1)

* Tiempo: **O(n)**
* Memoria: **O(1)**
* Muy usada en entrevistas y producción.

Ejemplo `1 2 3 4 5`, `d=2`:

* rev(1 2) => 2 1 3 4 5
* rev(3 4 5) => 2 1 5 4 3
* rev(todo) => 3 4 5 1 2 ✅

---

### Opción B) **Copia con índices** (simple, rápida, muy limpia)

Creás `result[n]` y reasignás:

`result[i] = arr[(i + d) % n]`  (para rotación izquierda)

* Tiempo: **O(n)**
* Memoria: **O(n)**
* Más fácil de leer/testear; buen estándar “mid”.

---

### Opción C) **Juggling algorithm (GCD cycles)** (óptima en memoria, más técnica)

Mueve elementos en ciclos usando `gcd(n, d)`.

* Tiempo: **O(n)**
* Memoria: **O(1)**
* Correcta, pero más fácil equivocarse; yo la dejo como alternativa.

---

### ¿HashTable / List / etc?

* **HashTable** no aporta nada: no necesitas búsqueda por clave.
* **List/ArrayList**: si lo resolvés con subList + addAll, igual terminás en O(n) pero con overhead y menos control.
* Para este problema, **arreglo + aritmética** es lo correcto.

---

# Qué vamos a hacer (en modo TDD con JUnit)

Te propongo implementar **Opción A (3-reverses)** porque es la más “pro” y sin costo extra de memoria.

### Casos de prueba mínimos (JUnit)

1. `d=2`, `[1,2,3,4,5]` => `[3,4,5,1,2]`
2. `d=0` => mismo arreglo
3. `d=n` => mismo arreglo
4. `d>n` (ej. `d=7`, n=5) => igual a `d=2`
5. arreglo vacío `[]`
6. arreglo de 1 elemento `[9]`

---

## Código sugerido (estructura lista para tu proyecto)

### `src/main/java/.../LeftRotation.java`

```java
package menwic.ayd2.ex1;

import java.util.Arrays;

public final class LeftRotation {

    private LeftRotation() {}

    // Devuelve un NUEVO arreglo rotado (no muta el original)
    public static int[] rotateLeft(int[] arr, int d) {
        if (arr == null) {
            throw new IllegalArgumentException("arr no puede ser null");
        }
        int n = arr.length;
        if (n == 0) return new int[0];

        int shift = d % n;
        if (shift < 0) shift = (shift + n) % n; // por si te pasan negativos
        if (shift == 0) return Arrays.copyOf(arr, n);

        int[] out = Arrays.copyOf(arr, n);
        reverse(out, 0, shift - 1);
        reverse(out, shift, n - 1);
        reverse(out, 0, n - 1);
        return out;
    }

    private static void reverse(int[] a, int i, int j) {
        while (i < j) {
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
            i++;
            j--;
        }
    }
}
```

### `src/test/java/.../LeftRotationTest.java`

```java
package menwic.ayd2.ex1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeftRotationTest {

    @Test
    void rotatesLeftBy2() {
        assertArrayEquals(new int[]{3,4,5,1,2},
                LeftRotation.rotateLeft(new int[]{1,2,3,4,5}, 2));
    }

    @Test
    void dZeroReturnsSameContent() {
        assertArrayEquals(new int[]{1,2,3},
                LeftRotation.rotateLeft(new int[]{1,2,3}, 0));
    }

    @Test
    void dEqualsNReturnsSameContent() {
        assertArrayEquals(new int[]{1,2,3},
                LeftRotation.rotateLeft(new int[]{1,2,3}, 3));
    }

    @Test
    void dGreaterThanNIsNormalized() {
        assertArrayEquals(new int[]{3,4,5,1,2},
                LeftRotation.rotateLeft(new int[]{1,2,3,4,5}, 7));
    }

    @Test
    void emptyArrayStaysEmpty() {
        assertArrayEquals(new int[]{},
                LeftRotation.rotateLeft(new int[]{}, 10));
    }

    @Test
    void singleElementNoChange() {
        assertArrayEquals(new int[]{9},
                LeftRotation.rotateLeft(new int[]{9}, 100));
    }

    @Test
    void nullArrayThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> LeftRotation.rotateLeft(null, 1));
    }
}
```

Ejecutás:

```bash
mvn clean test
```

---

## Siguiente paso (ya, sin cambiar rumbo)

Decime: **¿la función debe mutar el arreglo original o devolver uno nuevo?**

Si el enunciado “devuelve el resultado”, lo normal (y más seguro) es **devolver uno nuevo** (como te lo dejé). Si tu profe lo quiere *in-place*, lo adapto en 2 líneas.


