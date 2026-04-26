package com.oycm.week.No499;

import java.util.ArrayList;
import java.util.List;

public class Solution_1 {

    /**
     * 3912. <a href="https://leetcode.cn/problems/valid-elements-in-an-array/description/">数组中的有效元素</a>
     *
     * @param nums 整数数组
     * @return
     */
    public List<Integer> findValidElements(int[] nums) {
        /*
        如果元素 nums[i] 满足以下 至少一个 条件，则认为它是 有效 元素：
            它 严格大于 其左侧的所有元素。
            它 严格大于 其右侧的所有元素。
        第一个元素和最后一个元素始终有效。
        返回所有有效元素组成的数组，顺序与它们在 nums 中出现的顺序相同。
         */
        /*
        维护一个 lMax,
        rMax[i] 表示 i 右边的最大值
         */
        int n = nums.length;
        int lMax = nums[0];
        int[] rMax = new int[n];
        rMax[n - 1] = nums[n - 1];
        for (int i = n - 2; i > 0; i--) {
            rMax[i] = Math.max(nums[i + 1], rMax[i + 1]);
        }
        List<Integer> ans = new ArrayList<>();
        ans.add(nums[0]);
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > lMax || nums[i] > rMax[i]) {
                ans.add(nums[i]);
            }
            lMax = Math.max(lMax, nums[i]);
        }
        if (n > 1) {
            ans.add(nums[n - 1]);
        }
        return ans;
    }

}
