package seker.algorithm.sort.select;

import seker.algorithm.sort.ISort;

/**
 * 1.定义
 * 堆是n个元素的有限序列{ K1,K2,…,Kn }，它当且仅当满足如下关系：
 *    k(i) <= k(2i)
 *    k(i) <= k(2i+1)
 * 这是一个上小、底大的堆。若是一个上大、底小的堆，只需把“ <= ”改为“ >= ”即可。
 * 
 * 堆是一种数据元素之间的逻辑关系，常用向量做存储结构。
 * 对于满二叉树，当对它的结点 {由上而下，自左至右} 编号之后，编号为 i 的结点是编号为 2i 和 2i+1 结点的双亲。
 * 反过来讲，结点 2i 是结点 i 的左孩子，结点 2i+1 是结点 i 的右孩子。
 * 下图 表示完全二叉树和它在向量中的存储状态。结点编号对应向量中的下标号。 
 * 数据：[12][14][15][16][18][17]
 * 下标：    1   2   3   4   5   6
 * 
 *                     12(1)
 *                   /      \
 *               14(2)      15(3)
 *                /  \      /  \
 *            16(4) 18(5)  17(6)  
 * 
 * 用堆的概念分析向量中的数据，它显然满足（上小、底大）堆的关系。不难看出满足堆的逻辑关系的一组数据，可画成二叉树的形状，
 * 并且它是一棵完全二叉树树形。因此，也可借助完全二叉树来描述堆的概念。
 * 
 * 若完全二叉树中任一非叶子结点的值小于等于（或大于等于）其左、右孩子结点的值，则从根结点开始按结点编号排列所得的结点序列就是一个堆。
 * 
 * 
 * 2、堆排序的算法思想
 * 堆排序利用了大根堆(或小根堆)堆顶记录的关键字最大(或最小)这一特征，使得在当前无序区中选取最大(或最小)关键字的记录变得简单。
 * 
 * （1）用大根堆排序的基本思想
 *       ① 先将初始文件R[1..n]建成一个大根堆，此堆为初始的无序区
 *       ② 再将关键字最大的记录R[1](即堆顶)和无序区的最后一个记录R[n]交换，由此得到新的无序区R[1..n-1]和有序区R[n]，且满足R[1..n-1].keys≤R[n].key
 *       ③ 由于交换后新的根R[1]可能违反堆性质，故应将当前无序区R[1..n-1]调整为堆。
 *       ④ 然后再次将R[1..n-1]中关键字最大的记录R[1]和该区间的最后一个记录R[n-1]交换，由此得到新的无序区R[1..n-2]和有序区R[n-1..n]，且仍满足关系R[1..n-2].keys≤R[n-1..n].keys，同样要将R[1..n-2]调整为堆。
 *       ……
 *       ⑤ 直到无序区只有一个元素为止。
 * 
 * （2）大根堆排序算法的基本操作：
 *       ① 初始化操作：将R[1..n]构造为初始堆；
 *       ② 每一趟排序的基本操作：将当前无序区的堆顶记录R[1]和该区间的最后一个记录交换，然后将新的无序区调整为堆(亦称重建堆)。
 * 
 * 注意：
 *       ①只需做n-1趟排序，选出较大的n-1个关键字即可以使得文件递增有序。
 *       ②用小根堆排序与利用大根堆类似，只不过其排序结果是递减有序的。堆排序和直接选择排序相反：在任何时刻，堆排序中无序区总是在有序区之前，且有序区是在原向量的尾部由后往前逐步扩大至整个向量为止。
 *
 * 3.算法时间复杂度
 *       堆排序中 heap 算法的时间复杂度与堆所对应的完全二叉树的树高度 log2n 相关。而 heapsort 中对 heap 的调用数量级为n，所以堆排序的整个时间复杂度为O(nlog2n) 。并且堆排序是不稳定的。 
 */

public class HeapSort implements ISort {
    
    @Override
    public int[] sort(int[] data) {
        final int n = data.length;
        int temp = 0;
        
        // build heap.
        for (int i = n / 2 - 1; i >= 0; i--) {
            // adjust heap.
            heapPass(data, i, n);
        }
        
        for (int i = n - 1; i > 0; i--) {
            temp = data[i];
            data[i] = data[0];
            data[0] = temp;
            
            // adjust heap.
            heapPass(data, 0, i);
        }
        return data;
    }
    
    /**
     * 
     * @param data  data
     * @param i     the index of root node [0, 1, 2..., n/2-1]
     * @param n     [i, i+1, i+2,..., n-1] is not sorted.
     */
    private void heapPass(int[] data, int i, int n) {
        int temp = data[i];
        int j = 2 * i + 1;                          // data[j] is the left child.
        
        while (j <= n - 1) {
            if (j < n - 1 && data[j] < data[j + 1]) {   // Compare left child and right child.
                j++;                                    // data[j] is the bigger child.
            }
            
            if (temp >= data[j]) {
                break;                          // The parent's value is bigger than left child and right child.
            } else {
                data[(j - 1) / 2] = data[j];    //(j-1)/2 is the parent of j.
                j = 2 * j + 1;                  // Left child again, to recursive.
            }
        }
        data[(j - 1) / 2] = temp;
    }
}
