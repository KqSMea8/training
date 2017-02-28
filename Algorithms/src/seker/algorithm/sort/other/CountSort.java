package seker.algorithm.sort.other;

import seker.algorithm.sort.ISort;

/**
 * 计数排序
 *
 * Created by seker on 28/2/17.
 */
public class CountSort implements ISort {
    
    @Override
    public int[] sort(int[] data) {
        return countSort(data);
    }
    
    private int[] countSort(int[] data) {
        final int n = data.length;
        int[] temp = new int[n + 1];
        
        for (int aData : data) {
            temp[aData]++;
        }
        
        int z = 0;
        
        for (int i = 0; i <= n; i++) {
            while (temp[i]-- > 0) {
                data[z++] = i;
            }
        }
        return data;
    }
    
    // http://www.cnblogs.com/zer0Black/p/6169858.html
    
    public static int[] countSort1(int[] arr){
        if (arr == null || arr.length == 0) {
            return null;
        }
        
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        
        //找出数组中的最大最小值
        for(int i = 0; i < arr.length; i++){
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }
        
        int help[] = new int[max];
        
        //找出每个数字出现的次数
        for(int i = 0; i < arr.length; i++){
            int mapPos = arr[i] - min;
            help[mapPos]++;
        }
        
        int index = 0;
        for(int i = 0; i < help.length; i++){
            while(help[i]-- > 0){
                arr[index++] = i+min;
            }
        }
        
        return arr;
    }
    
    public static int[] countSort2(int[] arr){
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        
        //找出数组中的最大最小值
        for(int i = 0; i < arr.length; i++){
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }
        
        int[] help = new int[max - min + 1];
        
        //找出每个数字出现的次数
        for(int i = 0; i < arr.length; i++){
            int mapPos = arr[i] - min;
            help[mapPos]++;
        }
        
        //计算每个数字应该在排序后数组中应该处于的位置
        for(int i = 1; i < help.length; i++){
            help[i] = help[i-1] + help[i];
        }
        
        //根据help数组进行排序
        int res[] = new int[arr.length];
        for(int i = 0; i < arr.length; i++){
            int post = --help[arr[i] - min];
            res[post] = arr[i];
        }
        
        return res;
    }
}
