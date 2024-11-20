package com.sakib.io.lru_cache;

public class CacheData {
    public KVNode kvNode;

    int heapIndex;

    public CacheData(KVNode kvNode, int heapIndex) {
        this.kvNode = kvNode;
        this.heapIndex = heapIndex;
    }
}
