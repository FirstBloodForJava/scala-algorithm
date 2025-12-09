package com.oycm.algorithm.e.ea.advance;

import java.util.HashMap;
import java.util.Map;

public class Solution_8 {

    /**
     * 2555. <a href="https://leetcode.cn/problems/maximize-win-from-two-segments/description/">两个线段获得的最多奖品</a> 2081
     *
     * @param prizePositions n [0, 10^5] prizePositions[i] 表示第 i 件奖品在 X 轴上的位置，按照非递减顺序排序（意味着一个位置可能有多个奖品）
     * @param k              [0, 10^9]
     * @return 求选择一个长为 k 的两个线段（可以相交），能获得的最多奖品
     */
    public static int maximizeWin(int[] prizePositions, int k) {
        /*
        假设 长为 k 的线段相交必然存在一个对应不相交的线段，线段越不相交，答案才越大
        怎么统计计算一段区间两个长为 k 不相交的答案？[1,2*k]
        可以使用二分查找 [nums[i], nums[i] + k] 大于 nums[i] + k 的第一个index index - i 就是一段以 index之前为一段，之后为一段的最大值
        查找之前，查询当前 i 是否存在之前的答案
        这里逻辑有什么问题？ map 获取到的不一定是最大的
        应该维护当前点前面的最大值点数 + 当前点右边的奖品数
         */
        int ans = 1;
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < prizePositions.length; i++) {
//            int r1 = lowerBound(prizePositions, i - 1, prizePositions[i] + k);
//            ans = Math.max(ans, r1 - i + map.getOrDefault(i, 0));
//            map.merge(r1, r1 - i, Math::max);
//        }
        int n = prizePositions.length;
        int pre = 1;
        // 查找左边的最大值
        for (int i = 1; i < n; i++) {
            // >= nums[i] - k 的 index
            int left = lowerBound(prizePositions, -1, prizePositions[i - 1] - k);
            pre = Math.max(pre, i - left);
            int right = lowerBound(prizePositions, i - 1, prizePositions[i] + k + 1);
            // 怎么判断是否删除元素
            ans = Math.max(ans, pre + right - i);
        }

        return ans;
    }

    public static int lowerBound(int[] nums, int l, int target) {
        int r = nums.length;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;

    }

    public static void main(String[] args) {
        maximizeWin(new int[]{1, 2, 3, 4}, 0);
    }


}
