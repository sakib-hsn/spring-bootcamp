package com.sakib.io.lru_cache;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LRUCache {
    public int capacity;

    public int ttl;

    public Map<String, CacheData> map;

    MinHeap heap;

    private final KVNode head;

    private final KVNode tail;

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


    public void removeFromList(KVNode kvNode) {
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
        System.out.println("removeExpired called");
//        System.out.println("currentTime = " + currentTime);
//        if (heap.heap.isEmpty()) {
//            System.out.println("heap is empty");
//        } else {
//            System.out.println("heap.get(0).timestamp = " + heap.get(0).timestamp);
//            System.out.println("(currentTime - heap.get(0).timestamp) = " + (currentTime - heap.get(0).timestamp) / 1000);
//        }
        while (!heap.heap.isEmpty() && currentTime - heap.get(0).timestamp > ttl * 1000L) {
            String key = heap.get(0).key;
            System.out.println("removing: " + key);
            KVNode kvNode = map.get(key).kvNode;
            removeFromList(kvNode);
            removeFromHeap(kvNode);
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
            removeFromList(keyNode);
            moveToFront(keyNode);
        } else {
            if (map.size() >= capacity) {
                KVNode kvNode = tail.next;
                removeFromList(kvNode);
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
        removeFromList(kvNode);
        moveToFront(kvNode);

        return kvNode.value;
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2, 10);
//        lruCache.put("1", "1", 1);
//        lruCache.put("2", "2", 2);
//        lruCache.removeExpired(4);
//        String value = lruCache.get("1");
//        System.out.println("value = " + value);
//        lruCache.put("3", "3", 3);
//        lruCache.removeExpired(5);
//        value = lruCache.get("2");
//        System.out.println("value = " + value);
//        value = lruCache.get("3");
//        System.out.println("value = " + value);

        Thread thread = new Thread(() -> {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            while (true) {
                System.out.println("Current Time: " + formatter.format(new Date()));
                lruCache.removeExpired(System.currentTimeMillis());
                try {
                    Thread.sleep(lruCache.ttl * 1000L); // Sleep for ttl second
                } catch (InterruptedException e) {
                    System.out.println("Time thread interrupted!");
                    break;
                }
            }
        });

        thread.start();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter choice: ");
            System.out.println("1. put");
            System.out.println("2. get");
            int coice = scanner.nextInt();
            System.out.println("Enter key: ");
            String key = scanner.next();
            String value;
            switch (coice) {
                case 1:
                    System.out.println("Enter value: ");
                    value = scanner.next();
                    lruCache.put(key, value, System.currentTimeMillis());
                    break;
                case 2:
                    value = lruCache.get(key);
                    System.out.println("value = " + value);
            }
        }

//        thread.interrupt();
    }
}
