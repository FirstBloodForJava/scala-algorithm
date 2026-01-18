package com.oycm.algorithm.d.binary_search_ans_max_to_min;

import java.util.Arrays;

public class Solution_4 {

    /**
     * 1552. <a href="https://leetcode.cn/problems/magnetic-force-between-two-balls/description/">两球之间的磁力</a> 1920
     * 和 2517 类似
     *
     * @param position
     * @param m
     * @return
     */
    public int maxDistance(int[] position, int m) {
        int n = position.length;
        Arrays.sort(position);
        int left = 0;
        int right = (position[n - 1] - position[0]) / (m - 1) + 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (check(position, mid) >= m) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public int check(int[] nums, int max) {
        int cnt = 1;
        int preMin = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - preMin >= max) {
                preMin = nums[i];
                cnt++;
            }
        }

        return cnt;
    }

}
