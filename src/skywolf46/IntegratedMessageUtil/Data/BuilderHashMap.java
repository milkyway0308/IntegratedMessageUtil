package skywolf46.IntegratedMessageUtil.Data;

import java.util.HashMap;

public class BuilderHashMap<K, V> {
    private HashMap<K, V> map = new HashMap<>();

    public BuilderHashMap<K, V> put(K k, V v) {
        map.put(k, v);
        return this;
    }

    public V get(K k) {
        return map.get(k);
    }

    public HashMap<K, V> getMap() {
        return map;
    }
}
