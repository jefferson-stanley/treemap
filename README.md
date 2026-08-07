# TreeMap

Este repositório contém a implementação de uma **TreeMap** e um material didático do mesmo com testes de unidade e de eficiência em comparativo com a estrutura de dados já nativa de **Java**.

# Introdução

O TreeMap é uma estrutura de dados que implementa a interface `Map` e usa uma **árvore rubro-negra** como estrutura interna. Diferente de um `HashMap`, o TreeMap mantém todas as chaves armazenadas em ordem crescente. Essa estrutura de dados é comumente usada em situações em que a ordenação dos dados é obrigatória ou são necessárias buscas por intervalos de valores.

# Objetivo

Produzir um material didático e prático completo sobre TreeMap com a implementação da estrutura em conjunto com testes de eficiência nas suas operações em comparação com a já implementada em **Java**(`java.util.TreeMap`).

# [Material Didático](data/text/treemap.md)

Material didático sobre o funcionamento da estrutura de dados TreeMap no padrão da disciplina de Estrutura de Dados e Algoritmos da UFCG (Universidade Federal de Campina Grande). Contendo uma explicação detalhada da estrutura sobre seu funcionamento, implementação e exemplos práticos de sua usabilidade.

# Estrutura do repositório
```text
treemap
├───data            #Dados gerais do projeto.
│   ├───graphics    #Gráficos gerados a partir do benchmark
│   └───text        #CSV de resultados e material didático em markdown
├───images          #Imagens utilizadas no markdown da entrega do projeto
├───scripts         #Scripts utilizados no projeto
├───src             #Diretório principal que contêm as implementações
    ├───main
    │   └───java
    │       ├───benchmark       #Classes de execução e medição do benchmark
    │       ├───map
    │       │   ├───impl        #Implementação da TreeMap
    │       │   └───interfaces  #Interfaces utilizadas na implementação do código
    │       ├───structures      #BST e árvore rubro-negra
    │       └───utils           #Geração de datasets para o benchmark
    └───test        #Diretório contendo os testes de unidade                  
        └───java
            ├───map
            │   ├───impl        
            │   └───interfaces
            └───structures
```
# Como rodar os testes
Para que os testes funcionem corretamente é necessário ter `Java 17+` e para gerar os gráficos(opcional) será necessário ter `Python 3` instalado em sua máquina.

Primeiro compile o repositório

**Linux/macOS**
```bash
javac -d out $(find src/main/java src/test/java -name "*.java")
```

**Windows (PowerShell)**
```powershell
javac -d out (Get-ChildItem -Recurse -Filter *.java src\main\java, src\test\java).FullName
``` 

## Testes

Os testes de unidade contêm asserções para todas as classes utilizadas na TreeMap

**Para executá-los utilize a flag -ea (para habilitar os asserts de Java):**

```bash
java -ea -cp out map.impl.TreeMapAsserts
java -ea -cp out map.interfaces.MapAsserts
java -ea -cp out structures.BinarySearchTreeAsserts
java -ea -cp out structures.RedBlackTreeAsserts
```
## Benchmark

**Para executar o Benchmark utilize o comando:**
```bash 
java -cp out benchmark.BenchmarkRunner
```

## Gráficos

**Os resultados do benchmark ficam armazenados em [data/text/benchmark_results.csv](data/text/benchmark_results.csv). Para gerar os gráficos basta executar os seguintes comandos na raiz do repositório:**
```bash
python3 -m venv .venv
source .venv/bin/activate
pip install pandas matplotlib seaborn
python3 scripts/plot.py
```


# Metodologia
### Testes de unidade
Os **testes de unidade** são feitos para cada camada da implementação da árvore, demonstrando a hierarquia de dependência da estrutura.

**BinarySearchTreeAsserts** - verifica as operações básicas de uma `árvore binária` como: inserção, remoção, tamanho e limpeza.  
**RedBlackTreeAsserts** - verifica as propriedades de uma `árvore rubro-negra` como: balanceamento, rotações, cor da raiz e a atualização de chaves presentes na árvore.  
**TreeMapAsserts** - verifica as propriedades da `TreeMap` com a implementação da interface `Map`.  
**MapAsserts** - verifica as operações de um `Map` como: inserção, busca, verificação da chave e valor presente no mapa, remoção, limpeza do mapa e as views da coleção.  

