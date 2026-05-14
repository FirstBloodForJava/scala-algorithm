package com.oycm.month2026.may;

import java.util.ArrayList;
import java.util.List;

public class Solution_14 {

    /**
     * 2784. <a href="https://leetcode.cn/problems/check-if-array-is-good/description/">检查数组是否是好的</a> 1376
     *
     * @param nums 整数数组
     * @return
     */
    public boolean isGood(int[] nums) {
        /*
        一个整数数组 nums ，如果它是数组 base[n] 的一个排列，我们称它是个 好 数组。
        base[n] = [1, 2, ..., n - 1, n, n] （换句话说，它是一个长度为 n + 1 且包含 1 到 n - 1 恰好各一次，包含 n  两次的一个数组）。
        比方说，base[1] = [1, 1] ，base[3] = [1, 2, 3, 3] 。
        如果数组是一个好数组，请你返回 true ，否则返回 false 。
        注意：数组的排列是这些数字按任意顺序排布后重新得到的数组。
         */
        /*
        base[n], n = nums.length - 1
        nums[i] >= n, 不符合要求
        base[i] 初始次数都为 0，出现一次后，次数减少 1，如果减少后，次数小于 -1
         */
        int n = nums.length;

        int[] base = new int[n];
        base[n - 1] = 1;
        for (int i : nums) {
            if (i >= n) {
                return false;
            }
            base[i]--;
            if (base[i] < -1) {
                return false;
            }
        }

        return true;
    }

}

class Solution_442 {

    /**
     * 442. <a href="https://leetcode.cn/problems/find-all-duplicates-in-an-array/description/">数组中重复的数据</a>
     *
     * @param nums n = nums.length; n [1, 1e5]; 1 <= nums[i] <= n
     * @return
     */
    public List<Integer> findDuplicates(int[] nums) {
        /*
        一个长度为 n 的整数数组 nums ，其中 nums 的所有整数都在范围 [1, n] 内，且每个整数出现 最多两次。
        找出所有出现 两次 的整数，并以数组形式返回。
        时间复杂度 O(n) 且常量空间实现
         */
        /*
        如果 x = nums[i] 把 nums[x-1] 标记为 负数，后面遍历到 nums[x-1] < 0，则说明 x 出现了两次
         */
        /*
        另外解题思路：nums.length = n, 如果 [1, n] 中一个数字出现 2 次，则对应 [1, n] 中一个数字会不存在
        如果 nums[i] != nums[nums[i]-1] 把 nums[i] 这个值换到正确的下标 nums[i] - 1，nums[i] 换成原来下标 nums[i] - 1 的值；
            当前 nums[i] == nums[nums[i]-1] 时，说明这个下标已经正确，当前 i 下标再次出现已经正确的位置的值时，就不切换位置，这个位置可能就是缺失的数字，
            后面遍历到 nums[i] - 1 != i，就是出现了 2 次的数字
         */
        List<Integer> ans = new ArrayList<>();
        for (int x : nums) {
            x = Math.abs(x);
            int i = nums[x - 1];
            if (i < 0) {
                ans.add(x);
            } else {
                nums[x - 1] = -nums[x - 1];
            }
        }

        return ans;
    }

}
