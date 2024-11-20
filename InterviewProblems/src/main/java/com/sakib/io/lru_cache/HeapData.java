package com.sakib.io.lru_cache;

public class HeapData {
    public String key;

    public long timestamp;

    public HeapData(String key, long timestamp) {
        this.key = key;
        this.timestamp = timestamp;
    }
}
