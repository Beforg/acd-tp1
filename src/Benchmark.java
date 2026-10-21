import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.function.Consumer;

public class Benchmark {

    private static final int REPETICOES = 10;
    private static final int WARMUP_RODADAS = 3;
    private static final int[] TAMANHOS = {100, 1000, 10000, 100000};
    private static final String[] CONFIGURACOES = {"Aleatorio", "Ordenado", "Invertido", "Repetidos"};

    public static void main(String[] args) {
        System.out.println("=== INICIANDO BENCHMARK COMPLETO ===");

        try (PrintWriter writer = new PrintWriter(new FileWriter("resultados.csv"))) {
            // Cabeçalho do CSV
            writer.println("Algoritmo,Tamanho,Configuracao,Execucao,Tempo (Ms)");

            // Inicializações e Warm-up da JVM
            System.out.println("Executando Warm-up da JVM...");
            executarWarmup();

            // Processamento dos testes para cada tamanho e configuração
            for (int n : TAMANHOS) {
                System.out.println("\n>>> Testando para Tamanho N = " + n);

                for (String config : CONFIGURACOES) {
                    System.out.println("  Configuração: " + config);

                    // Algoritmos (Generics)
                    Integer[] baseGenerica = obterBaseGenerica(config, n);

                    rodarEEscreverGenerico(writer, "BubbleSort", baseGenerica, Ordenadores::bubbleSort, n, config);
                    rodarEEscreverGenerico(writer, "SelectionSort", baseGenerica, Ordenadores::selectionSort, n, config);
                    rodarEEscreverGenerico(writer, "InsertionSort", baseGenerica, Ordenadores::insertionSort, n, config);
                    rodarEEscreverGenerico(writer, "ShellSort", baseGenerica, Ordenadores::shellSort, n, config);
                    rodarEEscreverGenerico(writer, "MergeSort", baseGenerica, Ordenadores::mergeSort, n, config);
                    rodarEEscreverGenerico(writer, "QuickSort", baseGenerica, Ordenadores::quickSort, n, config);
                    rodarEEscreverGenerico(writer, "HeapSort", baseGenerica, Ordenadores::heapSort, n, config);

                    // Algoritmos Lineares (Primitivos)
                    int[] basePrimitiva = obterBasePrimitiva(config, n);

                    rodarEEscreverPrimitivo(writer, "CountingSort", basePrimitiva, Ordenadores::countingSort, n, config);
                    rodarEEscreverPrimitivo(writer, "RadixSort", basePrimitiva, Ordenadores::radixSort, n, config);
                    rodarEEscreverPrimitivo(writer, "BucketSort", basePrimitiva, Ordenadores::bucketSort, n, config);
                }
            }

            System.out.println("\n=== BENCHMARK CONCLUÍDO COM SUCESSO! ===");
            System.out.println("Resultados salvos no arquivo 'resultados.csv'");

        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo CSV: " + e.getMessage());
        }
    }
    // Métodos auxiliares para execução e escrita dos resultados no CSV
    private static void rodarEEscreverGenerico(PrintWriter writer, String nome, Integer[] base, Consumer<Integer[]> algoritmo, int n, String config) {
        for (int rep = 1; rep <= REPETICOES; rep++) {
            Integer[] copia = base.clone();

            long inicio = System.nanoTime();
            algoritmo.accept(copia);
            long fim = System.nanoTime();

            double tempoMs = (fim - inicio) / 1_000_000.0;
            writer.printf("%s,%d,%s,%d,%.4f\n", nome, n, config, rep, tempoMs);
        }
    }

    private static void rodarEEscreverPrimitivo(PrintWriter writer, String nome, int[] base, Consumer<int[]> algoritmo, int n, String config) {
        for (int rep = 1; rep <= REPETICOES; rep++) {
            int[] copia = base.clone();

            long inicio = System.nanoTime();
            algoritmo.accept(copia);
            long fim = System.nanoTime();

            double tempoMs = (fim - inicio) / 1_000_000.0;
            writer.printf("%s,%d,%s,%d,%.4f\n", nome, n, config, rep, tempoMs);
        }
    }

    private static void executarWarmup() {
        for (int i = 0; i < WARMUP_RODADAS; i++) {
            Integer[] arr = GeradorDeEntradas.gerarAleatorioGenerico(1000);
            int[] arrPrimitivo = GeradorDeEntradas.gerarAleatorioPrimitivo(1000);

            Ordenadores.quickSort(arr.clone());
            Ordenadores.heapSort(arr.clone());
            Ordenadores.bubbleSort(arr.clone());
            Ordenadores.selectionSort(arr.clone());
            Ordenadores.insertionSort(arr.clone());
            Ordenadores.shellSort(arr.clone());
            Ordenadores.mergeSort(arr.clone());
            Ordenadores.countingSort(arrPrimitivo.clone());
            Ordenadores.radixSort(arrPrimitivo.clone());
            Ordenadores.bucketSort(arrPrimitivo.clone());
        }
    }

    private static Integer[] obterBaseGenerica(String config, int n) {
        switch (config) {
            case "Ordenado": return GeradorDeEntradas.gerarOrdenadoGenerico(n);
            case "Invertido": return GeradorDeEntradas.gerarInvertidoGenerico(n);
            case "Repetidos": return GeradorDeEntradas.gerarRepetidosGenerico(n);
            default: return GeradorDeEntradas.gerarAleatorioGenerico(n);
        }
    }

    private static int[] obterBasePrimitiva(String config, int n) {
        switch (config) {
            case "Ordenado": return GeradorDeEntradas.gerarOrdenadoPrimitivo(n);
            case "Invertido": return GeradorDeEntradas.gerarInvertidoPrimitivo(n);
            case "Repetidos": return GeradorDeEntradas.gerarRepetidosPrimitivo(n);
            default: return GeradorDeEntradas.gerarAleatorioPrimitivo(n);
        }
    }
}
