package com.oycm.month2026.april;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution_23 {

    /**
     * 2615. <a href="https://leetcode.cn/problems/sum-of-distances/description/">等值距离和</a> 1793
     *
     * @param nums
     * @return
     */
    public long[] distance(int[] nums) {
        /*
        对于满足 nums[j] == nums[i] 且 j != i 的所有 j ，arr[i] 等于所有 |i - j| 之和。
        如果不存在这样的 j ，则令 arr[i] 等于 0 。
         */
        /*
        前缀和思路：把相同值的下标分组，下标数组看成一个柱状图，每个 i 就是求 nums[i] * i - preSum + sufSum - nums[i] * (m - i)
         */
        int n = nums.length;
        long[] arr = new long[n];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.computeIfAbsent(nums[i], l -> new ArrayList<>()).add(i);
        }
        // 前缀和
        long[] sums = new long[n + 1];
        for (List<Integer> value : map.values()) {
            int m = value.size();
            if (m < 2) {
                continue;
            }
            // 计算前缀和
            for (int i = 0; i < value.size(); i++) {
                sums[i + 1] = sums[i] + value.get(i);
            }
            for (int i = 0; i < value.size(); i++) {
                int height = value.get(i);
                long bottom = (long) height * i - sums[i];
                long top = sums[m] - sums[i] - (long) height * (m - i);
                arr[height] = bottom + top;
            }
        }
        return arr;
    }

}

class Solution_2615 {
    public long[] distance(int[] nums) {
        /*
        考虑前一个点和下一个点的距离变化量
        1, 3, 5, 7
        a0 = arr[1] = 2 + 4 + 6 = 12
        a1 = arr[3] = 2 + 2 + 4 = 8
        a1 和 a0 相比较，左边多个一个数 1，增加距离 3-1; 右边距离[(1,3), (1,5), (1,7)] 变成了 [(3,3), (3,5), (3,7)] 距离变短了 -2 * 3
        a1 = a0 + i * (diff) - diff * (m - i);
        a1 = a0 + (2 * i - m) * diff
         */
        int n = nums.length;
        long[] arr = new long[n];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.computeIfAbsent(nums[i], l -> new ArrayList<>()).add(i);
        }
        for (List<Integer> value : map.values()) {
            long s = 0;
            int start = value.get(0);
            for (int x : value) {
                s += x - start;
            }
            int m = value.size();
            arr[start] = s;
            for (int i = 1; i < m; i++) {
                arr[value.get(i)] = s += (2L * i - m) * (value.get(i) - value.get(i - 1));
            }
        }

        return arr;
    }
}
