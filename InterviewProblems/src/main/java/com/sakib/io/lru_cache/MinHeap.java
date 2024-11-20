package com.sakib.io.lru_cache;

import java.util.ArrayList;
import java.util.List;

public class MinHeap {
    public List<HeapData> heap;

    public LRUCache cache;

    public MinHeap(LRUCache cache) {
        heap = new ArrayList<>();
        this.cache = cache;
    }

    public int insert(HeapData value) {
        heap.add(value);
        return heapifyUp(heap.size() - 1);
    }

    public void remove(int i) {
        heap.set(i, heap.get(heap.size() - 1));
        cache.updateHeapIndex(heap.get(heap.size() - 1).key, i);
        heap.remove(heap.size() - 1);
        heapifyDown(i);
    }

    public HeapData get(int i) {
        return heap.get(i);
    }

    private void swap(int i, int j) {
        HeapData temp = heap.get(i);
        cache.updateHeapIndex(heap.get(i).key, j);
        heap.set(i, heap.get(j));
        cache.updateHeapIndex(heap.get(j).key, i);
        heap.set(j, temp);
    }

    private int heapifyUp(int i) {
        int parent = (i - 1) / 2;
        if (parent < 0) {
            return i;
        }
        if (heap.get(parent).timestamp > heap.get(i).timestamp) {
            swap(i, parent);
            return heapifyUp(parent);
        }
        return i;
    }

    private void heapifyDown(int i) {
        int lowest = i;
        int left = 2 * i + 1;
        int right = left + 1;

        if (left < heap.size() && heap.get(left).timestamp < heap.get(lowest).timestamp) {
            lowest = left;
        }

        if (right < heap.size() && heap.get(right).timestamp < heap.get(lowest).timestamp) {
            lowest = right;
        }

        if (lowest != i) {
            swap(i, lowest);
            heapifyDown(lowest);
        }
    }
}
