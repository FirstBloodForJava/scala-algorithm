package com.oycm.hot100.heap;

import java.util.*;

public class Solution_75 {

    /**
     * 347. <a href="https://leetcode.cn/problems/top-k-frequent-elements/description/">前 K 个高频元素</a>
     *
     * @param nums nums.length
     * @param k    k 的取值范围是 [1, 数组中不相同的元素的个数]
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        /*
        给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
         */
        /*
        先记录数组中相同元素出现频次情况，根据这个构建一个二元组最大堆，堆根据频次排序
         */
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int num : nums) {
            cnt.merge(num, 1, Integer::sum);
        }
        PriorityQueue<int[]> max = new PriorityQueue<>(cnt.size(), (a, b) -> b[0] - a[0]);
        for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
            max.add(new int[]{entry.getValue(), entry.getKey()});
        }
        int[] ans = new int[k];
        int i = 0;
        while (k-- > 0) {
            ans[i++] = max.poll()[1];
        }
        return ans;
    }

    public int[] bucketSort(int[] nums, int k) {
        /*
        先记录数组中相同元素出现频次情况，同时记录一个元素出现次数最大值 maxCnt
        创建一个桶，长度为 maxCnt，遍历 map，把 key 次数作为桶的下标，加入到桶中
        倒序遍历这个桶，由于题目保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的，顺序可以无要求
         */
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int num : nums) {
            cnt.merge(num, 1, Integer::sum);
        }
        int maxCnt = Collections.max(cnt.values());
        List<Integer>[] buckets = new List[maxCnt + 1];
        int[] ans = new int[k];
        Arrays.setAll(buckets, l -> new ArrayList<>());
        for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
            buckets[entry.getValue()].add(entry.getKey());
        }
        for (int i = maxCnt, j = 0; j < k; i--) {
            for (int val : buckets[i]) {
                ans[j++] = val;
            }
        }

        return ans;
    }

}
