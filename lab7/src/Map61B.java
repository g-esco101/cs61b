package src;

import java.util.Set;
/* Your implementation src.BSTMap should implement this interface. To do so,
 * append "implements src.Map61B<K,V>" to the end of your "public class..."
 * declaration, though you can use other formal type parameters if you'd like.
 */
public interface Map61B<K, V> extends Iterable<K> {
    /** Removes all of the mappings from this map. */
    void clear();

    /** Returns true if this map contains a mapping for the specified key.
     *
     * @param key to check if it is contained.
     * @return true if the key is contained. Otherwise, false.
     */
    boolean containsKey(K key);

    /** Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     *
     * @param key the key of the associated value.
     * @return the value associated with the key.
     */
    V get(K key);

    /** Returns the number of key-value mappings in this map.
     *
     * @return the number of k-value mappings.
     */
    int size();

    /** Associates the specified value with the specified key in this map.
     *
     * @param key of the mapping.
     * @param value the associated value of the key.
     */
    void put(K key, V value);

    /** Returns a Set view of the keys contained in this map.
     *
     * @return a set of the keys.
     */
    Set<K> keySet();

    /** Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an UnsupportedOperationException.
     *
     * @param key of the mapping to be removed.
     */
    V remove(K key);

    /** Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     *
     * @param key of the mapping to be removed.
     * @param value the associated value of the key.
     */
    V remove(K key, V value);
}
