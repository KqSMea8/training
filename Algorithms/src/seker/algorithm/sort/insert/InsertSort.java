package seker.algorithm.sort.insert;

import seker.algorithm.sort.ISort;

/**
 * 这是一个对少量元素进行排序的有效算法。
 * 
 * 插入排序的工作机理与很多人打牌时，整理手中牌时的做法差不多。在开始摸牌时，我们左手是空的，牌面朝下放在桌上，接着，一次从桌上
 * 摸起一张牌，并将它插入到左手一把牌中正确的位置上。为了找到这张牌的正确位置，要将它与手中已有的每一张牌从右向左地进行比较。无
 * 论在什么时候，左手中的排都是排好序的，这些牌原先都是桌上那副牌里最顶上的一些牌。
 * 
 * 稳定的排序
 * 时间复杂度：1 + 2 + 3 + …… + N = O（N ^ 2）的复杂度。
 * 空间复杂度：O(1)
 */
public class InsertSort implements ISort {
    @Override
    public int[] sort(int[] data) {
        final int n = data.length;
        
        int sentinel;   // 哨兵

        for (int i = 1, j; i < n; i++) {
            sentinel = data[i];
            
            for (j = i - 1; j >= 0 && data[j] > sentinel; j--) {
                data[j + 1] = data[j];
            }
            
            data[j + 1] = sentinel;
        }
        return data;
    }
}
