import os
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

os.makedirs("data/graphics", exist_ok=True)

df = pd.read_csv("data/text/benchmark_results.csv")

sns.set_theme(style="whitegrid")

for operation in df["operation"].unique():
    plt.figure(figsize=(10, 6))

    subset = df[df["operation"] == operation]

    ax = sns.lineplot(
        data=subset,  # type: ignore
        x="len",
        y="averageTime",
        hue="structure",
        style="dataset",
        markers=True,
        dashes=True,
        linewidth=2.5,
        markersize=8,
    )

    plt.xscale("log")
    plt.yscale("log")

    plt.title(f"Desempenho da Operação: {operation}", fontsize=14, fontweight="bold")
    plt.xlabel("Tamanho da Entrada (n) [Escala Log]", fontsize=12)
    plt.ylabel("Tempo Médio (ms) [Escala Log]", fontsize=12)
    plt.legend(title="Estrutura / Dataset", bbox_to_anchor=(1.05, 1), loc="upper left")

    plt.tight_layout()

    output_path = f"data/graphics/grafico_{operation.lower()}.png"
    plt.savefig(output_path, dpi=300)
    print(f"Gráfico salvo em: {output_path}")
    plt.close()
