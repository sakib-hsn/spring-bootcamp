package io.sakib.demo.design_patterns.singleton;

import java.util.HashMap;
import java.util.Map;

public class Cache {
    private Map<String, String> map = new HashMap<>();

    private static Cache instance;

    private Cache() {
        map = new HashMap<>();
    }

    // synchronized for thread safety (100% singleton)
    public synchronized static Cache getInstance() {
        if (instance == null) {
            instance = new Cache();
        }
        return instance;
    }

    public void putKeyValue(String key, String value) {
        map.put(key, value);
    }

    public String getValue(String key) {
        return map.get(key);
    }
}
