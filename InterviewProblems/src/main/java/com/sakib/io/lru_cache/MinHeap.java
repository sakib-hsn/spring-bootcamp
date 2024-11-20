package com.sakib.io.lru_cache;

import java.util.ArrayList;
import java.util.List;

public class MinHeap {
    public List<Integer> heap;

    public MinHeap() {
        heap = new ArrayList<>();
    }

    public void insert(int value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }

    public void remove(int i) {
        heap.set(i, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        heapifyDown(i);
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    private void heapifyUp(int i) {
        int parent = (i - 1) / 2;
        if (parent >= 0 && heap.get(parent) > heap.get(i)) {
            swap(i, parent);
            heapifyUp(parent);
        }
    }

    private void heapifyDown(int i) {
        int lowest = i;
        int left = 2 * i + 1;
        int right = left + 1;

        if (left < heap.size() && heap.get(left) < heap.get(lowest)) {
            lowest = left;
        }

        if (right < heap.size() && heap.get(right) < heap.get(lowest)) {
            lowest = right;
        }

        if (lowest != i) {
            swap(i, lowest);
            heapifyDown(lowest);
        }
    }
}
