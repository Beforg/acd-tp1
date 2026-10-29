#!/usr/bin/env python3
"""Processa resultados.csv, corrige o decimal com vírgula e gera resumo e gráficos.

Uso:
    python analise.py resultados.csv
Dependências:
    pip install pandas matplotlib
"""
import csv
import os
import sys
import pandas as pd
import matplotlib.pyplot as plt

entrada = sys.argv[1] if len(sys.argv) > 1 else "resultados.csv"
pasta_saida = "analise_benchmark"
pasta_graficos = os.path.join(pasta_saida, "graficos")
os.makedirs(pasta_graficos, exist_ok=True)

registros = []
with open(entrada, "r", newline="", encoding="utf-8-sig") as arquivo:
    leitor = csv.reader(arquivo)
    cabecalho = next(leitor)
    esperado = ["Algoritmo", "TipoDado", "Tamanho", "Configuracao", "Execucao", "TempoMs"]
    if cabecalho != esperado:
        raise ValueError(f"Cabeçalho inesperado: {cabecalho}")
    for numero_linha, linha in enumerate(leitor, start=2):
        # Corrige linhas como QuickSort,Integer,100,Aleatorio,1,0,0576
        if len(linha) == 7:
            linha = linha[:5] + [linha[5] + "." + linha[6]]
        if len(linha) != 6:
            raise ValueError(f"Linha {numero_linha} inválida: {linha}")
        alg, tipo, tamanho, config, execucao, tempo = linha
        registros.append({
            "Algoritmo": alg.strip(),
            "TipoDado": tipo.strip(),
            "Tamanho": int(tamanho),
            "Configuracao": config.strip(),
            "Execucao": int(execucao),
            "TempoMs": float(tempo.replace(",", "."))
        })

df = pd.DataFrame(registros)
if df.empty:
    raise ValueError("Não foram encontrados resultados.")
if (df["TempoMs"] < 0).any():
    raise ValueError("Há tempos negativos.")

df.to_csv(os.path.join(pasta_saida, "resultados_corrigidos.csv"),
          index=False, sep=";", decimal=",", encoding="utf-8-sig")

chaves = ["Algoritmo", "TipoDado", "Tamanho", "Configuracao"]
resumo = df.groupby(chaves, as_index=False).agg(
    Repeticoes=("TempoMs", "count"),
    MediaMs=("TempoMs", "mean"),
    MedianaMs=("TempoMs", "median"),
    DesvioPadraoMs=("TempoMs", "std"),
    MinimoMs=("TempoMs", "min"),
    MaximoMs=("TempoMs", "max")
)
resumo["DesvioPadraoMs"] = resumo["DesvioPadraoMs"].fillna(0)
resumo.to_csv(os.path.join(pasta_saida, "resumo.csv"),
              index=False, sep=";", decimal=",", encoding="utf-8-sig")

configs = ["Aleatorio", "Ordenado", "Invertido", "Repetidos"]
tipos = sorted(df["TipoDado"].unique())

for (alg, tipo), parte in resumo.groupby(["Algoritmo", "TipoDado"]):
    fig, ax = plt.subplots(figsize=(9, 5.5))
    for config in configs:
        serie = parte[parte["Configuracao"] == config].sort_values("Tamanho")
        if not serie.empty:
            ax.plot(serie["Tamanho"], serie["MediaMs"], marker="o", label=config)
    ax.set_xscale("log")
    if (parte["MediaMs"] > 0).all():
        ax.set_yscale("log")
    ax.set(title=f"{alg} — {tipo}: tempo médio por tamanho",
           xlabel="Tamanho da entrada (escala log)", ylabel="Tempo médio (ms)")
    ax.grid(True, which="both", alpha=0.25)
    ax.legend(title="Configuração")
    fig.tight_layout()
    nome = "".join(c if c.isalnum() or c in "-_" else "_" for c in f"individual_{alg}_{tipo}")
    fig.savefig(os.path.join(pasta_graficos, nome + ".png"), dpi=160)
    plt.close(fig)

for tipo in tipos:
    for config in configs:
        parte = resumo[(resumo["TipoDado"] == tipo) & (resumo["Configuracao"] == config)]
        if parte.empty:
            continue
        fig, ax = plt.subplots(figsize=(10, 6))
        for alg, serie in parte.groupby("Algoritmo"):
            serie = serie.sort_values("Tamanho")
            ax.plot(serie["Tamanho"], serie["MediaMs"], marker="o", label=alg)
        ax.set_xscale("log")
        if (parte["MediaMs"] > 0).all():
            ax.set_yscale("log")
        ax.set(title=f"Comparação — {tipo} — {config}",
               xlabel="Tamanho da entrada (escala log)", ylabel="Tempo médio (ms)")
        ax.grid(True, which="both", alpha=0.25)
        ax.legend(fontsize=8)
        fig.tight_layout()
        nome = "".join(c if c.isalnum() or c in "-_" else "_" for c in f"comparacao_{tipo}_{config}")
        fig.savefig(os.path.join(pasta_graficos, nome + ".png"), dpi=160)
        plt.close(fig)

print(f"Concluído. Resultados em: {pasta_saida}")
