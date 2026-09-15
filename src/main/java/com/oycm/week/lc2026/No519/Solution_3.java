package com.oycm.week.lc2026.No519;

public class Solution_3 {

    /**
     * 统计影子数对 I
     *
     * @param nums
     * @return
     */
    public long shadowPairs(int[] nums) {
        /*
        给你一个长度为 n 的整数数组 nums。
        如果一对下标 (i, j) 满足以下所有条件，则称其为一个影子对：
            0 <= i < j < n
            nums[i] < nums[j]
            不存在下标 k，使得 i < k < j 且 nums[k] < nums[i] < nums[j]。
        返回影子对的总数。
         */
        int n = nums.length;
        long ans = 0;
        int[] stVal = new int[n];
        int[] stCnt = new int[n];
        int top = 0;
        // 栈中元素数量
        long total = 0;

        for (int j = 0; j < n; j++) {
            int v = nums[j];
            // 栈顶元素 和 v 后续下标无法组成答案因为 nums[k] < nums[i] 成立
            while (top > 0 && stVal[top - 1] > v) {
                total -= stCnt[--top];
            }

            ans += total;

            if (top > 0 && stVal[top - 1] == v) {
                // 相等不能构成影子对
                ans -= stCnt[top - 1];
                stCnt[top - 1]++;
            } else {
                stVal[top] = v;
                stCnt[top] = 1;
                top++;
            }
            total++;
        }
        return ans;
    }
}
