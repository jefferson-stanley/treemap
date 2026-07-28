<!--toc:start-->
- [O Que É Uma Árvore Preto-Vermelha?](#o-que-é-uma-árvore-preto-vermelha)
- [O Nó](#o-nó)
  - [Os Nós Nulos de Uma Árvore Preto-Vermelha](#os-nós-nulos-de-uma-árvore-preto-vermelha)
- [Propriedades](#propriedades)
- [Altura](#altura)
<!--toc:end-->

+++
title = "Red Black Tree"
date = 2026-07-26
tags = []
categories = []
github = "<https://github.com/joaoarthurbm/eda-implementacoes/tree/master/java/src/tabelahash>"
+++

***

Imagine uma situação em que você precisa fazer múltiplas inserções e remoções na sua estrutura de árvore, ter o menor custo possível e manter o balanceamento para que operações como a busca continuem sendo executadas em complexidade $O(log(n))$. Baseada no conhecimento adquirido até agora na displina, a resposta natural seria o uso de uma árvore AVL, contudo, por possuir uma política de balanceamento muito rígida, a árvore AVL não consegue atender a essas condições.

O balanceamento de uma árvore AVL exige que a diferença entre as subárvores esquerda e direita de qualquer nó não pode ser maior que 1. Consequentemente, as inserções e especialmente as remoções podem demandar muitas rotações para restaurar o equilíbrio e manter a árvore plana. Se uma aplicação faz muitas inserções/remoções, o custo de rebalanceamento pode ser alto. Para ocasiões em que você adiciona e/ou remove muitos dados, a melhor opção é uma Árvore Preto-Vermelha.

# O Que É Uma Árvore Preto-Vermelha?

Uma Árvore Preto-Vermelha é uma árvore binária de busca auto-balanceada que possui um sitema de coloração para os nós e impõe regras para que seja obtido um balanceamento aproximado.

# O Nó

O nó guarda uma informação extra: **cor**, que pode ser vermelha ou preta. A cor é um tipo de classe que representa constantes pré-definidas e únicas, um enum.

```java
public class Node{
    int value;
    Color color;

    Node parent;
    Node left;
    Node right;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.color = Color.RED;
    }

    public void setColor(Color color){
        this.color = color;
    }
}

enum Color {
    RED,
    BLACK
}
}
```

Iniciamos o nó com a cor vermelha devido à uma propriedade, mais abaixo falaremos sobre.

## Os Nós Nulos de Uma Árvore Preto-Vermelha

Um nó NIL é um nó folha que não possui valor. Esse nó sentinela nos ajuda a determinar a cor dos tios ou irmãos, sendo essencial nas propriedades de coloração.

- Um nó NIL é sempre uma folha e não pode ter filhos;
- O nó sentinela é sempre **preto**;

# Propriedades

1. Cada nó é vermelho ou preto;

2. A raiz da árvore é sempre preta;

3. Toda folha NIL é preta;

4. Um nó vermelho não pode ter filhos vermelhos;

5. O "Black Height" é o número de nós pretos de um nó até suas folhas nulas(NIL). Todo caminho de um nó até suas folhas descendentes possui o mesmo Black Height;

# Altura

A altura de uma árvore preto-vermelho é definida pelo maior caminho da raiz a uma folha NIL, ou seja, o número de arestas no maior caminho da raiz até qualquer folha nula.
