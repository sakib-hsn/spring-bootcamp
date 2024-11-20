package com.sakib.io.lru_cache;

public class KVNode {
    public String key;

    public String value;

    public long timestamp;

    public KVNode prev, next;

    public KVNode(String key, String value, long timestamp) {
        this.key = key;
        this.value = value;
//        this.timestamp = System.currentTimeMillis();
        this.timestamp = timestamp;
    }
}
