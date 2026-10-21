import java.util.Random;

public class GeradorDeEntradas {

    private static final Random random = new Random(42);

    // Vetor aleatório (Integer[])
    public static Integer[] gerarAleatorioGenerico(int n) {
        Integer[] arr = new Integer[n];

        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(n * 10);
        }

        return arr;
    }

    // Vetor já ordenado (crescente)
    public static Integer[] gerarOrdenadoGenerico(int n) {
        Integer[] arr = new Integer[n];

        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }

        return arr;
    }

    // Vetor invertido (decrescente)
    public static Integer[] gerarInvertidoGenerico(int n) {
        Integer[] arr = new Integer[n];

        for (int i = 0; i < n; i++) {
            arr[i] = n - i;
        }

        return arr;
    }

    // Vetor com elementos repetidos
    public static Integer[] gerarRepetidosGenerico(int n) {
        Integer[] arr = new Integer[n];

        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(10); // Números de 0 a 9
        }

        return arr;
    }

    // Algoritmos de tempo linear ===============================

    // Vetor aleatório (int[])
    public static int[] gerarAleatorioPrimitivo(int n) {
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(n * 10);
        }

        return arr;
    }

    // Vetor já ordenado (crescente)
    public static int[] gerarOrdenadoPrimitivo(int n) {
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }

        return arr;
    }

    // Vetor invertido (decrescente)
    public static int[] gerarInvertidoPrimitivo(int n) {
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = n - i;
        }

        return arr;
    }

    // Vetor com elementos repetidos
    public static int[] gerarRepetidosPrimitivo(int n) {
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(10);
        }

        return arr;
    }
}
