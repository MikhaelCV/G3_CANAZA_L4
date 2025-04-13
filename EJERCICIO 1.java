import java.util.*;

public class Subconjunto{

    public static void main(String[] args) {
        
        // Ingresamos los valores del arreglo
        int[] entrada1 = {5, 2, 4, 8, 10, 3, 14}; // Resultado esperado: true
        int[] entrada2 = {5, 4, 8, 10, 3, 5, 27}; // Resultado esperado: false
        int[] entrada3 = {5, 4, 8, 10, 3, 6, 27}; // Resultado esperado: false
        int[] entrada4 = {6, 2, 16, 5, 7, 10, 33}; // Resultado esperado: false
        int[] entrada5 = {6, 2, 16, 5, 3, 10, 33}; // Resultado esperado: false
        int[] entrada6 = {4, 2, 5, 1, 6, 13};
        
        System.out.println(puedeFormarSuma(entrada1)); 
        System.out.println(puedeFormarSuma(entrada2));
        System.out.println(puedeFormarSuma(entrada3)); 
        System.out.println(puedeFormarSuma(entrada4)); 
        System.out.println(puedeFormarSuma(entrada5)); 
        System.out.println(puedeFormarSuma(entrada6)); 
    }
    
    // Método que evalúa si es posible alcanzar la suma objetivo 
    public static boolean puedeFormarSuma(int[] entrada) {
        int n = entrada[0]; // cantidad de elementos del arreglo
        int[] arr = new int[n];
        System.arraycopy(entrada, 1, arr, 0, n);
        int objetivo = entrada[entrada.length - 1];
        
        // Listas para guardar números obligatorios y candidatos a ser usados
        List<Integer> obligatorios = new ArrayList<>();
        List<Integer> candidatos = new ArrayList<>();
    
        // Clasifica cada número del conjunto
        for (int i = 0; i < n; i++) {
            int actual = arr[i];
            
             // Si es potencia de dos, debe incluirse obligatoriamente
            if (esPotenciaDeDos(actual)) {
                obligatorios.add(actual); 
            }
            
            // Si es múltiplo de 5, solo es válido si el siguiente número no es impar
            else if (actual % 5 == 0) {
                if (i + 1 < n && arr[i + 1] % 2 != 0) {
                    continue; // Se descarta
                } 
                else {
                    candidatos.add(actual); // Es válido
                }
            }
            
            // Los demás se consideran candidatos
            else {
                candidatos.add(actual);
            }
        }
        
        // Se suma todo lo obligatorio
        int sumaObligatoria = obligatorios.stream().mapToInt(Integer::intValue).sum();
        
        // Se calcula lo que falta para alcanzar el objetivo
        int sumaRestante = objetivo - sumaObligatoria;
        
        // Si ya se pasó del objetivo, no es posible
        if (sumaRestante < 0) return false;
        return puedeSumar(candidatos, sumaRestante);
    }

    // Método auxiliar que inicia la búsqueda por backtracking
    private static boolean puedeSumar(List<Integer> lista, int objetivo) {
        return puedeSumarDesde(lista, objetivo, 0);
    }
    
     // Backtracking recursivo para intentar alcanzar el objetivo desde cierto índice
    private static boolean puedeSumarDesde(List<Integer> lista, int objetivo, int index) {
        
        // Si el objetivo es 0, se logró formar la suma
        if (objetivo == 0) return true;
        
        // Si se terminó la lista o el objetivo es negativo, no es posible
        if (index >= lista.size() || objetivo < 0) return false;

        // Incluir el número actual
        if (puedeSumarDesde(lista, objetivo - lista.get(index), index + 1)) return true;

        // Omite el número actual
        return puedeSumarDesde(lista, objetivo, index + 1);
    }
    
    // Método auxiliar para verificar si un número es potencia de 2
    private static boolean esPotenciaDeDos(int x) {
        return x > 0 && (x & (x - 1)) == 0;
    }
}
