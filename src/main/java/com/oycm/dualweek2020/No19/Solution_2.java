package com.oycm.dualweek2020.No19;

public class Solution_2 {

    /**
     * 1343. <a href="https://leetcode.cn/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold/description/">大小为 K 且平均值大于等于阈值的子数组数目</a> 1317
     *
     * @param arr
     * @param k
     * @param threshold
     * @return
     */
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        /*
        给你一个整数数组 arr 和两个整数 k 和 threshold 。
        请你返回长度为 k 且平均值大于等于 threshold 的子数组数目。
         */
        /*
        定长滑动窗口
         */
        int sum = 0;
        int cnt = 0;
        threshold *= k;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            int l = i - k + 1;
            if (l >= 0) {
                // 窗口形成
                if (sum >= threshold) cnt++;
                // l 出窗口
                sum -= arr[l];
            }
        }
        return cnt;
    }

}
