/**
 * 排序的基本概念 
 * 所谓排序，就是要整理文件中的记录，使之按关键字递增(或递减)次序排列起来。其确切定义如下：
 *         输入：n个记录R1，R2，…，Rn，其相应的关键字分别为K1，K2，…，Kn。
  *        输出：Ril，Ri2，…，Rin，使得Ki1 ≤ Ki2 ≤ … ≤ Kin。(或Ki1 ≥ Ki2 ≥ … ≥ Kin)。
 * 
 * （1）排序的分类
 *      1）．按是否涉及数据的内、外存交换分
 *         在排序过程中，若整个文件都是放在内存中处理，排序时不涉及数据的内、外存交换，则称之为内部排序(简称内排序)；反之，若排序过程中要进行数据的内、外存交换，则称之为外部排序。
 *      注意：
 *         ①  内排序适用于记录个数不很多的小文件
 *         ②  外排序则适用于记录个数太多，不能一次将其全部记录放人内存的大文件。
 *      2）．按策略划分内部排序方法
 *         可以分为五类：插入排序、选择排序、交换排序、归并排序和分配排序。
 * 
 * （2）排序算法的基本操作
 *         大多数排序算法都有两个基本的操作：
 *         1）   比较两个关键字的大小；
 *         2）   改变指向记录的指针或移动记录本身。
 *      注意：
 *         第2）种基本操作的实现依赖于待排序记录的存储方式。
 * 
 * （3）排序算法性能评价
 *      1）.评价排序算法好坏的标准
 *         评价排序算法好坏的标准主要有两条：
 *         ①  所需的辅助空间
 *         ②  算法本身的复杂程度
 *      2）.排序算法的空间复杂度
 *         若排序算法所需的辅助空间并不依赖于问题的规模n，即辅助空间是O(1)，则称之为就地排序(In-PlaceSou)。
 *         非就地排序一般要求的辅助空间为O(n)。
 *      3）.排序算法的时间开销
 *         大多数排序算法的时间开销主要是关键字之间的比较和记录的移动。有的排序算法其执行时间不仅依赖于问题的规模，还取决于输入实例中数据的状态。
 */
package seker.algorithm.sort;

import seker.algorithm.sort.insert.BinaryInsertSort;
import seker.algorithm.sort.insert.InsertSort;
import seker.algorithm.sort.insert.ShellSort;
import seker.algorithm.sort.merge.MergeSort;
import seker.algorithm.sort.radix.RadixSort;
import seker.algorithm.sort.select.HeapSort;
import seker.algorithm.sort.select.SelectSort;
import seker.algorithm.sort.swap.BubbleSort;
import seker.algorithm.sort.swap.QuickSort;

public class SortTest {
    public static final int N    = 100000;
    public static final int BASE = N;

    int[]                   data = new int[N];

    public String dataToString(int[] data) {
        StringBuilder buffer = new StringBuilder("data = ");
        for (int i = 0; i < 100; i++) {
            buffer.append(data[i]).append(", ");
        }
        return buffer.delete(buffer.length() - 2, buffer.length()).toString();
    }

    public SortTest() {
        for (int i = 0; i < N; i++) {
            data[i] = (int) (Math.random() * BASE);
        }
//        System.out.println(String.format("%1$-16s:%2$s", "Unordered ", dataToString(data)));

        ISort[] sorts = new ISort[] { 
                new InsertSort(), 
                new BinaryInsertSort(), 
                new ShellSort(), 
                
                new MergeSort(),
                
                new RadixSort(),
                
                new SelectSort(),
                new HeapSort(),
                
                new BubbleSort(), 
                new QuickSort(),
            };
        sorts = new ISort[] { 
                new ShellSort(), 
                new MergeSort(),
                new HeapSort(),
                new QuickSort(),
            };

        int[] clone = new int[data.length];
        for (ISort sort : sorts) {
            System.arraycopy(data, 0, clone, 0, data.length);
            
            long start = System.currentTimeMillis();
            sort.sort(clone);
            long end = System.currentTimeMillis();
            long cost = end - start;
            System.out.println(String.format("%1$-16s(%2$-5d):%3$s", sort.getClass().getSimpleName(), cost, dataToString(clone)));
//            System.out.println(String.format("%1$-16s(%2$-4d)", sort.getClass().getSimpleName(), cost));
        }
    }

    public static void main(String[] args) {
        new SortTest();
    }
}
