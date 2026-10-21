import java.util.Arrays;

public class TestesCorretude {

    public static void main(String[] args) {
        System.out.println("=== INICIANDO TESTES DE CORRETUDE ===\n");

        // 1. Teste para Algoritmos Genéricos (Comparable[])
        testarAlgoritmosGenericos();

        // 2. Teste para Algoritmos de Tempo Linear (int[])
        testarAlgoritmosLinares();

        System.out.println("\n=== TODOS OS TESTES CONCLUÍDOS ===");
    }

    private static void testarAlgoritmosGenericos() {
        System.out.println("--- Testando Algoritmos Genéricos (<T extends Comparable<T>>) ---");

        Integer[][] cenarios = {
                {5, 2, 9, 1, 7, 3, 8, 4, 6},           // Aleatório
                {1, 2, 3, 4, 5, 6, 7, 8, 9},           // Já Ordenado
                {9, 8, 7, 6, 5, 4, 3, 2, 1},           // Invertido
                {4, 2, 4, 1, 2, 4, 3, 1, 4}            // Repetidos
        };

        String[] nomesCenarios = {"Aleatório", "Já Ordenado", "Invertido", "Repetidos"};

        for (int c = 0; c < cenarios.length; c++) {
            Integer[] original = cenarios[c];
            Integer[] esperado = original.clone();
            Arrays.sort(esperado); // Gabarito oficial do Java

            System.out.println("\nCenário: " + nomesCenarios[c]);

            // Valida Heapsort
            Integer[] arrHeap = original.clone();
            Ordenadores.heapSort(arrHeap);
            validar("Heapsort", arrHeap, esperado);

            // Valida Quicksort
            Integer[] arrQuick = original.clone();
            Ordenadores.quickSort(arrQuick);
            validar("Quicksort", arrQuick, esperado);

            // Valida Mergesort
            Integer[] arrMerge = original.clone();
            Ordenadores.mergeSort(arrMerge);
            validar("Mergesort", arrMerge, esperado);

             // Valida Shellsort
             Integer[] arrShell = original.clone();
             Ordenadores.shellSort(arrShell);
             validar("Shellsort", arrShell, esperado);

             // Valida Insertionsort
             Integer[] arrInsertion = original.clone();
             Ordenadores.insertionSort(arrInsertion);
             validar("Insertionsort", arrInsertion, esperado);

             // Valida Selectionsort
             Integer[] arrSelection = original.clone();
             Ordenadores.selectionSort(arrSelection);
             validar("Selectionsort", arrSelection, esperado);

             // Valida Bubblesort
             Integer[] arrBubble = original.clone();
             Ordenadores.bubbleSort(arrBubble);
             validar("Bubblesort", arrBubble, esperado);
        }
    }

    private static void testarAlgoritmosLinares() {
        System.out.println("\n--- Testando Algoritmos de Tempo Linear (int[]) ---");

        int[][] cenarios = {
                {170, 45, 75, 90, 802, 24, 2, 66},     // Aleatório
                {2, 24, 45, 66, 75, 90, 170, 802},     // Já Ordenado
                {802, 170, 90, 75, 66, 45, 24, 2},     // Invertido
                {45, 2, 45, 24, 2, 66, 45, 2}          // Repetidos
        };

        String[] nomesCenarios = {"Aleatório", "Já Ordenado", "Invertido", "Repetidos"};

        for (int c = 0; c < cenarios.length; c++) {
            int[] original = cenarios[c];
            int[] esperado = original.clone();
            Arrays.sort(esperado);

            System.out.println("\nCenário: " + nomesCenarios[c]);

            // Valida Radixsort
            int[] arrRadix = original.clone();
            Ordenadores.radixSort(arrRadix);
            validar("Radix Sort", arrRadix, esperado);

            // Valida Bucketsort
            int[] arrBucket = original.clone();
            Ordenadores.bucketSort(arrBucket);
            validar("Bucket Sort", arrBucket, esperado);

            // Valida Countingsort
             int[] arrCounting = original.clone();
             Ordenadores.countingSort(arrCounting);
             validar("Counting Sort", arrCounting, esperado);
        }
    }

    private static <T> void validar(String nomeAlgoritmo, T[] obtido, T[] esperado) {
        if (Arrays.equals(obtido, esperado)) {
            System.out.println("  [OK] " + nomeAlgoritmo);
        } else {
            System.err.println("  [FAIL] " + nomeAlgoritmo + " -> Obtido: " + Arrays.toString(obtido) + " | Esperado: " + Arrays.toString(esperado));
        }
    }

    private static void validar(String nomeAlgoritmo, int[] obtido, int[] esperado) {
        if (Arrays.equals(obtido, esperado)) {
            System.out.println("  [OK] " + nomeAlgoritmo);
        } else {
            System.err.println("  [FAIL] " + nomeAlgoritmo + " -> Obtido: " + Arrays.toString(obtido) + " | Esperado: " + Arrays.toString(esperado));
        }
    }
}

