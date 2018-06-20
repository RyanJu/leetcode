package com.zrk.leetcode.homework;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/6/11 14:59 1.0
 * @time 2018/6/11 14:59
 * @project leetcode com.zrk.leetcode.homework
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/6/11 14:59
 */

public class Heap<T> {

    public static void main(String[] args) {
        Integer[] array = {7, 9, 1, 3, 2, 4, 10, 8, 111, 23};
        Heap<Integer> heap = new Heap<>();
        heap.build(array);
        printHeap(heap);
        heap.insert(3);
        printHeap(heap);
        heap.insert(0);
        printHeap(heap);

        System.out.println("take");
        while (!heap.empty()) {
            System.out.print(" " + heap.takeTop());
        }
        printHeap(heap);

        for (int a : array) {
            heap.insert(a);
        }
        printHeap(heap);

        Integer[] sort = Heap.heapSort(array);

        System.out.println(Arrays.toString(sort));
    }

    private static final int DEFAULT_SIZE = 7;

    public static void printHeap(Heap heap) {
        if (heap == null || heap.empty()) {
            System.out.println("\nheap is null");
            return;
        }
        for (int i = 0; i < heap.mSize; i++) {
            System.out.print(" " + heap.mArray[i]);
        }
        System.out.println();
    }


    T[] mArray;
    int mSize;
    Comparator<? super T> mComparator;

    public Heap() {
        mArray = (T[]) new Object[DEFAULT_SIZE];
    }

    public Heap(Comparator<? super T> comparator) {
        this();
        this.mComparator = comparator;
    }

    public static <T> T[] heapSort(T[] input) {
        Heap<T> heap = new Heap<>();
        heap.build(input);

        for (int i = input.length - 1; i > 0; i--) {
            heap.swap(i, 0);
            heap.mSize--;
            heap.siftDown(0);
        }
        return input;
    }

    public void build(T[] array) {
        mArray = array;
        mSize = array.length;
        for (int i = parent(mSize - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    public void insert(T data) {
        if (mSize + 1 >= mArray.length) {
            grow(mArray.length * 2 + 1);
        }
        mArray[mSize++] = data;
        siftUp(mSize - 1);
    }

    public T top() {
        return mArray == null ? null : mArray[0];
    }

    public boolean empty() {
        return mSize <= 0;
    }

    public T takeTop() {
        if (mArray == null || empty()) {
            return null;
        }

        T top = mArray[0];
        --mSize;
        if (mSize > 0) {
            swap(0, mSize);
            siftDown(0);
        }
        return top;
    }

    private void siftUp(int index) {
        while (indexValid(index)) {
            int parent = parent(index);
            if (!indexValid(parent)) {
                return;
            }
            if (compare(index, parent) < 0) {
                swap(index, parent);
                index = parent;
            } else {
                return;
            }
        }
    }

    private void grow(int capcity) {
        T[] newArray = (T[]) new Object[capcity];
        System.arraycopy(mArray, 0, newArray, 0, mSize);
        mArray = newArray;
    }

    private void siftDown(int index) {
        while (indexValid(index)) {
            int min = index;
            int left = left(index);
            if (indexValid(left) && compare(index, left) > 0) {
                min = left;
            }
            int right = right(index);
            if (indexValid(right) && compare(min, right) > 0) {
                min = right;
            }
            if (min == index) {
                return;
            }
            swap(min, index);
            index = min;
        }
    }

    private void swap(int index1, int index2) {
        T temp = mArray[index1];
        mArray[index1] = mArray[index2];
        mArray[index2] = temp;
    }

    private int compare(int index1, int index2) {
        if (mComparator != null) {
            return mComparator.compare(mArray[index1], mArray[index2]);
        }
        return ((Comparable) mArray[index1]).compareTo(mArray[index2]);
    }

    int parent(int index) {
        return (index - 1) / 2;
    }

    int left(int index) {
        return index * 2 + 1;
    }

    int right(int index) {
        return index * 2 + 2;
    }

    boolean indexValid(int index) {
        return index >= 0 && index < mSize;
    }

    boolean hasParent(int index) {
        return parent(index) >= 0;
    }

    boolean hasLeft(int index) {
        return left(index) < mSize;
    }

    boolean hasRight(int index) {
        return right(index) < mSize;
    }
}
