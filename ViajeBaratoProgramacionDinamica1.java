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
 */

package Ejercicios;

public class ViajeBaratoProgramacionDinamica1 {
        public static int[][] calcularCostoMinimo(int[][] T) {
            int n = T.length;
            int[][] C = new int[n][n];

            // Inicializar C con los valores de T (solo donde T[i][j] tiene datos válidos)
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        C[i][j] = 0;
                    } else if (i < j) {
                        C[i][j] = T[i][j]; // copia el costo directo
                    } else {
                        C[i][j] = Integer.MAX_VALUE; // no se puede ir hacia atrás
                    }
                }
            }

            // Aplicar lógica para encontrar caminos más baratos haciendo escalas
            for (int i = n - 1; i >= 0; i--) {
                for (int j = i + 1; j < n; j++) {
                    for (int k = i + 1; k < j; k++) {
                        if (C[i][k] != Integer.MAX_VALUE && C[k][j] != Integer.MAX_VALUE) {
                            C[i][j] = Math.min(C[i][j], C[i][k] + C[k][j]);
                        }
                    }
                }
            }

            return C;
        }

        public static void main(String[] args) {
            int[][] T = {
                    {0, 2, 8, 100},
                    {0, 0, 6, 50},
                    {0, 0, 0, 3},
                    {0, 0, 0, 0}
            };

            int[][] C = calcularCostoMinimo(T);

            // Imprimir matriz resultante
            for (int i = 0; i < C.length; i++) {
                for (int j = 0; j < C[i].length; j++) {
                    if (C[i][j] == Integer.MAX_VALUE) {
                        System.out.print("∞\t");
                    } else {
                        System.out.print(C[i][j] + "\t");
                    }
                }
                System.out.println();
            }

            System.out.println("Costo mínimo de 0 a 3: " + C[0][3]); // Esperado: 2 + 6 + 3 = 11
        }
    }
