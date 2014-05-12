package seker.algorithm.sort.merge;

import seker.algorithm.sort.ISort;

/**
 * 归并排序 (merge sort)
 * 归并的含义是将两个或两个以上的有序表合并成一个新的有序表。归并排序有多路归并排序、两路归并排序 , 可用于内排序，也可以用于外排序。
 * 这里仅对内排序的两路归并方法进行讨论。 
 * 
 * 1.两路归并排序算法思路
 *      ①把n个记录看成n个长度为l的有序子表；
 *      ②进行两两归并使记录关键字有序，得到n/2个长度为2的有序子表； 
 *      ③重复第②步直到所有记录归并成一个长度为n的有序表为止。
 *  【例】 有一组关键字{4,7,5,3,2,8,6,1},n=8,将其按由小到大的顺序排序。
 *  两路归并排序操作过程如下图 所示，其中l为子表长度。
 * 
 * 初始    [4] [7] [5] [3] [2] [8] [6] [1]  i=1
 * 第1趟  [4   7] [5   3] [2   8] [6   1]  i=2
 * 第2趟  [4   7   5   3] [2   8   6   1]  i=4
 * 第n趟  [4   7   5   3   2   8   6   1]  i=n
 * 
 * 
 * 2.算法实现
 * 此算法的实现不像图示那样简单，现分三步来讨论。
 *      首先从宏观上分析，首先让子表表长L=1进行处理；不断地使L=2*L，进行子表处理，直到L >= n为止，把这一过程写成一个主体框架函数mergesort。
 *      然后对于某确定的子表表长L，将n个记录分成若干组子表，两两归并，这里显然要循环若干次，把这一步写成一个函数 mergepass，可由 mergesort调用。
 *      最后再看每一组（一对）子表的归并，其原理是相同的，只是子表表长不同，换句话说，是子表的首记录号与尾记录号不同，把这个归并操作作为核心算法写成函数 merge，由 mergepass来调用。
 * 
 * 3.算法分析
 * （1）稳定性
 *      归并排序是一种稳定的排序。
 * （2）存储结构要求
 *      可用顺序存储结构。也易于在链表上实现。
 * （3）时间复杂度
 *      对长度为n的文件，需进行lgn趟二路归并，每趟归并的时间为O(n)，故其时间复杂度无论是在最好情况下还是在最坏情况下均是O(n*lgn)。
 * （4）空间复杂度
 *      需要一个辅助向量来暂存两有序子文件归并的结果，故其辅助空间复杂度为O(n)，显然它不是就地排序。
 * 注意：
 *      若用单链表做存储结构，很容易给出就地的归并排序。
 */
public class MergeSort implements ISort {
    int[] temp;
    
    @Override
    public int[] sort(int[] data) {
        temp = new int[data.length];
        mergesort(data, 0, data.length - 1);
        return data;
    }
    
    /**
     * 将 src[s...t]归并排序成tar[s...t]
     */
    private void mergesort(int[] data, int l, int h) {
        if (l < h) {
            int m = (l + h) / 2;
            
            mergesort(data, l, m);
            mergesort(data, m + 1, h);
            
            merge(data, l, m, h);
        }
    }
    
    /*
     * 将有序的src[l...m]和src[m+1...h]归并为有序的temp[l...h]
     */
    private void merge(int[] data, int l, int m, int h) {
        int index = l;
        int i, j;
        for (i = l, j = m + 1; i <= m && j <= h;) {
            if (data[i] < data[j]) {
                temp[index++] = data[i++];
            } else {
                temp[index++] = data[j++];
            }
        }
        
        while (i <= m) {
            temp[index++] = data[i++];
        }
        
        while (j <= h) {
            temp[index++] = data[j++];
        }
        
        for (int k = l; k <= h; k ++) {
            data[k] = temp[k];
        }
    }
}
