import java.util.Random;

public class QuickSelect {

    // Método que implementa el algoritmo QuickSelect
    public static int quickselect(int[] arr, int k) {
        // Convertimos k de 1-based a 0-based
        return quickselectHelper(arr, 0, arr.length - 1, k - 1);
    }
// 
    // Método auxiliar recursivo para realizar la búsqueda
    private static int quickselectHelper(int[] arr, int left, int right, int k) {
        if (left == right) {
            return arr[left];
        }

        // Seleccionamos un pivote aleatorio
        Random rand = new Random();
        int pivotIndex = left + rand.nextInt(right - left + 1);
        int pivot = arr[pivotIndex];

        // Particionamos el arreglo
        pivotIndex = partition(arr, left, right, pivot);

        // El índice del pivote es el índice de la mediana
        if (k == pivotIndex) {
            return arr[k];
        } else if (k < pivotIndex) {
            return quickselectHelper(arr, left, pivotIndex - 1, k);
        } else {
            return quickselectHelper(arr, pivotIndex + 1, right, k);
        }
    }

    // Método para particionar el arreglo
    private static int partition(int[] arr, int left, int right, int pivot) {
        int leftIndex = left;
        int rightIndex = right;

        // Colocamos el pivote al final del arreglo
        while (leftIndex <= rightIndex) {
            // Mover leftIndex hacia la derecha mientras arr[leftIndex] < pivot
            while (arr[leftIndex] < pivot) {
                leftIndex++;
            }
            // Mover rightIndex hacia la izquierda mientras arr[rightIndex] > pivot
            while (arr[rightIndex] > pivot) {
                rightIndex--;
            }
            // Si leftIndex es menor o igual que rightIndex, intercambiamos
            if (leftIndex <= rightIndex) {
                swap(arr, leftIndex, rightIndex);
                leftIndex++;
                rightIndex--;
            }
        }

        // Devolvemos la posición del pivote
        return leftIndex;
    }

    // Método para intercambiar dos elementos en el arreglo
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Método principal para probar el algoritmo
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 4, 3, 20, 15};
        int k = 4;
        System.out.println("El " + k + "to elemento más pequeño es: " + quickselect(arr, k));
    }
}

