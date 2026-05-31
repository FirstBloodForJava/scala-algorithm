package com.oycm.week2026.No504;

import java.util.*;

public class Solution_4 {

    public int[] maximumMEX(int[] nums) {
        /*
        给你一个整数数组 nums。
        你需要构造一个数组 result，具体做法是重复执行以下操作，直到 nums 变为空：
            选择一个整数 k，满足 1 <= k <= len(nums)。
            计算 nums 的前 k 个元素的 MEX。
            将这个 MEX 附加到 result。
            从 nums 中移除前 k 个元素。
        返回执行这些操作后能得到的字典序最大的数组 result。
        数组的 MEX 是指数组中不包含的最小非负整数。
        如果两个数组 a 和 b 在第一个不同的下标处，数组 a 的元素大于数组 b 的对应元素，
        则数组 a 字典序大于数组 b。如果前 min(a.length, b.length) 个元素都相同，那么较长的数组是字典序更大的数组。
         */
        /*
        要想字典序大，第一个 MEX 就要大。若 MEX = m，m > 0，
            则一定存在一个子数组包含 [0, m-1] 所有值，
         */
        int n = nums.length;

        Map<Integer,List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            // 记录相同值得所有下标
            map.computeIfAbsent(nums[i], l -> new ArrayList<>()).add(i);
        }
        // 不包含最小值 0，每次删除一个元素，所有 MEX = 0
        if (map.get(0) == null) {
            return new int[n];
        }
        List<Integer> ans = new ArrayList<>();
        int left = 0;
        while (left < n) {
            int curMex = -1;
            int right = left;
            while (true) {
                int nextMex = curMex + 1;
                List<Integer> list = map.get(nextMex);
                if (list == null) break;
                // 查找 nextMex 是否在要移除得区间中 >= left
                int idx = Collections.binarySearch(list, left);
                if (idx < 0 && -(idx + 1) == list.size()) {
                    break;
                }
                curMex = nextMex;
                right = Math.max(right, list.get(idx > 0 ? idx : -(idx + 1)));
            }
            ans.add(curMex + 1);
            left = right + 1;
        }

        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}
