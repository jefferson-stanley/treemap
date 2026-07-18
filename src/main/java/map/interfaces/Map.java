package map.interfaces;

import java.util.Collection;
import java.util.Set;

public interface Map<K, V> {

    /**
     * Insere um par chave-valor no mapa.
     * Se a chave já existir, o valor deve ser atualizado.
     */
    void put(K key, V value);

    /**
     * Busca um valor pela chave.
     * Retorna o valor correspondente ou null se a chave não existir.
     */
    V get(K key);

    /**
     * Remove o nó correspondente à chave informada.
     */
    void remove(K key);

    /**
     * Verifica se o mapa contém uma determinada chave.
     */
    boolean containsKey(K key);

    boolean containsValue(V value);

    /**
     * Retorna a quantidade de elementos no mapa.
     */
    int size();

    /**
     * Verifica se o mapa está vazio.
     */
    boolean isEmpty();

    void clear();

    Set<K> keySet();

    Collection<V> values();

    Set<Entry<K, V>> entrySet();

    interface Entry<K, V> {
        K getKey();

        V getValue();
    }
}
