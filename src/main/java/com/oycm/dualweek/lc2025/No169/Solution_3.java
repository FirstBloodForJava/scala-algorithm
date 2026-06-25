package com.oycm.dualweek.lc2025.No169;

public class Solution_3 {

    /**
     * 3738. <a href="https://leetcode.cn/problems/longest-non-decreasing-subarray-after-replacing-at-most-one-element/description/">替换至多一个元素后最长非递减子数组</a> 1811
     *
     * @param nums
     * @return
     */
    public int longestSubarray(int[] nums) {
        /*
        给你一个整数数组 nums。
        你被允许 最多 将数组中的一个元素替换为任何其他整数值。
        返回在执行至多一次替换后，可以获得的 最长非递减子数组 的长度。

        如果数组中的每个元素都大于或等于其前一个元素（如果存在），则称该数组为 非递减 的。
         */
        /*
        1 <= nums.length <= 1e5
        -1e9 <= nums[i] <= 1e9
         */
        int n = nums.length;
        // f[i] 表示 [i, n-1] 最长非递减数组长度
        int[] suf = new int[n];
        suf[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            suf[i] = nums[i] <= nums[i + 1] ? suf[i + 1] + 1 : 1;
        }
        int ans = suf[0];
        if (ans == n) return ans;
        int pre = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i - 1] > nums[i]) {
                /*
                num[i-1] nums[i] nums[i+1]
                修改：
                    如果 nums[i-1] <= nums[i+1] 修改 nums[i] 为中间值（此时为最优解），非递减数组长度为 pre + 1 + suf[i+1]
                    如果 nums[i-1] > nums[i+1]:
                        修改 nums[i] >= nums[i-1]，非递减数组长度为 pre + 1；
                        修改 nums[i-1] <= nums[i]，非递减数组长度为 1 + suf[i]
                 */
                if (i + 1 < n) {
                    if (nums[i - 1] <= nums[i + 1]) {
                        ans = Math.max(ans, pre + 1 + suf[i + 1]);
                    } else {
                        int temp = Math.max(pre + 1, 1 + suf[i]);
                        if (i - 2 >= 0 && nums[i - 2] <= nums[i]) {
                            temp = Math.max(temp, suf[i] + pre);
                        }
                        ans = Math.max(ans, temp);
                    }

                } else {
                    ans = Math.max(ans, pre + 1);
                }
                pre = 1;
            } else {
                pre++;
                ans = Math.max(pre, ans);
            }
        }

        return ans;
    }

    public int longestSubarray_1(int[] nums) {
        /*
        写法优化，必须 判断 n = 1 情况，不适用 n = 2
         */
        int n = nums.length;
        int ans = 2;
        if (n <= 2) return ans;

        int[] suf = new int[n];
        suf[n - 1] = 1;

        for (int i = n - 2; i > 0; i--) {
            if (nums[i] > nums[i + 1]) {
                suf[i] = 1;
            } else {
                suf[i] = suf[i + 1] + 1;
                // 把 nums[i-1] 修改为小于 nums[i]
                ans = Math.max(ans, suf[i] + 1);
            }
        }
        int pre = 1;
        for (int i = 1; i < n - 1; i++) {
            if (nums[i - 1] <= nums[i + 1]) {
                // 拼接 pre[0, i-1] nums[i] [i+1, n-1]
                ans = Math.max(ans, pre + 1 + suf[i + 1]);
            }
            if (nums[i - 1] <= nums[i]) {
                pre++;
                // 把 nums[i+1] 修改
                ans = Math.max(ans, pre + 1);
            } else {
                pre = 1;
            }
        }

        return ans;
    }

}
