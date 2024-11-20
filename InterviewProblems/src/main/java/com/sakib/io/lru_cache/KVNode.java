package com.sakib.io.lru_cache;

public class KVNode {
    public String key;

    public String value;

    public KVNode prev, next;

    public KVNode(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
