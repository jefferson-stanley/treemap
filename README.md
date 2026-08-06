# TreeMap

Este repositório contém a implementação de uma **TreeMap** e um material didático do mesmo com testes de unidade e de eficiência em comparativo com a estrutura de dados já nativa de **Java**.

# Introdução

O TreeMap é uma estrutura de dados que implementa a interface `Map` e usa uma **árvore rubro-negra** como estrutura interna. Diferente de um `HashMap`, o TreeMap mantém todas as chaves armazenadas em ordem crescente. Essa estrutura de dados é comumente usada em situações em que a ordenação dos dados é obrigatória ou são necessárias buscas por intervalos de valores.

# Objetivo

Produzir um material didático e prático completo sobre TreeMap com a implementação da estrutura em conjunto com testes de eficiência nas suas operações em comparação com a já implementada em **Java**(`java.util.TreeMap`).

# [Material Didático](data/text/treemap.md)

Material didático sobre o funcionamento da estrutura de dados TreeMap no padrão da disciplina de Estrutura de Dados e Algortimos da UFCG (Universidade Federal de Campina Grande). Contendo uma explicação detalhada da estrutura sobre seu funcionamento, implementação e exemplos práticos de sua usabilidade.

# Como rodar os testes:

Compile o repositório

**Linux/macOS**
```bash
    javac -d out $(find src/main/java -name "*.java")
```

**Windows (PowerShell)**
```powershell
    javac -d out (Get-ChildItem -Recurse -Filter *.java src\main\java).FullName
``` 

```Os testes de unidade contém asserções para todas as classes utilizadas na TreeMap```
**Para executá-los utilize a flag -ea(para habilitar os asserts de Java):**

```bash
java -ea cp out map.impl.TreeMapAsserts
java -ea cp out map.interfaces.MapAsserts
java -ea cp out structures.BinarySearchTreeAsserts
java -ea cp out structures.RedBlackTreeAsserts

