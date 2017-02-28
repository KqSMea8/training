package seker.algorithm.sort.select;

import seker.algorithm.sort.ISort;

/**
 * 选择排序排序 
 *      选择排序(Selection Sort)的基本思想是：每一趟从待排序的记录中选出关键字最小的记录，顺序放在已排好序的子文件的最后，直到全部记录排序完毕。
 *      常用的选择排序方法有简单选择排序和堆排序。
 * 
 * 简单选择排序 
 *      简单选择排序(simple selection sort)也是直接选择排序。此方法在一些高级语言课程中做过介绍，是一种较为容易理解的方法。
 * 1.算法思想
 *       对于一组关键字{K1,K2,…,Kn}， 
 *       首先从K1,K2,…,Kn中选择最小值，假如它是 Kz，则将Kz与 K1对换；
 *       然后从K2，K3，… ，Kn中选择最小值 Kz，再将Kz与K2对换。
 *       如此进行选择和调换n-2趟，第(n-1)趟，从Kn-1、Kn中选择最小值 Kz将Kz与Kn-1对换，
 *       最后剩下的就是该序列中的最大值，一个由小到大的有序序列就这样形成。
 * 
 * 【例】下图是一个有5个关键字{3,4,1,5,2}的简单选择排序过程的示意图。
 *  
 *  初    始     3   4   1   5   2
 *  第一趟   [1]  4   3   5   2
 *  第二趟   [1   2]  3   5   4
 *  第三趟   [1   2   3]  5   4
 *  第四趟   [1   2   3   4]  5
 *  结    果   [1   2   3   4   5]
 *  
 * 2.算法的时间复杂度
 *  该算法的时间复杂度为 O(n ^ 2)。并且排序是不稳定的。
 */
public class SelectSort implements ISort {
    @Override
    public int[] sort(int[] data) {
        int temp;
                
        for (int i = 0, index, n = data.length; i < n; i++) {
            index = i;
            
            for (int j = i + 1; j < n; j++) {   // Find the smallest item.
                if (data[j] < data[index]) {
                    index = j;
                }
            }
            
            if (index > i) {                        // Swap value
                temp = data[i];
                data[i] = data[index];
                data[index] = temp;
            }
        }
        return data;
    }
}
