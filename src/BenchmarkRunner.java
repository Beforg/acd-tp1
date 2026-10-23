import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.function.Consumer;

/**
 * Executa o benchmark dos algoritmos e grava resultados.csv.
 */
public class BenchmarkRunner {

    private static final int REPETICOES = 10;
    private static final int WARMUP_RODAS = 3;
    private static final int[] TAMANHOS = {100, 1000, 10000, 100000, 1000000};
    private static final String[] CONFIGURACOES =
            {"Aleatorio", "Ordenado", "Invertido", "Repetidos"};
    private static final String[] TIPOS_GENERICOS = {"Integer", "String", "Pessoa"};

    public static void main(String[] args) {
        System.out.println("== BENCHMARK ==");
        System.out.println("Executando warmup para JIT...");
        executarWarmup();
        try (PrintWriter writer = new PrintWriter(new FileWriter("resultados.csv"))) {
            System.out.println("Iniciando execuções do benchmark...");
            writer.println("Algoritmo,TipoDado,Tamanho,Configuracao,Execucao,TempoMs");
            for (int tamanho : TAMANHOS) {
                System.out.println("Executando para tamanho: " + tamanho);
                for (String configuracao : CONFIGURACOES) {
                    System.out.println("Executando para configuração: " + configuracao);
                    executarGenericos(writer, tamanho, configuracao);
                    executarPrimitivos(writer, tamanho, configuracao);
                }
            }
            System.out.println("Todas as execuções concluídas.");
        } catch (IOException e) {
            throw new IllegalStateException("Não foi possível gravar resultados.csv", e);
        }
        System.out.println("Benchmark concluído. Resultados gravados em resultados.csv");
    }

    private static void executarGenericos(PrintWriter writer, int tamanho, String configuracao) {
        for (String tipo : TIPOS_GENERICOS) {
            Object base = obterBaseGenerica(tipo, configuracao, tamanho);
            executarGenerico(writer, "QuickSort", tipo, tamanho, configuracao, base, Ordenadores::quickSort);
            executarGenerico(writer, "MergeSort", tipo, tamanho, configuracao, base, Ordenadores::mergeSort);
            executarGenerico(writer, "HeapSort", tipo, tamanho, configuracao, base, Ordenadores::heapSort);
            executarGenerico(writer, "ShellSort", tipo, tamanho, configuracao, base, Ordenadores::shellSort);
            if (tamanho <= 100000) {
                executarGenerico(writer, "BubbleSort", tipo, tamanho, configuracao, base, Ordenadores::bubbleSort);
                executarGenerico(writer, "SelectionSort", tipo, tamanho, configuracao, base, Ordenadores::selectionSort);
                executarGenerico(writer, "InsertionSort", tipo, tamanho, configuracao, base, Ordenadores::insertionSort);
            }
        }
    }

    private static void executarPrimitivos(PrintWriter writer, int tamanho, String configuracao) {
        int[] base = obterBasePrimitiva(configuracao, tamanho);
        executarPrimitivo(writer, "RadixSort", tamanho, configuracao, base, Ordenadores::radixSort);
        executarPrimitivo(writer, "CountingSort", tamanho, configuracao, base, Ordenadores::countingSort);
        executarPrimitivo(writer, "BucketSort", tamanho, configuracao, base, Ordenadores::bucketSort);
    }

    private static <T extends Comparable<T>> void executarGenerico(
            PrintWriter writer, String algoritmo, String tipo, int tamanho,
            String configuracao, Object base, Consumer<T[]> ordenador) {
        for (int execucao = 1; execucao <= REPETICOES; execucao++) {
            @SuppressWarnings("unchecked")
            T[] copia = ((T[]) base).clone();
            long inicio = System.nanoTime();
            ordenador.accept(copia);
            double tempoMs = (System.nanoTime() - inicio) / 1_000_000.0;
            escrever(writer, algoritmo, tipo, tamanho, configuracao, execucao, tempoMs);
        }
    }

