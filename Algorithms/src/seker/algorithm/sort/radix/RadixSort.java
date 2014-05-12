package seker.algorithm.sort.radix;

import seker.algorithm.sort.ISort;
import seker.algorithm.sort.SortTest;

/**
 * 基数排序（Radix Sorting）
 * 
 * 基数排序于其他各类排序方法完全不同。
 * 其他的排序方法主要是通过关键字之间的比较和移动记录这种操作，而实现技术排序不需要进行关键字间的比较。
 * 基数排序是一种借助多关键字排序的思想对单逻辑关键字进行排序的方法。
 */
public class RadixSort implements ISort {
    @Override
    public int[] sort(int[] data) {
        final int radix = (int) Math.log10(SortTest.BASE);
        for (int r = 0; r < radix; r ++) {
            int n = data.length;
            for (n = n - 1; n > 0; n--) {   // 冒泡排序
                boolean bSwap = false;
                for (int i = 0; i < n; i++) {
                    int radix_prev = convert(r, data[i]);
                    int radix_next = convert(r, data[i + 1]);
                    if (radix_prev > radix_next) {
                        int temp = data[i];
                        data[i] = data[i + 1];
                        data[i + 1] = temp;

                        bSwap = true;
                    }
                }
                if (!bSwap) {
                    break;
                }
            }
        }
        return data;
    }
    
    private int convert(int radix, int value) {
        return (int) (value % Math.pow(10, radix + 1) / Math.pow(10, radix));
    }
}
