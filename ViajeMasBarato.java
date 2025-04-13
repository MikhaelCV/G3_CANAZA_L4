/*
EJERCICIO 03: VIAJE MAS BARATO SIN PROGRAMACIÓN DINÁMICA
En un río hay ‘n’ embarcaderos, en cada uno de los cuales se puede alquilar un bote para ir a otro
embarcadero que esté más abajo en el río. Suponemos que no se puede remontar el río. Una tabla
de tarifas indica los costes de viajar entre los distintos embarcaderos. Se supone que puede ocurrir
que un viaje entre ‘i’ y ‘j’ salga más barato haciendo escala en ‘k’ embarcaderos que yendo directamente. El problema consistirá en
determinar el coste mínimo para un par de embarcaderos.
Vamos a llamar a la tabla de tarifas T, así T[i,j] será el coste de ir del embarcadero i al j. La
matriz será triangular superior de orden n, donde n es el número de embarcaderos.
 */

/*
Enfocada en resolver el problema de manera directa, con una búsqueda más simple.
 */

package Ejercicios;

public class ViajeMasBarato {
    public static int[][] calcularCostoMinimo(int[][] T) {
        int n = T.length;
        int[][] C = new int[n][n];

        for (int i = 0; i < n; i++) {
            C[i][i] = 0;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                C[i][j] = T[i][j];
                for (int k = i + 1; k < j; k++) {
                    C[i][j] = Math.min(C[i][j], T[i][k] + C[k][j]);
                }
            }
        }

        return C;
    }

    public static void main(String[] args) {
        int[][] T = {
                {0, 3, 10, 100},
                {0, 0, 2, 50},
                {0, 0, 0, 20},
                {0, 0, 0, 0}
        };

        int[][] C = calcularCostoMinimo(T);
        System.out.println("Costo mínimo de 0 a 3: " + C[0][3]); // 25
    }
}