    private static void executarPrimitivo(PrintWriter writer, String algoritmo, int tamanho,
                                          String configuracao, int[] base,
                                          Consumer<int[]> ordenador) {
        for (int execucao = 1; execucao <= REPETICOES; execucao++) {
            int[] copia = base.clone();
            long inicio = System.nanoTime();
            ordenador.accept(copia);
            double tempoMs = (System.nanoTime() - inicio) / 1_000_000.0;
            escrever(writer, algoritmo, "int[]", tamanho, configuracao, execucao, tempoMs);
        }
    }

    private static void escrever(PrintWriter writer, String algoritmo, String tipo, int tamanho,
                                 String configuracao, int execucao, double tempoMs) {
        writer.printf("%s,%s,%d,%s,%d,%.4f%n",
                algoritmo, tipo, tamanho, configuracao, execucao, tempoMs);
    }

    private static void executarWarmup() {
        for (int rodada = 0; rodada < WARMUP_RODAS; rodada++) {
            System.out.println("Warmup rodada " + (rodada + 1) + " de " + WARMUP_RODAS);
            Integer[] generico = GeradorDeEntradas.gerarAleatorioGenerico(128);
            int[] primitivo = GeradorDeEntradas.gerarAleatorioPrimitivo(128);
            Ordenadores.quickSort(generico.clone());
            Ordenadores.mergeSort(generico.clone());
            Ordenadores.heapSort(generico.clone());
            Ordenadores.shellSort(generico.clone());
            Ordenadores.bubbleSort(generico.clone());
            Ordenadores.selectionSort(generico.clone());
            Ordenadores.insertionSort(generico.clone());
            Ordenadores.radixSort(primitivo.clone());
            Ordenadores.countingSort(primitivo.clone());
            Ordenadores.bucketSort(primitivo.clone());
        }
    }

    private static Object obterBaseGenerica(String tipo, String configuracao, int tamanho) {
        switch (tipo) {
            case "String":
                return gerarString(configuracao, tamanho);
            case "Pessoa":
                return gerarPessoa(configuracao, tamanho);
            default:
                return gerarInteger(configuracao, tamanho);
        }
    }

    private static Integer[] gerarInteger(String configuracao, int tamanho) {
        switch (configuracao) {
            case "Ordenado": return GeradorDeEntradas.gerarOrdenadoGenerico(tamanho);
            case "Invertido": return GeradorDeEntradas.gerarInvertidoGenerico(tamanho);
            case "Repetidos": return GeradorDeEntradas.gerarRepetidosGenerico(tamanho);
            default: return GeradorDeEntradas.gerarAleatorioGenerico(tamanho);
        }
    }

    private static String[] gerarString(String configuracao, int tamanho) {
        switch (configuracao) {
            case "Ordenado": return GeradorDeEntradas.gerarOrdenadoString(tamanho);
            case "Invertido": return GeradorDeEntradas.gerarInvertidoString(tamanho);
            case "Repetidos": return GeradorDeEntradas.gerarRepetidosString(tamanho);
            default: return GeradorDeEntradas.gerarAleatorioString(tamanho);
        }
    }

    private static Pessoa[] gerarPessoa(String configuracao, int tamanho) {
        switch (configuracao) {
            case "Ordenado": return GeradorDeEntradas.gerarOrdenadoPessoa(tamanho);
            case "Invertido": return GeradorDeEntradas.gerarInvertidoPessoa(tamanho);
            case "Repetidos": return GeradorDeEntradas.gerarRepetidosPessoa(tamanho);
            default: return GeradorDeEntradas.gerarAleatorioPessoa(tamanho);
        }
    }

    private static int[] obterBasePrimitiva(String configuracao, int tamanho) {
        switch (configuracao) {
            case "Ordenado": return GeradorDeEntradas.gerarOrdenadoPrimitivo(tamanho);
            case "Invertido": return GeradorDeEntradas.gerarInvertidoPrimitivo(tamanho);
            case "Repetidos": return GeradorDeEntradas.gerarRepetidosPrimitivo(tamanho);
            default: return GeradorDeEntradas.gerarAleatorioPrimitivo(tamanho);
        }
    }
}
