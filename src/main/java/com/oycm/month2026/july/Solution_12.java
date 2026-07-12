package com.oycm.month2026.july;

import java.util.Arrays;

public class Solution_12 {

    /**
     * 1331. 数组序号转换
     * <br>
     * 1331. <a href="https://leetcode.cn/problems/rank-transform-of-an-array/description/">数组序号转换</a> 1356
     *
     * @param arr
     * @return
     */
    public int[] arrayRankTransform(int[] arr) {
        /*
        给你一个整数数组 arr ，请你将数组中的每个元素替换为它们排序后的序号。
        序号代表了一个元素有多大。序号编号的规则如下：
            序号从 1 开始编号。
            一个元素越大，那么序号越大。如果两个元素相等，那么它们的序号相同。
            每个数字的序号都应该尽可能地小。
         */
        /*
        其它：排序去重，二分查找
         */
        int n = arr.length;
        Integer[] idx = new Integer[n];
        Arrays.setAll(idx, i -> i);
        Arrays.sort(idx, (a, b) -> arr[a] - arr[b]);

        int pre = 0;
        if (n > 0) {
            pre = arr[idx[0]];
            arr[idx[0]] = 1;
        }

        for (int i = 1; i < n; i++) {
            int cur = arr[idx[i]];
            arr[idx[i]] = arr[idx[i-1]];
            if (cur > pre) {
                arr[idx[i]]++;
            }
            pre = cur;
        }

        return arr;
    }

}
