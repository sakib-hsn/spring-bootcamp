package com.sakib.io.lru_cache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    public int capacity;

    public int ttl;

    public Map<String, CacheData> map;

    MinHeap heap;

    private KVNode head;

    private KVNode tail;

    public LRUCache(int capacity, int ttl) {
        this.capacity = capacity;
        this.ttl = ttl;
        map = new HashMap<>();
        heap = new MinHeap(this);

        head = new KVNode("", "", 999999);
        tail = new KVNode("", "", 999999);

        tail.next = head;
        head.prev = tail;
    }

    public void remove(KVNode kvNode) {
        kvNode.prev.next = kvNode.next;
        kvNode.next.prev = kvNode.prev;
    }

    public void moveToFront(KVNode kvNode) {
        head.prev.next = kvNode;
        kvNode.prev = head.prev;
        head.prev = kvNode;
        kvNode.next = head;
    }

    public void removeFromHeap(KVNode kvNode) {
        int removeIndex = map.get(kvNode.key).heapIndex;
        heap.remove(removeIndex);
    }

    public void removeExpired(long currentTime) {
        while (!heap.heap.isEmpty() && currentTime - heap.get(0).timestamp > ttl) {
            String key = heap.get(0).key;
            heap.remove(map.get(key).heapIndex);
            map.remove(key);
        }
    }

    public void updateHeapIndex(String key, int index) {
        map.get(key).heapIndex = index;
    }

    public void put(String key, String value, long timestamp) {
        if (map.containsKey(key)) {
            KVNode keyNode = map.get(key).kvNode;
            keyNode.value = value;
            remove(keyNode);
            moveToFront(keyNode);
        } else {
            if (map.size() >= capacity) {
                KVNode kvNode = tail.next;
                remove(kvNode);
                removeFromHeap(kvNode);
                map.remove(kvNode.key);
            }
            KVNode kvNode = new KVNode(key, value, timestamp);
            moveToFront(kvNode);

            HeapData heapData = new HeapData(key, timestamp);
            int heapIndex = heap.insert(heapData);

            CacheData cacheData = new CacheData(kvNode, heapIndex);

            map.put(key, cacheData);
        }
    }

    public String get(String key) {
        if (!map.containsKey(key)) {
            return null;
        }

        KVNode kvNode = map.get(key).kvNode;
        remove(kvNode);
        moveToFront(kvNode);

        return kvNode.value;
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2, 2);

        lruCache.put("1", "1", 1);
        lruCache.put("2", "2", 2);
        lruCache.removeExpired(4);
        String value = lruCache.get("1");
        System.out.println("value = " + value);
        lruCache.put("3", "3", 3);
        lruCache.removeExpired(5);
        value = lruCache.get("2");
        System.out.println("value = " + value);
        value = lruCache.get("3");
        System.out.println("value = " + value);
    }
}
