package com.oycm.week.No493;

public class Solution_3 {

    public int longestArithmetic(int[] nums) {
        /*

         */

        int n = nums.length;
        if (n <= 2) return n;

        int ans = 2;

        // dpEnd[i] 表示以 i 结尾的最长等差子数组长度
        int[] dpEnd = new int[n];
        // dpStart[i] 表示以 i 开头的最长等差子数组长度
        int[] dpStart = new int[n];

        dpEnd[0] = 1;
        for (int i = 1; i < n; i++) {
            if (i == 1 || nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                dpEnd[i] = dpEnd[i - 1] + 1;
            } else {
                dpEnd[i] = 2;
            }
            ans = Math.max(ans, dpEnd[i]);
        }
        dpStart[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (i == n - 2 || nums[i + 1] - nums[i] == nums[i + 2] - nums[i + 1]) {
                dpStart[i] = dpStart[i + 1] + 1;
            } else {
                dpStart[i] = 2;
            }
        }

        // 尝试修改每个位置
        for (int i = 0; i < n; i++) {
            /*
            情况1: 修改 nums[i] 使其满足左边等差数列的延续
             */
            if (i >= 2) {
                int diff = nums[i - 1] - nums[i - 2];
                int expected = nums[i - 1] + diff;
                // 修改 nums[i] 为 expected
                int len = dpEnd[i - 1] + 1;
                if (i + 2 < n && nums[i + 1] - expected == diff && nums[i + 2] - nums[i + 1] == diff) {
                    len += dpStart[i + 1];
                } else if (i + 1 < n && nums[i + 1] - expected == diff) {
                    len++;
                }
                ans = Math.max(ans, len);
            }

            /*
            情况2: 修改 nums[i] 使其满足右边等差数列的延续
             */
            if (i + 2 < n) {
                int diff = nums[i + 2] - nums[i + 1];
                int expected = nums[i + 1] - diff;
                // 修改 nums[i] 满足右边
                int len = dpStart[i + 1] + 1;
                if (i - 2 >= 0 && expected - nums[i - 1] == diff && nums[i - 1] - nums[i - 2] == diff) {
                    len += dpEnd[i - 1];
                } else if (i - 1 >= 0 && expected - nums[i - 1] == diff) {
                    len++;
                }
                ans = Math.max(ans, len);
            }
            /*
            情况3: 修改 nums[i] 使其成为连接左右两段等差数列的桥梁
             */
            if (i >= 2 && i + 2 < n) {
                int diffLeft = nums[i - 1] - nums[i - 2];
                int diffRight = nums[i + 2] - nums[i + 1];
                // 值要大于
                if (diffLeft == diffRight && nums[i - 1] + 2 * diffRight == nums[i + 1]) {
                    ans = Math.max(ans, dpEnd[i - 1] + 1 + dpStart[i + 1]);
                }
            }
            System.out.println(ans);
        }

        return ans;

    }

    public static void main(String[] args) {
        System.out.println(new Solution_3().longestArithmetic(new int[]{70500, 81869, 26980, 41672, 17202, 62313, 92481, 52535, 97646, 42757}));
    }

}
