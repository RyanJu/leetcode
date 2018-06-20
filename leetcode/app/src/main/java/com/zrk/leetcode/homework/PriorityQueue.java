package com.zrk.leetcode.homework;

import java.util.Arrays;
import java.util.Timer;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/6/15 17:43 1.0
 * @time 2018/6/15 17:43
 * @project leetcode com.zrk.leetcode.homework
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/6/15 17:43
 */

public class PriorityQueue<T extends Comparable> {

    transient Object[] mEntries = new Object[3];
    int mSize;

    public PriorityQueue() {
    }

    public PriorityQueue(T[] entries) {
        if (entries != null) {
            mEntries = Arrays.copyOf(entries, entries.length, Object[].class);
            mSize = entries.length;
            heapify();
        }
    }

    private void heapify() {
        int k = mSize >>> 2 - 1;
        for (int i = k; i >= 0; i--) {
            siftDown(i);
        }
    }

    private void siftDown(int i) {
        Comparable key = (Comparable) mEntries[i];
        while (i < mSize) {
            int left = childLeft(i);
            int right = left + 1;
            int k = left;
            if (right < mSize) {
                if (((Comparable) mEntries[right]).compareTo(mEntries[left]) > 0) {
                    k = right;
                }
            }
            if (left < mSize) {
                if (key.compareTo(mEntries[k]) < 0) {
                    mEntries[i] = mEntries[k];
                    i = k;
                } else
                    break;
            } else
                break;
        }
        mEntries[i] = key;
    }

    public T extractMax() {
        if (mEntries == null || mEntries.length <= 0) {
            return null;
        }
        T max = (T) mEntries[0];
        mEntries[0] = mEntries[mSize - 1];
        mEntries[mSize - 1] = null;
        mSize--;
        siftDown(0);
        return max;
    }

    public T maximum() {
        return (mEntries == null || mEntries.length <= 0) ? null : (T) mEntries[0];
    }

    public void insert(T t) {
        if (mSize + 1 >= mEntries.length) {
            grow(mEntries.length * 2 + 1);
        }

        mEntries[mSize++] = t;
        siftUp(mSize - 1);
    }

    private void siftUp(int index) {
        Comparable key = (Comparable) mEntries[index];
        while (index > 0) {
            int p = parent(index);
            if (p >= 0) {
                if (key.compareTo(mEntries[p]) > 0) {
                    mEntries[index] = mEntries[p];
                    index = p;
                } else
                    break;
            } else
                break;
        }
        mEntries[index] = key;
    }

    private void grow(int newLength) {
        if (mSize > newLength) {
            newLength = mSize * 2 + 1;
        }
        mEntries = Arrays.copyOf(mEntries, newLength, Object[].class);
    }

    public boolean increaseKey(T t, T newOne) {
        if (t == null || newOne == null) {
            return false;
        }
        if (t.compareTo(newOne) <= 0) {
            return false;
        }
        int index = findIndex(t);
        if (index == -1) {
            return false;
        }
        mEntries[index] = newOne;
        siftUp(index);
        return true;
    }

    private int findIndex(T t) {
        if (mEntries == null || mEntries.length <= 0) {
            return -1;
        }
        int i = 0;
        Comparable key = t;
        while (i < mSize) {
            int cp = key.compareTo(mEntries[i]);
            if (cp == 0) {
                return i;
            } else if (cp < 0) {
                i = childLeft(i);
            } else {
                i = childLeft(i) + 1;
            }
        }
        return -1;
    }

    public boolean decreaseKey(T t, T newOne) {
        if (t == null || newOne == null) {
            return false;
        }
        if (t.compareTo(newOne) >= 0) {
            return false;
        }
        int index = findIndex(t);
        if (index == -1) {
            return false;
        }
        mEntries[index] = newOne;
        siftDown(index);
        return true;
    }

    public boolean empty() {
        return mSize == 0;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int childLeft(int index) {
        return index * 2 + 1;
    }


    public static void main(String[] args) {
        Integer[] array = {2, 1, 3, 10, 12, 13, 88, 9, 100, 55};
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        queue = new PriorityQueue<>(array);
        print(queue);

        while (!queue.empty()) {
            System.out.println("extract max = " + queue.extractMax());
        }

    }

    private static void print(PriorityQueue queue) {
        final Object[] entries = queue.mEntries;
        final int size = queue.mSize;
        if (entries != null) {
            for (int i = 0; i < size; i++) {
                System.out.print("  " + entries[i]);
            }
        }
        System.out.println();
    }
}
