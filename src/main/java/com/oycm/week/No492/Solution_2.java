package com.oycm.week.No492;

public class Solution_2 {

    public int smallestBalancedIndex(int[] nums) {
        /*
        平衡下标: i 左侧所有元素的总和等于 i 右侧所有元素的乘积
        左侧没有元素，则总和视为 0
        右侧没有元素，则乘积视为 1
        前缀和 + 从右到左遍历
        sums[i] = nums[i+1] * ... nums[n-1]
        i 右移
        sums[i] 减少, p 变大 后续就不可能再相等第一个就是答案
         */
        int n = nums.length;
        long[] sums = new long[n + 1];
        for (int i = 0; i < nums.length; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
        int ans = -1;
        long p = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (sums[i] == p) {
                return i;
            }
            // 大于前缀和也不可能
            if (p > sums[i]) {
                break;
            }
            p *= nums[i];
        }

        return ans;
    }

}
