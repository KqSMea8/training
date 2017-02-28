package seker.algorithm.sort.swap;

import seker.algorithm.sort.ISort;

/**
 * 冒泡排序算法的思想：
 *      很简单，每次遍历完序列都把最大（小）的元素放在最前面，然后再对剩下的序列从父前面的一个过程，
 *      每次遍历完之后待排序序列就少一个元素，当待排序序列减小为只有一个元素的时候排序就结束了。
 *      因此，复杂度在最坏的情况下是O（N ^ 2）。 
 *      
 *      时间复杂度：1 + 2 + 3 + …… + N = O（N ^ 2）的复杂度。 
 *      空间复杂度：O(1)
 */
public class BubbleSort implements ISort {
    
    @Override
    public int[] sort(int[] data) {
        int n = data.length;
        int temp;
        boolean swap;
        
        for (n = n - 1; n > 0; n--) {
            swap = false;
            for (int i = 0; i < n; i++) {
                if (data[i] > data[i + 1]) {
                    temp = data[i];
                    data[i] = data[i + 1];
                    data[i + 1] = temp;
                    
                    swap = true;
                }
            }
            if (!swap) {
                break;
            }
        }
        return data;
    }
}
