package com.oycm.hot100.trick;

public class Solution_100 {

    /**
     * 287. <a href="https://leetcode.cn/problems/find-the-duplicate-number/description/">寻找重复数</a>
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        /*
        给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
        假设 nums 只有 一个重复的整数 ，返回 这个重复的数。
         */
        /*
        如果能修改数组，可以通过 nums[i] 和 nums[i] - 1 下标交换，最终 i != nums[i] - 1 的就是重复数
         */
        /*

         */
        int slow = 0;
        int fast = 0;
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) break;
        }
        int head = 0;
        while (head != slow) {
            slow= nums[slow];
            head = nums[head];
        }
        return slow;
    }

}
