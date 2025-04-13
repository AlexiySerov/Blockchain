package Kusu.Blockchain.utils;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

public class ARCache<K, V> {
    private final LinkedHashMap<K, V> T1 = new LinkedHashMap<>(); // LRU для новых элементов
    private final LinkedHashMap<K, V> T2 = new LinkedHashMap<>(); // LRU для частых элементов
    private final Set<K> B1 = new HashSet<>(); // Ghost-записи T1
    private final Set<K> B2 = new HashSet<>(); // Ghost-записи T2
    private int cacheSize = 1000;
    private int p = 0; // Параметр адаптации (баланс T1/T2)

    public ARCache() {
    }

    public ARCache(int size) {
        this.cacheSize = size;
    }

    public V get(K key) {
        // Если в T1 или T2 — возвращаем и перемещаем в голову T2
        if (T2.containsKey(key)) {
            return T2.get(key);
        }
        if (T1.containsKey(key)) {
            V value = T1.remove(key);
            put2(key, value);
            return value;
        }

        // Если в ghost-записях — адаптируем размеры
        if (B1.contains(key)) {
            p = Math.min(p + 1, cacheSize); // Увеличиваем приоритет T2
            replace(key);
            return null;
        }
        if (B2.contains(key)) {
            p = Math.max(p - 1, 0); // Уменьшаем приоритет T2
            replace(key);
            return null;
        }
        return null; // Кэш-промах
    }

    private void put2(K key, V value) {
        T2.put(key, value);
    }

    public void put(K key, V value) {
        if (T2.containsKey(key)) {
            T2.put(key, value);
            return;
        }
        if (T1.containsKey(key)) {
            put2(key, value);
            return;
        }
        // Новый элемент → добавляем в T1
        T1.put(key, value);
        evictIfNeeded();
    }

    private void evictIfNeeded() {
        if (T1.size() + T2.size() >= cacheSize) {
            if (T1.size() > p) {
                // Вытесняем из T1
                K lastKey = T1.keySet().iterator().next();
                T1.remove(lastKey);
                B1.add(lastKey);
            } else {
                // Вытесняем из T2
                K lastKey = T2.keySet().iterator().next();
                T2.remove(lastKey);
                B2.add(lastKey);
            }
        }
    }

    private void replace(K key) {
        if (B1.contains(key)) {
            // Элемент был в T1 → увеличиваем приоритет T2
            p = Math.min(p + 1, cacheSize);
        } else if (B2.contains(key)) {
            // Элемент был в T2 → уменьшаем приоритет T2
            p = Math.max(p - 1, 0);
        }
        evictIfNeeded(); // Вытесняем элемент, если кэш переполнен
    }


}