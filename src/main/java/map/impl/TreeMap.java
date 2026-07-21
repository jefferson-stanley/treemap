package map.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import structures.*;
import map.interfaces.*;

public class TreeMap<K extends Comparable<K>, V> implements Map<K, V> {

    RedBlackTree<K, V> tree;

    public TreeMap() {
        tree = new RedBlackTree<>();
    }

    /**
     * Insere um novo elemento associado a uma chave, utilizando uma Árvore Preta e Vermelha com a lógica de mapas.
     * @param key Chave que será associada ao valor, usada para determinar sua posição na árvore.
     * @param value Valor que será relacionado à chave.
     */
    @Override
    public void put(K key, V value) {
        tree.insert(key, value);
    }

    /**
     * Retorna o valor associado à chave passada como parâmetro. Realiza uma busca do nó correspondete à chave na árvore.
     * @param key Chave relacionada ao valor que deve ser retornado.
     * @return Valor associado à chave indicada. Retorna null caso a chave não esteja presente no mapa.
     */
    @Override
    public V get(K key) {

        Node<K, V> node = tree.search(key);

        if (node == null || node.getKey() == null) {
            return null;
        }

        return node.getValue();
    }

    /**
     * Remove o mapeamento da chave passada como parâmetro.
     * @param key Chave que será removida, juntamente com seu valor, do mapa.
     */
    @Override
    public void remove(K key) {

        tree.delete(key);
    }

    /**
     * Verifica a existência no mapa da chave passada como parâmetro.
     * @param key Chave que será verificada.
     * @return true se houver mapeamento da chave indicada ou false caso não haja.
     */
    @Override
    public boolean containsKey(K key) {
        return tree.containsKey(key);
    }

    /**
     * Retorna a quantidade de elementos na árvore.
     * @return Valor inteiro indicando o tamanho total da árvore.
     */
    @Override
    public int size() {
        return tree.getSize();
    }

    /**
     * Verifica se a árvore está vazia.
     * @return true caso não tenha elementos na árvore ou false caso tenha pelo menos um.
     */
    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public boolean containsValue(V value) {
        return tree.containsValue(value);
    }

    @Override
    public void clear() {
        tree.clear();
    }

    @Override
    public Set<K> keySet() {
        return tree.collectKeys();
    }

    @Override
    public Collection<V> values() {
        return tree.collectValues();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entries = new HashSet<>();

        tree.forEachEntry(node -> {
            entries.add(new MeuEntry<>(node.getKey(), node.getValue()));
        });

        return entries;
    }

    private static class MeuEntry<K, V> implements Entry<K, V> {
        private final K key;
        private final V value;

        public MeuEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Entry))
                return false;
            Entry<?, ?> e = (Entry<?, ?>) o;
            return (key == null ? e.getKey() == null : key.equals(e.getKey())) &&
                    (value == null ? e.getValue() == null : value.equals(e.getValue()));
        }

        @Override
        public int hashCode() {
            return (key == null ? 0 : key.hashCode()) ^ (value == null ? 0 : value.hashCode());
        }

    }
}
