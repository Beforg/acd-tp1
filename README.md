# TP1 — Algoritmos de Ordenação

Trabalho Prático 1 da disciplina de **Algoritmos e Classificação de Dados**, do curso de Engenharia de Computação da Universidade Federal do Pampa (UNIPAMPA), campus Bagé.

## Sobre o projeto

Este projeto implementa e avalia experimentalmente dez algoritmos de ordenação utilizando Java. O objetivo é analisar o desempenho dos métodos em diferentes condições de entrada e relacionar os tempos medidos com suas complexidades teóricas.

O experimento considera diferentes tamanhos de vetores, disposições iniciais dos elementos e tipos de dados, buscando compreender como esses fatores influenciam o desempenho dos algoritmos.

## Algoritmos implementados

### Algoritmos comparativos

- **Bubble Sort:** compara e troca elementos adjacentes fora de ordem.
- **Selection Sort:** seleciona o menor elemento da região não ordenada.
- **Insertion Sort:** insere cada elemento em sua posição na região ordenada.
- **Shell Sort:** utiliza intervalos decrescentes para realizar ordenações parciais.
- **Merge Sort:** divide o vetor recursivamente e intercala as partes ordenadas.
- **Quick Sort:** utiliza particionamento de três vias, separando elementos menores, iguais e maiores que o pivô.
- **Heap Sort:** utiliza uma estrutura de max-heap para ordenar os elementos.

### Algoritmos não comparativos

- **Counting Sort:** utiliza contagens de ocorrências para determinar as posições dos elementos.
- **Radix Sort:** ordena os números processando seus dígitos individualmente.
- **Bucket Sort:** distribui os elementos em baldes, ordena cada grupo e combina os resultados.

## Metodologia experimental

Os algoritmos foram avaliados considerando diferentes configurações:

- **Tamanhos de entrada:** de 100 até 1.000.000 de elementos, conforme o algoritmo e a configuração.
- **Disposição inicial:** vetores aleatórios, ordenados, invertidos e com valores repetidos.
- **Tipos de dados:** inteiros primitivos (`int[]`), objetos `Integer`, strings (`String`) e objetos de uma classe personalizada `Pessoa`.
- **Medições:** coleta de tempos de execução e análise estatística dos resultados.

O relatório registra **4.440 execuções válidas**. As análises consideram métricas como média, mediana, desvio-padrão e coeficiente de variação, além de gráficos comparativos.

## Resultados e análise

Os experimentos permitem investigar:

- O impacto do tamanho do vetor no tempo de execução.
- A influência da disposição inicial dos elementos.
- As diferenças de desempenho entre algoritmos de complexidade quadrática e métodos de crescimento assintótico inferior.
- O comportamento dos algoritmos não comparativos diante das características dos valores de entrada.
- A influência do tipo de dado e dos custos das operações de comparação.
- A variabilidade das medições experimentais.

Os resultados devem ser interpretados considerando as implementações avaliadas e as condições específicas dos experimentos, sem generalizar as conclusões para todos os cenários possíveis.

## Organização do projeto

O repositório reúne os componentes necessários ao desenvolvimento e à avaliação experimental dos algoritmos:

- Implementações dos dez algoritmos e seus métodos auxiliares.
- Testes de corretude.
- Código responsável pela execução dos experimentos.`(src)`
- Rotinas de coleta e registro dos tempos.
- Dados experimentais utilizados na análise (`src/dados`).
- Gráficos dos resultados `(src/dados/graficos)`
- Script para a análise estatística dos dados `(src/dados/analise.py)`



## Como executar

O projeto utiliza Java. Para executar os experimentos:

1. Clone o repositório.
2. Abra o projeto no ambiente de desenvolvimento utilizado ou configure o ambiente Java correspondente.
3. Execute os testes de corretude antes de iniciar as medições.
4. Execute a classe responsável pelos experimentos.
5. Consulte os arquivos de resultados gerados e o relatório técnico para interpretar as medições.

Os comandos específicos de compilação e execução dependem da estrutura e das classes de entrada presentes no repositório.

## Tecnologias utilizadas

- **Java** — implementação dos algoritmos e execução dos experimentos.
- **Git e GitHub** — versionamento e disponibilização do projeto.
- **LaTeX** — elaboração do relatório técnico.
- **Ferramentas de análise de dados e gráficos** — organização e visualização dos resultados experimentais.

## Entregáveis presentes no repositório

O projeto contempla os seguintes materiais:

- Código-fonte dos algoritmos.
- Testes de corretude.
- Código de execução e registro dos experimentos.
- Dados experimentais coletados.
- Relatório técnico com metodologia, resultados, gráficos, discussão e conclusões.


## Contexto acadêmico

**Instituição:** Universidade Federal do Pampa (UNIPAMPA)  
**Campus:** Bagé — RS  
**Curso:** Engenharia de Computação  
**Componente curricular:** Algoritmos e Classificação de Dados  
**Trabalho:** Trabalho Prático 1 — Algoritmos de Ordenação

---