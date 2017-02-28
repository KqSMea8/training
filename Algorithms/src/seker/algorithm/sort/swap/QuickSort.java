package seker.algorithm.sort.swap;

import seker.algorithm.sort.ISort;

/**
 * 快速排序由霍尔 (Hoare) 提出，它是一种对冒泡排序的改正。由于其排序速度快，故称快速排序 (quick sort) 。
 * 快速排序方法的实质是将一组关键字 [K1 ,K2,…,Kn] 进行分区交换排序。 
 * 
 * 1.算法思路
 *       ①以第一个关键字 K1 为控制字，将 [K1 ,K2, … ,Kn] 分成两个子区，使左区所有关键字小于等于 K1 ，右区所有关键字大于等于 K1 ，最后控制字居两个子区中间的适当位置。在子区内数据尚处于无序状态。 
 *       ②将右区首、尾指针(记录的下标号)保存入栈，对左区进行与第①步相类似的处理，又得到它的左子区和右子区，控制字居中。 
 *       ③重复第①、②步，直到左区处理完毕。然后退栈对一个个右子区进行相类似的处理，直到栈空。 
 *       由以上三步可以看出：
 *          快速排序算法总框架是进行多趟的分区处理；
 *          而对某一特定子区，则应把它看成又是一个待排序的文件，控制字总是取子区中第一个记录的关键字。
 *          现在设计一个函数 hoare ，它仅对某一待排序文件进行左、右子区的划分，使控制字居中；
 *          再设计一个主体框架函数 quicksort ，在这里多次调用 hoare 函数以实现对整个文件的排序。
 *  
 * 2.快速排序算法分析
 *       快速排序的非递归算法引用了辅助栈，它的深度为 log2n 。假设每一次分区处理所得的两个子区长度相近，那么可入栈的子区长度分别为：
 *       (n/2) ^ 1,
 *       (n/2) ^ 2,
 *       (n/2) ^ 3,
 *       (n/2) ^ 4,
 *        … 
 *       (n/2) ^ k，
 *       又因为 n/2k=1, 所以 k=log2n 。分母中2的指数恰好反映出需要入栈的子区个数，它就是 log2n ，也即栈的深度。
 *       在最坏情况下，比如原文件关键字已经有序，每次分区处理仅能得到一个子区。可入的子区个数接近 n, 此时栈的最大深度为 n. 
 *       快速排序主体算法时间运算量约 O(log2n) ，划分子区函数运算量约 O(n) ，所以总的时间复杂度为 O(nlog2n) ，它显然优于冒泡排序 O(n2).
 *       可是算法的优势并不是绝对的。试分析，当原文件关键字有序时，快速排序时间复杂度是 O(n2), 这种情况下快速排序不快。而这种情况的冒泡排序是 O(n),反而很快。
 *       在原文件记录关键字无序时的多种排序方法中，快速排序被认为是最好的一种排序方法。
 */
public class QuickSort implements ISort {
    
    @Override
    public int[] sort(int[] data) {
        quickSort(data, 0, data.length - 1);
        return data;
    }
    
    private void quickSort(final int[] data, final int low, final int high) {
        int pivot = partition(data, low, high);
        if (pivot > low + 1) {
            quickSort(data, low, pivot - 1);
        }
        
        if (pivot < high - 1) {
            quickSort(data, pivot + 1, high);
        }
    }
    
    private int partition(final int[] data, int low, int high) {
        while (low < high) {
            while (low < high && data[high] >= data[low]) {
                high--;
            }
            if (data[high] < data[low]) {
                int temp = data[low];
                data[low] = data[high];
                data[high] = temp;
            }
            
            while (low < high && data[low] <= data[high]) {
                low++;
            }
            if (data[low] > data[high]) {
                int temp = data[low];
                data[low] = data[high];
                data[high] = temp;
            }
        }
        return low;
    }
}