### Benchmark

O **benchmark** compara a `TreeMap` deste repositório com a de Java (`java.util.TreeMap`), medindo o tempo de execução das seguintes operações:

**INSERT** - inserção dos elementos presentes no dataset.  
**SEARCH** - busca dos elementos de uma estrutura já populada.  
**DELETE** - remoção dos elementos de uma estrutura já populada.  
**MIXED_WORKLOAD** - uma carga de trabalho mista com 70% de busca, 20% de inserção e 10% de remoção para ter uma visão mais prática do funcionamento das estruturas.

### Datasets

As operações são testadas com datasets gerados por `DataGenerator` (com uma seed fixa para reprodutibilidade dos testes) são eles:

`random` - valores aleatórios sem ordem.  
`sorted` - valores ordenados de forma crescente.  
`reverse` - valores ordenados de forma decrescente.  
`nearly_sorted` - valores quase ordenados de forma crescente com 10% dos valores em posições trocadas.  
`duplicates` - valores gerados em um intervalo pequeno, criando assim uma alta taxa de duplicação.

Cada operação é executada para os tamanhos de entrada: **100, 1000, 10000, 100000, 1000000**.

### Medição

São executadas 5 repetições de aquecimento (*warmup*), excluídas da medição final, para reduzir o efeito de otimização da JVM.  
Após isso, são executadas 30 repetições com seu tempo de execução cronometrado por `System.nanoTime()`. É calculado o tempo médio e o desvio padrão para cada tamanho de entrada.

# Benchmark
Abaixo está a amostra dos nossos experimentos com gráficos e tabelas registrando os dados coletados.

## Inserção:
![Gráfico de inserção](data/graphics/grafico_insert.png)

### Resultados do benchmark
* Dataset: *random*

|Tamanho | MyTreeMap(ms) | JavaTreeMap(ms) |
|---|---|---|
| 100 | 0.034483 | 0.054630 |
| 1000 | 0.101467 | 0.073243 |
| 10000 | 1.031837 | 0.950887 |
| 100000 | 18.358423 | 17.771543 |
| 1000000 | 694.900857 | 719.421973 |

* Dataset: *sorted*

|Tamanho | MyTreeMap(ms) | JavaTreeMap(ms) |
|---|---|---|
| 100 | 0.032423 | 0.019107 | 
| 1000 | 0.087563 | 0.119833 |
| 10000 | 0.552017 | 0.580303 |
| 100000 | 9.482713 | 8.480597 |
| 1000000 | 91.026483 | 95.285190 |

* Dataset: *reverse*

|Tamanho | MyTreeMap(ms) | JavaTreeMap(ms) |
|---|---|---|
| 100 | 0.019927 | 0.023147 |
| 1000 | 0.042057 | 0.040627 |
| 10000 | 0.485470 | 0.519300 |
| 100000 | 8.554627 | 7.670820 |
| 1000000 | 124.017217 | 88.197363 |

* Dataset: *nearly_sorted*

|Tamanho | MyTreeMap(ms) | JavaTreeMap(ms) |
|---|---|---|
| 100 | 0.007220 | 0.007007 |
| 1000 | 0.050267 | 0.058730 |
| 10000 | 0.880417 | 0.831600 |
| 100000 | 12.677910 | 12.949673 |
| 1000000 | 220.449987 | 218.309243 |

* Dataset: *duplicates*

|Tamanho | MyTreeMap(ms) | JavaTreeMap(ms) |
|---|---|---|
| 100 | 0.001943 | 0.003817 |
| 1000 | 0.018737 | 0.018750 |
| 10000 | 0.287697 | 0.293797 |
| 100000 | 3.511287 | 3.726837 |
| 1000000 | 44.081883 | 38.793603 |

## Busca:
![Gráfico de busca](data/graphics/grafico_search.png)

### Resultados do benchmark
* Dataset: *random*

|Tamanho | MyTreeMap(ms) | JavaTreeMap(ms) |
|---|---|---|
| 100 | 0.012097 | 0.029120 |
| 1000 | 0.051540 | 0.122547 |
| 10000 | 0.867517 | 0.811160 |
| 100000 | 16.466040 | 16.868800 |
| 1000000 | 518.930530 | 611.292633 |

