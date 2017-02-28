package seker.algorithm.sort.other;

import seker.algorithm.sort.ISort;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by seker on 28/2/17.
 */
public class BucketSort implements ISort {
    @Override
    public int[] sort(int[] data) {
    
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < data.length; i++){
            max = Math.max(max, data[i]);
            min = Math.min(min, data[i]);
        }
    
        //桶数
        int bucketNum = (max - min) / data.length + 1;
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<ArrayList<Integer>>(bucketNum);
        for(int i = 0; i < bucketNum; i++){
            bucketArr.add(new ArrayList<Integer>());
        }
    
        //将每个元素放入桶
        for(int i = 0; i < data.length; i++){
            int num = (data[i] - min) / (data.length);
            bucketArr.get(num).add(data[i]);
        }
    
        //对每个桶进行排序
        for(int i = 0; i < bucketArr.size(); i++){
            Collections.sort(bucketArr.get(i));
        }
        
        int index = 0;
        for (ArrayList<Integer> bucket : bucketArr) {
            for (int i : bucket) {
                data[index++] = i;
            }
        }
        return data;
    }
}
