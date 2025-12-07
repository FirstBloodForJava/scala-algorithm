package com.oycm.datastructure.heap.basic;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution_16 {

    /**
     * 2406. 将区间分为最少组数 1713
     * https://leetcode.cn/problems/divide-intervals-into-minimum-number-of-groups/description/
     * <p>
     * 同一组任意两个区间不相交，求最少需要划分多少个组
     *
     * @param intervals 二维数组
     * @return
     */
    public int minGroups(int[][] intervals) {
        /*
        如何分配才能用最少的组，让一个组的区间不相交
        先对数组按 intervals[i][0] 按升序排序，开始时，区间为空
        记录第一个区间 [l1, r1], 当 l2 分配时，有两种情况
            l2 <= r1, 不能分配原来的组
            l2 > l1, 可以继续使用原来的组
        由于 l 是有序，当 新进来的 l 和 历史的 各个组的 最小 r 相比，如果比它大，则可以任意分配，如果小于等于，则需要新开一个分组
        */
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int[] interval : intervals) {
            // 不为空，更新最小值
            if (!heap.isEmpty() && interval[0] > heap.peek()) {
                heap.poll();
            }
            heap.offer(interval[1]);
        }
        return heap.size();
    }

    public static void main(String[] args) {

    }
}
