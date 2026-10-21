import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Ordenadores {

    /**
     * Ordena um array usando o algoritmo de ordenação por inserção (Insertion Sort).
     * @param a o array a ser ordenado
     * @param <T> o tipo de elementos do array, que deve implementar a interface Comparable
     */
    public static <T extends Comparable<T>>
    void insertionSort(T[] a) {
        int n = a.length;

        for (int i = 1; i < n; i++) {
            T chave = a[i];
            int j = i - 1;
            while (j >= 0 && a[j].compareTo(chave)  > 0) {
                a[j+1] = a[j];
                j--;
            }
            a[j+1]=chave;
        }
    }

    /**
     * Ordena um array usando o algoritmo de ordenação por seleção (Selection Sort).
     * @param a o array a ser ordenado
     * @param <T> o tipo de elementos do array, que deve implementar a interface Comparable
     */
    public static <T extends Comparable<T>>
    void selectionSort(T[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j].compareTo(a[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                T temp = a[i];
                a[i] = a[minIndex];
                a[minIndex] = temp;
            }
        }
    }


    /**
     * Ordena um array usando o algoritmo de ordenação por bolha (Bubble Sort).
     * @param a o array a ser ordenado
     * @param <T> o tipo de elementos do array, que deve implementar a interface Comparable
     */

    public static <T extends Comparable<T>>
    void bubbleSort(T[] a) {
        int length = a.length;

        for (int i = 0; i < length - 1; i++) {
            boolean trocou = false;

            for (int j = length - 1; j > i; j--) {
                if (a[j].compareTo(a[j - 1]) < 0) {
                    T tmp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = tmp;

                    trocou = true;
                }
            }

            if (!trocou) {
                return;
            }
        }
    }


    /**
     * Ordena um array usando o algoritmo de ordenação por heap (Heap Sort).
     * @param a o array a ser ordenado
     * @param <T> o tipo de elementos do array, que deve implementar a interface Comparable
     */
    public static <T extends Comparable<T>>
    void heapSort(T[] a) {
        int n = a.length;
        buildMaxHeap(a,n);

        for (int i = n -1; i > 0; i--) {
            T tmp = a[i];
            a[i] = a[0];
            a[0] = tmp;
            maxHeapify(a,i,0);
        }
    }

    private static <T extends Comparable<T>>
    void maxHeapify(T[] a, int n, int i) {
        int maior = i;
        int esquerdo = 2*i+1;
        int direito = 2*i + 2;

        if (esquerdo < n && a[esquerdo] .compareTo(a[maior]) > 0) {
            maior = esquerdo;
        }
        if (direito < n && a[direito].compareTo(a[maior]) > 0) {
            maior = direito;
        }
        if (maior != i) {
            T tmp = a[i];
            a[i] = a[maior];
            a[maior] = tmp;
            maxHeapify(a,n,maior);
        }
    }
    public static <T extends Comparable<T>>
    void buildMaxHeap(T[] a, int n) {
        for (int i = n/2 -1; i >= 0; i--) {
            maxHeapify(a,n,i);
        }
    }

    /**
     * Ordena um array usando o algoritmo de ordenação rápida (Quick Sort).
     * @param a o array a ser ordenado
     * @param <T> o tipo de elementos do array, que deve implementar a interface Comparable
     */
    public static <T extends Comparable<T>> void quickSort(T[] a) {
        if (a != null && a.length > 1) {
            quickSort(a, 0, a.length - 1);
        }
    }

    // 2. Método PRIVADO recursivo (3 parâmetros)
    private static <T extends Comparable<T>> void quickSort(T[] a, int p, int r) {
        if (p < r) {
            int q = partition(a, p, r);
            quickSort(a, p, q - 1);
            quickSort(a, q + 1, r);
        }
    }

    private static <T extends Comparable<T>>
    int partition(T[] a, int p, int r) {
        T pivo = a[r];
        int i = p-1;

        for (int j = p; j < r; j++) {
            if (a[j].compareTo(pivo) <= 0) {
                i++;
                T tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
            }
        }
        T tmp = a[i+1];
        a[i+1] = a[r];
        a[r] = tmp;
        return i+1;
    }

    /**
     * Ordena um array de inteiros usando o algoritmo de ordenação por base (Radix Sort).
     * @param a o array a ser ordenado
     */
    public static void radixSort(int[] a) {
        int n = a.length;
        int maior = encontrarMaior(a);

        for (int exp = 1; maior / exp > 0; exp *= 10) {
            countingSortPorDigito(a, n, exp);
        }
    }

    private static void countingSortPorDigito(int[] a, int n, int exp) {
        int[] output = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++) {
            int digito = (a[i] / exp) % 10;
            count[digito]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int digito = (a[i] / exp) % 10;
            output[count[digito] - 1] = a[i];
            count[digito]--;
        }

        for (int i = 0; i < n; i++) {
            a[i] = output[i];
        }
    }

    private static int encontrarMaior(int[] a) {
        int maior = a[0];

        for (int i = 1; i < a.length; i++) {
            if (a[i] > maior) {
                maior = a[i];
            }
        }

        return maior;
    }

    /**
     * Ordena um array de inteiros usando o algoritmo de ordenação por balde (Bucket Sort).
     * @param a o array a ser ordenado
     */
    @SuppressWarnings("unchecked")
    public static void bucketSort(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }

        int n = a.length;
        int maior = encontrarMaior(a);
        int menor = encontrarMenor(a);

        if (maior == menor) {
            return;
        }

        int intervalo = (maior - menor) / n + 1;

        List<Integer>[] baldes = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            baldes[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            int indiceBalde = (a[i] - menor) / intervalo;
            baldes[indiceBalde].add(a[i]);
        }

        for (int i = 0; i < n; i++) {
            Collections.sort(baldes[i]);
        }

        int index = 0;

        for (int i = 0; i < n; i++) {
            for (Integer num : baldes[i]) {
                a[index++] = num;
            }
        }
    }

    private static int encontrarMenor(int[] a) {
        int menor = a[0];

        for (int i = 1; i < a.length; i++) {
            if (a[i] < menor) {
                menor = a[i];
            }
        }

        return menor;
    }

    /**
     * Ordena um array de inteiros usando o algoritmo de ordenação por contagem (Counting Sort).
     * @param a o array a ser ordenado
     */
    public static void countingSort(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }

        int maior = a[0];
        int menor = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > maior) maior = a[i];
            if (a[i] < menor) menor = a[i];
        }

        int amplitude = maior - menor + 1;
        int[] count = new int[amplitude];
        int[] output = new int[a.length];

        for (int i = 0; i < a.length; i++) {
            count[a[i] - menor]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        for (int i = a.length - 1; i >= 0; i--) {
            int valor = a[i];
            output[count[valor - menor] - 1] = valor;
            count[valor - menor]--;
        }

        for (int i = 0; i < a.length; i++) {
            a[i] = output[i];
        }
    }
    /**
     * Ordena um array usando o algoritmo de ordenação por fusão (Merge Sort).
     * @param a o array a ser ordenado
     * @param <T> o tipo de elementos do array, que deve implementar a interface Comparable
     */
    public static <T extends Comparable<T>> void mergeSort(T[] a) {
        T[] aux = a.clone();
        mergeSort(a, aux, 0, a.length - 1);
    }

    private static <T extends Comparable<T>> void mergeSort(T[] a, T[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(a, aux, lo, mid);
        mergeSort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    private static <T extends Comparable<T>> void merge(T[] a, T[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (aux[j].compareTo(aux[i]) < 0) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    /**
     * Ordena um array usando o algoritmo de ordenação Shell Sort.
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<T>> void shellSort(T[] a) {
        int n = a.length;
        int h = 1;

        // Sequência de Knuth: 1, 4, 13, 40, 121...
        while (h < n / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            for (int i = h; i < n; i++) {
                T chave = a[i];
                int j = i;
                while (j >= h && a[j - h].compareTo(chave) > 0) {
                    a[j] = a[j - h];
                    j -= h;
                }
                a[j] = chave;
            }
            h /= 3;
        }
    }
}
