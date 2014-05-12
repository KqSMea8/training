package seker.algorithm.sort.insert;

import seker.algorithm.sort.ISort;

/**
 * 希尔排序 
 * 1.定义
 *  希尔排序（ shell sort ）是 D．L．希尔（ D.L.Shell ）提出的“缩小增量”的排序方法。
 *  它的作法不是每次一个元素挨一个元素的比较。 而是初期选用大跨步（增量较大）间隔比较，使记录跳跃式接近它的排序位置；
 *  然后增量缩小；最后增量为 1 ，这样记录移动次数大大减少，提高了排序效率。希尔排序对增量序列的选择没有严格规定。
 * 
 * 2.算法思路
 *   ①先取一个正整数 d1(d1 < n) ，把全部记录分成 d1个组，所有距离为 d1的倍数的记录看成一组，然后在各组内进行插入排序；
 *   ②然后取 d2( d2 < d1 ) 。
 *   ③重复上述分组和排序操作；直到取 di=1(i>=1) ，即所有记录成为一个组为止。一般选 d1约为n/2，d2为 d1/2， d3为 d2/2，…，di=1。
 * 
 * 当K=1时，此算法就等同于直接插入排序方法。由于前边大增量的处理，使关键字大体有序，因此最后一趟排序移动的记录少，处理速度快。
 * 有人在大量实验基础上推出，它的时间复杂长为 O(n ^ 1.3)。
 * 
 * 希尔排序是不稳定的。
 * 时间复杂度：它的时间复杂长为 O(n ^ 1.3 )
 * 空间复杂度：O(1)
 */

public class ShellSort implements ISort {
//    {
//        System.out.println("1111");
//    }
//    
//    public ShellSort() {
//        System.out.println("2222");
//    }
    @Override
    public int[] sort(int[] data) {
        final int n = data.length;
        int temp;

        for (int increment = n / 2; increment > 0; increment = increment / 2) {
            
            for (int i = increment, j; i < n; ++i) {
                temp = data[i];
                
                for (j = i - increment; j >= 0 && temp < data[j]; j -= increment) {
                    data[j + increment] = data[j];
                }
                
                data[j + increment] = temp;
            }
        }
        return data;
    }
}