* Dataset: *sorted*

|Tamanho | MyTreeMap(ms) | JavaTreeMap(ms) |
|---|---|---|
| 100 | 0.018437 | 0.044477 | 
| 1000 | 0.070830 | 0.069610 |
| 10000 | 0.436360 | 0.460647 |
| 100000 | 6.408250 | 6.254787 |
| 1000000 | 69.936893 | 67.338487 |

* Dataset: *reverse*

|Tamanho | MyTreeMap(ms) | JavaTreeMap(ms) |
|---|---|---|
| 100 | 0.013087 | 0.013697 |
| 1000 | 0.032213 | 0.037493 |
| 10000 | 0.484593 | 0.472327 |
| 100000 | 6.920037 | 7.290717 |
| 1000000 | 70.982670 | 68.863863 |

* Dataset: *nearly_sorted*

|Tamanho | MyTreeMap(ms) | JavaTreeMap(ms) |
|---|---|---|
| 100 | 0.034317 | 0.003197 |
| 1000 | 0.029717 | 0.040157 |
| 10000 | 0.549213 | 0.546860 |
| 100000 | 8.720697 | 9.111020 |
| 1000000 | 203.959430 | 198.428943 |

* Dataset: *duplicates*

|Tamanho | MyTreeMap(ms) | JavaTreeMap(ms) |
|---|---|---|
| 100 | 0.001310 | 0.001513 |
| 1000 | 0.011367 | 0.014207 |
| 10000 | 0.308773 | 0.310287 |
| 100000 | 3.783163 | 3.562753 |
| 1000000 | 36.957660 | 35.589790 |

## Remoção:
![Gráfico de remoção](data/graphics/grafico_delete.png)
* Dataset: *random*

|Tamanho | MyTreeMap(ms) | JavaTreeMap(ms) |
|---|---|---|
| 100 | 0.008310 | 0.006920 | 
| 1000 | 0.049157 | 0.021340 |
| 10000 | 0.034280 | 0.071153 |
| 100000 | 0.202933 | 0.551467 |
| 1000000 | 3.597983 | 3.320947 |

* Dataset: *sorted*

|Tamanho | MyTreeMap(ms) | JavaTreeMap(ms) |
|---|---|---|
| 100 | 0.013603 | 0.013850 | 
| 1000 | 0.033923 | 0.034067 |
| 10000 | 0.029050 | 0.018400 |
| 100000 | 0.146880 | 0.147883 |
| 1000000 | 2.668370 | 3.107987 |

* Dataset: *reverse*

|Tamanho | MyTreeMap(ms) | JavaTreeMap(ms) |
|---|---|---|
| 100 | 0.025553 | 0.020687 | 
| 1000 | 0.001410 | 0.014757 |
| 10000 | 0.026800 | 0.014437 |
| 100000 | 0.176703 | 0.166730 |
| 1000000 | 2.776337 | 4.692333 |

* Dataset: *nearly_sorted*

|Tamanho | MyTreeMap(ms) | JavaTreeMap(ms) |
|---|---|---|
| 100 | 0.017973 | 0.029960 | 
| 1000 | 0.001053 | 0.004463 |
| 10000 | 0.012083 | 0.013133 |
| 100000 | 0.144520 | 0.169970 |
| 1000000 | 3.119027 | 6.026147 |

* Dataset: *duplicates*

|Tamanho | MyTreeMap(ms) | JavaTreeMap(ms) |
|---|---|---|
| 100 | 0.002983 | 0.000757 | 
| 1000 | 0.001230 | 0.001330 |
| 10000 | 0.013053 | 0.014850 |
| 100000 | 0.160507 | 0.173857 |
| 1000000 | 5.224163 | 9.387407 |

## Carga de trabalho mista:
![Gráfico de carga de trabalho mista](data/graphics/grafico_mixed_workload.png)
* Dataset: *random*

|Tamanho | MyTreeMap(ms) | JavaTreeMap(ms) |
|---|---|---|
| 100 | 0.043763 | 0.008707 |
| 1000 | 0.081963 | 0.037543 |
| 10000 | 0.811203 | 0.828363 |
| 100000 | 21.124370 | 29.181797 |
| 1000000 | 1495.698780 | 1316.047287 |

* Dataset: *sorted*

