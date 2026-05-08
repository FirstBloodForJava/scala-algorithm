package com.oycm.hot100.hash;

import java.util.HashMap;
import java.util.Map;

public class Solution_1 {

    /**
     * 1. <a href="https://leetcode.cn/problems/two-sum/description/">两数之和</a>
     *
     * @param nums 整数数组
     * @param target 整数目标值
     * @return 目标和为 target 两个元素下标
     */
    public int[] twoSum(int[] nums, int target) {
        /*
        在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
        你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。
         */
        /*
        a + b = target => a = target - b
        记录前面遍历过的 nums[i] 及 i，查找前面是否存在 target - nums[i] 的元素
         */
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer key = map.get(target - nums[i]);
            if (key != null) {
                return new int[]{i, key};
            }
            map.put(nums[i], i);
        }
        return null;
    }

}
