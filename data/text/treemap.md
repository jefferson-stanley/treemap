# Tree Map

Tree Map é uma estrutura de dados de mapa ordenado que se utiliza de uma [Árvore Preto-vermelha] como motor de armazenamento. Por ser ordenada, ela garante que as operações de busca, inserção e remoção tenham uma complexidade O(logn), se mantendo sempre balanceada.

Já vimos diversas estruturas de dados de mapa, desde as mais simples até as mais complexas, então porque é interessante que estudemos mais uma dessas estruturas? Sendo direto ao ponto, isso se deve ao fato de que o TreeMap permite que os dados sejam organizados e classificados automaticamente com base nos valores de suas chaves (sejam elas alfabéticas, numéricas, etc.) independente de quando foram inseridos.

## Introdução

Primeiramente é importante falar que, por ser baseado em uma Árvore Preto-vermelha, o TreeMap necessariamente vai seguir as mesmas [propriedades] fundamentais de tal árvore. É imprescindível ter isso em mente. Agora, em questões de implementação, o que faz o TreeMap se diferenciar das outras estruturas de dados?


É isso que iremos ver a seguir.

## Por que e como manter a árvore ordenada?

### Por que ordenar? Cenários práticos

Primeiro que ao percorrer a árvore, os dados irão vir em ordem, seja ela crescente ou decrescente. Dessa forma, implica também que é muito mais fácil de encontrar os valores antecessores e sucessores, menores ou maiores que um determinado valor. Além disso, com a ordenação da árvore, é possível extrair fatias menores do mapa, sem que seja preciso percorrer todos os elementos. 

Imagine um cenário em que uma turma participe de um Amigo Secreto e para não haver valores muito discrepantes de presentes, vocês estipulem uma faixa de preços. Por exemplo: valor mínimo do presente - 75 reais, valor máximo do presente - 130 reais. Sendo assim, não faz sentido um integrante da turma procurar um presente que se encaixe nesse preço em um quiosque de rua ou fiteiro, muito menos em uma loja de grife, o ideal é buscar lojas específicas que atendam os requisitos. O trabalho do TreeMap é justamente esse, delimitar o espaço amostral, extrair fatias menores do mapa, ou seja, buscar lojas adequadas à faixa de preço de 70 a 130 reais.

Com esse exemplo é possível pensar em diversas outras situações em que seria ideal a utilização de um TreeMap para a resolução de um problema.

## Como manter a árvore ordenada? 

Para que seja possível manter a árvore ordenada, sem que se saiba com antecedência qual o tipo de dado que ele irá armazenar, a linguagem precisa de algum mecanismo que possibilitará comparar dois objetos e dizer qual vem antes do outro.

Em Java, tal ordenação é garantida por meio de duas estratégias:

Comparable: o objeto que serve como chave irá implementar a interface `Comparable` e definir uma regra própria de comparação no seu método "compareTo(Object o)".

Comparator: no momento da criação do TreeMap, é fornecido um objeto `Comparator` para definir como deve ser feita a comparação entre as chaves.

Basicamente, a diferença entre as duas estratégias está na forma em que se deseja que seja ordenada. Você pode ordenar por uma data, em ordem alfabética, por um dígito identificador, entre muitas outras possibilidades. No caso, a ordenação fica a critério do usuário.

## Regras de Organização do TreeMap

Por ser baseado em Árvore PV, é ela quem vai cuidardo balanceamento e das rotações por trás dos panos, o trabalho do TreeMap vai ser garantir que os pares (Chave, Valor) estejam organizados corretamente.

Temos três regras para garantir o funcionamento e a organização do TreeMap:

Ordenação Obrigatória por Chave: toda a ordenação é baseada nas chaves, em que obrigatoriamente a chave à esquerda é menor e a chave à direita é maior, de acordo com que parametro está sendo utilizade para a ordenação (númerica, alfabética, etc).

Unicidade de Chaves: o mapa não permite chaves duplicadas. Caso tentarmos inserir uma chave já existente, o valor associado a ela é sobrescrito.

Imutabilidade do Critério de Comparação: o critério usado para comparação (seja por `Comparable` ou `Comparator`) deve ser o mesmo durante toda a vida útil do mapa.

### O que É e o que NÃO É um TreeMap válido?

Vamos analisar como a estrutura armazena os pares de dados em memória e o que violaria as regras de um `TreeMap`.

#### Exemplo válido

As chaves numéricas identificam o produto e definem a posição exata na árvore. Note que a busca por qualquer chave segue perfeitamente a propriedade de busca binária, independente dos valores (nomes dos produtos) armazenados:

```
┌─────────────────────────────────────────┐
│              [ (50, "TV") ]  <-- Preto  │
│               /          \              │
│              /            \             │
│    [ (30, "Som") ]    [ (70, "PC") ]    │
│      <-- Vermelho       <-- Vermelho    │
│       /        \          /        \    │
│  [ (20,"Fone") ] [ (40,"Mouse") ]  NIL  │
│    <-- Preto       <-- Preto            │
└─────────────────────────────────────────┘
```

#### Exemplos inválidos

    Violação da Propriedade de Busca (Ordenação incorreta):

    [ (50, "TV") ] (Preto)
      /
    [ (60, "Teclado") ] (Vermelho)
 
    É inválido porque a chave `60` é maior que a chave `50`, mas está alocada na subárvore à esquerda, o que quebra o algoritmo de busca (`get`), que vai buscar `60` à direita e não o encontrará.


## Modelagem da Classe e Estrutura do Nó

Sabendo das regras de um mapa ordenado, é hora de vermos a implementação em Java.

Cada elemento/nó dentro da árvore não armazena apenas um valor simples, mas sim uma entrada contendo a chave, o valor e os ponteiros de navegação.

Vejamos uma versão simplificada de como a classe `TreeMap` e seu nó interno (`Entry`) são estruturados em Java:
