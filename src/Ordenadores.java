import java.util.Arrays;

public class Ordenadores {

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

    public static <T extends Comparable<T>>
    void selectionSort(T[] a) {
        for (int i = 0; i < a.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j].compareTo(a[minIndex]) < 0) {
                    minIndex = j;
                }
                if (minIndex != i) {
                    T temp = a[i];
                    a[i] = a[minIndex];
                    a[minIndex] = temp;
                }

            }
        }
    }


    public static <T extends Comparable<T>>
    void bubbllesort(T[] a) {
        int lenght = a.length;
        boolean trocou = false;
        for (int i = 0; i < lenght -1 ; i++) {
            System.out.println(i);
            for (int j = lenght; j > i + 1; j--) {
                if (a[j - 1].compareTo(a[j - 2]) < 0) {
                    trocou = true;
                    T tmp = a[j - 1];
                    a[j - 1] = a[j - 2];
                    a[j - 2] = tmp;
                }
                if (!trocou) {
                    System.out.println("Não trocou! interrompendo execução");
                    return;
                }
            }
        }

    }
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

    private static <T extends Comparable<T>>
    void quickSort(T[] a, int p, int r) {
        if (p < r) {
            int q = partition(a,p,r);
            quickSort(a,p,q-1);
            quickSort(a,q+1, r);
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
    public static void main(String[] args) {
        Integer[] numeros = {3,1,4,6,2,11,9,5,8};
        Integer[] worstCase = {1,2,3,4,5,6,7,8,9};
        System.out.println("Números antes da ordenação: " + Arrays.toString(numeros));
        Ordenadores.quickSort(numeros, 0, 8);
        System.out.println("Números após a ordenação: " + Arrays.toString(numeros));
        System.out.println("Números antes da ordenação: " + Arrays.toString(worstCase));
        Ordenadores.heapSort(worstCase);
        System.out.println("Números após a ordenação: " + Arrays.toString(worstCase));

        String[] nomes = {"Ana", "Debora", "Bruno", "Joao", "Carlos"};
        Ordenadores.insertionSort(nomes);
        System.out.println("Nomes ordenados:" + Arrays.toString(nomes));
    }

}