|Tamanho | MyTreeMap(ms) | JavaTreeMap(ms) |
|---|---|---|
| 100 | 0.039307 | 0.034947 | 
| 1000 | 0.085147 | 0.073283 |
| 10000 | 1.192143 | 1.235887 |
| 100000 | 20.165497 | 26.713170 |
| 1000000 | 1526.681400 | 1511.295303 |

* Dataset: *reverse*

|Tamanho | MyTreeMap(ms) | JavaTreeMap(ms) |
|---|---|---|
| 100 | 0.034773 | 0.047437 |
| 1000 | 0.104867 | 0.107047 |
| 10000 | 1.497177 | 0.929877 |
| 100000 | 27.243153 | 22.962893 |
| 1000000 | 1518.725507 | 1436.591797 |

* Dataset: *nearly_sorted*

|Tamanho | MyTreeMap(ms) | JavaTreeMap(ms) |
|---|---|---|
| 100 | 0.019407 | 0.010800 |
| 1000 | 0.026980 | 0.048603 |
| 10000 | 1.165010 | 0.858630 |
| 100000 | 25.454013 | 21.272887 |
| 1000000 | 1508.008423 | 1330.331813 |

* Dataset: *duplicates*

|Tamanho | MyTreeMap(ms) | JavaTreeMap(ms) |
|---|---|---|
| 100 | 0.002407 | 0.002457 |
| 1000 | 0.024980 | 0.038137 |
| 10000 | 0.335620 | 0.291567 |
| 100000 | 3.392183 | 3.787227 |
| 1000000 | 131.725520 | 204.674390 |

* **Vale ressaltar que o benchmark foi feito em um ambiente com as seguintes configurações:**

| | | 
|--- | --- | 
| *Processador* | Intel Core I7 150U 1.8GHz |
| *Memória Ram* | 32GB |
| *Sistema Operacional* | Windows 11 |

## Ameaças à validade
### É possível generalizar nossa experimentação para outras cargas?

Não completamente, já que nossos experimentos foram feitos unicamente com `chaves` do tipo `inteiro`. Sendo assim, não podemos afirmar que os resultados podem ser generalizados para outras cargas já que operações que fazem comparações entre dados inteiros são pouco custosas em comparação com `Strings` longas ou objetos mais complexos que inteiros.

Pode-se levar em consideração também o teste de *carga de trabalho mista* no qual foi feito usando uma única proporção(`70% de busca, 20% de inserção e 10% de remoção`). Dessa forma, não podemos afirmar que esse teste assumiria os mesmos resultados comparativos utilizando diferentes proporções.
### Vieses de medição: 
Repetições de preparação(*warmup*): Em alguns casos, o número de repetições de warmup pode não ser suficiente para preparar a JVM e utilizá-la otimizada durante toda a medição, principalmente para entradas grandes.

Em todos os experimentos ambas as estruturas (*MyTreeMap* e *JavaTreeMap*) são executadas na mesma JVM. Com isso, os resultados podem ser comprometidos, principalmente nas execuções com entradas muito grandes, pela forma como o `Garbage Collector` de Java funciona.

## Considerações finais
`TreeMap` é uma estrutura de dados utilizada para manter pares de chave-valor sempre ordenados, possibilitando operações de inserção, busca e remoção sempre em O(log n) em diversas aplicações. Na implementação presente neste repositório foi possível identificar uma média de tempo de execução ligeiramente maior, principalmente em cargas consideravelmente maiores, comparada à estrutura implementada por `java.util.TreeMap`.

Abaixo segue uma análise mais detalhada, em percentual, da diferença da estrutura implementada neste repositório (*MyTreeMap*) com a padrão de Java.

| **Operação** | **Resultado comparativo** |
| ------------ | --------- | 
| INSERT | MyTreeMap 1,4% mais lento |
| SEARCH | MyTreeMap 7,9% mais rápido |
| DELETE | MyTreeMap 34% mais rápido |
| MIXED_WORKLOAD | MyTreeMap 6,4% mais lento |

# Autores
* **[Caio Santos](https://github.com/caio-brito-santos)**  
* **[Igor Chaves](https://github.com/igor3chaves)**  
* **[Jefferson Stanley](https://github.com/jefferson-stanley)**  
* **[Pedro Barbosa](https://github.com/barbosapdr)**  


