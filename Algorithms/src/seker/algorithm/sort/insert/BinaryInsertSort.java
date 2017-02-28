package seker.algorithm.sort.insert;

import seker.algorithm.sort.ISort;

/**
 * 当直接插入排序进行到某一趟时，对于 r[i].key 来讲，前边 i-1 个记录已经按关键字有序。此时不用直接插入排序的方法，而改为折半查找，
 * 找出 r[i].key 应插的位置，然后插入。这种方法就是折半插入排序（ Binary insertion sort ）。
 * 
 * 折半插入排序方法是稳定的。
 * 
 * 折半插入排序，关键字的比较次数由于采用了折半查找而减少，数量级为O（N ^ log2 N） ，但是元素移动次数仍为O（N ^ 2）。
 * 故折半插入排序时间复杂度仍为O（N ^ 2） 。
 * 空间复杂度：O(1)
 */
public class BinaryInsertSort implements ISort {
    @Override
    public int[] sort(int[] data) {
        final int n = data.length;
        
        
        int sentinel; // 哨兵

        for (int i = 1, low, high, mid; i < n; i++) {
            sentinel = data[i];

            // Binary select to find the most appropriate index: low
            for (low = 0, high = i - 1; low <= high;) {
                mid = (low + high) / 2;
                if (sentinel < data[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
    
            System.arraycopy(data, low, data, low + 1, i - low);

            data[low] = sentinel;
        }
        return data;
    }
}
