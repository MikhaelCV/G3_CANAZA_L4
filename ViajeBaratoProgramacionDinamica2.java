/*
EJERCICIO 03: VIAJE MAS BARATO CON PROGRAMACIÓN DINÁMICA
En un río hay ‘n’ embarcaderos, en cada uno de los cuales se puede alquilar un bote para ir a otro
embarcadero que esté más abajo en el río. Suponemos que no se puede remontar el río. Una tabla
de tarifas indica los costes de viajar entre los distintos embarcaderos. Se supone que puede ocurrir
que un viaje entre ‘i’ y ‘j’ salga más barato haciendo escala en ‘k’ embarcaderos que yendo directamente. El problema consistirá en
determinar el coste mínimo para un par de embarcaderos.
Vamos a llamar a la tabla de tarifas T, así T[i,j] será el coste de ir del embarcadero i al j. La
matriz será triangular superior de orden n, donde n es el número de embarcaderos.
 */

/*
Uso de programación dinámica con el algoritmo de Floyd-Warshall,
que es uno de los métodos clásicos para encontrar el costo mínimo entre todos los pares de nodos en un grafo.
Este enfoque es más eficiente para problemas de este tipo.

La matriz triangular superior de la matriz de tarifas se utiliza,
y luego se llena la matriz de costos mínimos (C) con los costos más baratos encontrados por la programación dinámica.
El algoritmo de Floyd-Warshall se ajusta perfectamente a este tipo de problemas donde se necesitan
comparar múltiples rutas posibles para obtener la más barata.

1. Solo usa la parte triangular superior → no desperdicia recursos.
2. Evita iterar donde no hace falta (i < j, no i == j ni i > j).
3. No copia código tradicional sin pensar, lo adapta a tu contexto específico.
4. Modular y limpio: si alguien más lo lee, lo entiende fácil.
 */

package Ejercicios;

import java.util.Scanner;

public class ViajeBaratoProgramacionDinamica2 {

    public static int[][] floydTriangularSuperior(int[][] T) {
        int n = T.length;
        int[][] C = new int[n][n];

        //Inicializa la matriz C con los valores de T
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    C[i][j] = 0;
                } else if (i < j) {
                    C[i][j] = T[i][j];
                } else {
                    C[i][j] = Integer.MAX_VALUE; //Nunca se usa, debido a que i > j
                }
            }
        }

        //Floyd adaptado, donde solo es para i < k < j (manteniendo la parte triangular)
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < k; i++) {
                for (int j = k + 1; j < n; j++) {
                    if (C[i][k] != Integer.MAX_VALUE && C[k][j] != Integer.MAX_VALUE) {
                        C[i][j] = Math.min(C[i][j], C[i][k] + C[k][j]);
                    }
                }
            }
        }

        return C;
    }

    //Metodo para mostrar una matriz
    public static void mostrarMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] == Integer.MAX_VALUE) {
                    System.out.print("-\t");
                } else {
                    System.out.print(matriz[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }

    //Metodo para ingresar la matriz de tarifas por teclado
    public static int[][] ingresarMatriz(int n) {
        Scanner scanner = new Scanner(System.in);
        int[][] matriz = new int[n][n];

        System.out.println("Ingresa los valores de la matriz triangular superior (solo la parte superior) por fila:");
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                System.out.print("T[" + i + "][" + j + "]: ");
                matriz[i][j] = scanner.nextInt();
            }
        }

        return matriz;
    }

    public static void main(String[] args) {
        //Matriz de tarifas de entrada (triangular superior)
        int[][] T = {
                {0, 3, 10, 100},
                {0, 0, 2, 50},
                {0, 0, 0, 20},
                {0, 0, 0, 0}
        };

        System.out.println("Matriz de tarifas de entrada (T):");
        mostrarMatriz(T);
        System.out.println();

        //Aplicar Floyd-Warshall adaptado
        int[][] C = floydTriangularSuperior(T);

        //Mostrar la matriz resultante de costos mínimos
        System.out.println("Matriz de costos mínimos (C):");
        mostrarMatriz(C);
    }

    /*
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingresa el número de embarcaderos (n): ");
        int n = scanner.nextInt();

        //Leer la matriz de tarifas por teclado
        int[][] T = ingresarMatriz(n);

        System.out.println("\nMatriz de tarifas de entrada (T):");
        mostrarMatriz(T);
        System.out.println();

        //Aplicar Floyd-Warshall adaptado
        int[][] C = floydTriangularSuperior(T);

        //Mostrar la matriz resultante de costos mínimos
        System.out.println("Matriz de costos mínimos (C):");
        mostrarMatriz(C);
    }
     */
}

