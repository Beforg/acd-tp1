import java.util.Random;

/**
 * Gera entradas determinísticas para os cenários do benchmark.
 */
public final class GeradorDeEntradas {

    private static final Random random = new Random(42);

    private GeradorDeEntradas() {
    }

    public static Integer[] gerarAleatorioGenerico(int n) {
        Integer[] valores = new Integer[n];
        for (int i = 0; i < n; i++) {
            valores[i] = random.nextInt(limiteAleatorio(n));
        }
        return valores;
    }

    public static Integer[] gerarOrdenadoGenerico(int n) {
        Integer[] valores = new Integer[n];
        for (int i = 0; i < n; i++) {
            valores[i] = i;
        }
        return valores;
    }

    public static Integer[] gerarInvertidoGenerico(int n) {
        Integer[] valores = new Integer[n];
        for (int i = 0; i < n; i++) {
            valores[i] = n - 1 - i;
        }
        return valores;
    }

    public static Integer[] gerarRepetidosGenerico(int n) {
        Integer[] valores = new Integer[n];
        for (int i = 0; i < n; i++) {
            valores[i] = random.nextInt(10);
        }
        return valores;
    }

    public static String[] gerarAleatorioString(int n) {
        String[] valores = new String[n];
        for (int i = 0; i < n; i++) {
            valores[i] = formatar(random.nextInt(limiteAleatorio(n)));
        }
        return valores;
    }

    public static String[] gerarOrdenadoString(int n) {
        String[] valores = new String[n];
        for (int i = 0; i < n; i++) {
            valores[i] = formatar(i);
        }
        return valores;
    }

    public static String[] gerarInvertidoString(int n) {
        String[] valores = new String[n];
        for (int i = 0; i < n; i++) {
            valores[i] = formatar(n - 1 - i);
        }
        return valores;
    }

    public static String[] gerarRepetidosString(int n) {
        String[] valores = new String[n];
        for (int i = 0; i < n; i++) {
            valores[i] = formatar(random.nextInt(10));
        }
        return valores;
    }

    public static Pessoa[] gerarAleatorioPessoa(int n) {
        Pessoa[] valores = new Pessoa[n];
        for (int i = 0; i < n; i++) {
            valores[i] = criarPessoa(random.nextInt(limiteAleatorio(n)), i);
        }
        return valores;
    }

    public static Pessoa[] gerarOrdenadoPessoa(int n) {
        Pessoa[] valores = new Pessoa[n];
        for (int i = 0; i < n; i++) {
            valores[i] = criarPessoa(i, i);
        }
        return valores;
    }

    public static Pessoa[] gerarInvertidoPessoa(int n) {
        Pessoa[] valores = new Pessoa[n];
        for (int i = 0; i < n; i++) {
            valores[i] = criarPessoa(n - 1 - i, i);
        }
        return valores;
    }

    public static Pessoa[] gerarRepetidosPessoa(int n) {
        Pessoa[] valores = new Pessoa[n];
        for (int i = 0; i < n; i++) {
            valores[i] = criarPessoa(random.nextInt(10), i);
        }
        return valores;
    }

    public static int[] gerarAleatorioPrimitivo(int n) {
        int[] valores = new int[n];
        for (int i = 0; i < n; i++) {
            valores[i] = random.nextInt(limiteAleatorio(n));
        }
        return valores;
    }

    public static int[] gerarOrdenadoPrimitivo(int n) {
        int[] valores = new int[n];
        for (int i = 0; i < n; i++) {
            valores[i] = i;
        }
        return valores;
    }

    public static int[] gerarInvertidoPrimitivo(int n) {
        int[] valores = new int[n];
        for (int i = 0; i < n; i++) {
            valores[i] = n - 1 - i;
        }
        return valores;
    }

    public static int[] gerarRepetidosPrimitivo(int n) {
        int[] valores = new int[n];
        for (int i = 0; i < n; i++) {
            valores[i] = random.nextInt(10);
        }
        return valores;
    }

    private static int limiteAleatorio(int n) {
        return Math.max(1, n * 10);
    }

    private static String formatar(int valor) {
        return String.format("STR_%08d", valor);
    }

    private static Pessoa criarPessoa(int id, int indice) {
        return new Pessoa(id, "Pessoa_" + indice, 18 + indice % 53,
                1500.0 + (indice % 1000) * 25.0);
    }
}
