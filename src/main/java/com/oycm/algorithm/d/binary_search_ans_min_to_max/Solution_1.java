package com.oycm.algorithm.d.binary_search_ans_min_to_max;


public class Solution_1 {

    /**
     * 410. <a href="https://leetcode.cn/problems/split-array-largest-sum/description/">分割数组的最大值</a>
     * 将这个数组分成 k 个非空的连续子数组, 使得这 k 个子数组各自和的最大值 最小。
     *
     * @param nums
     * @param k
     * @return 分割后最小的和的最大值
     */
    public int splitArray(int[] nums, int k) {
        /*
        题解思路: 二分答案
        元素和的最大值越小, 需要的分段数就越多(k 越大); 元素和的最大值越大, 需要的分段数就越少(k 越小) 具有单调性, 可以尝试二分答案
        元素和(y) 随着 分割数(x) 递减, 当 分割数 <= k 时, 且 最大和 <= mid, 答案符合要求, 找一个最小的最大值
         */
        int mx = 0, sum = 0;
        for (int num : nums) {
            mx = Math.max(num, mx);
            sum += num;
        }
        int left = mx - 1;
        int ritht = sum;
        while (left + 1< ritht) {
            int mid = (left + ritht) / 2;
            if (check(nums, k, mid)) {
                ritht = mid;
            } else {
                left = mid;
            }
        }
        return ritht;
    }

    public boolean check(int[] nums, int k, int mid) {
        int cnt = 1;
        int s = 0;

        for (int num : nums) {
            if (s + num <= mid) {
                s += num;
                continue;
            }
            // 分割次数超了
            if (cnt == k) {
                return false;
            }
            cnt++;
            s = num;
        }

        return true;
    }

}
