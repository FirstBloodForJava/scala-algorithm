package com.leetcode.interview_question.h;

public class Solution_3 {

    /**
     * 面试题 08.03. <a href="https://leetcode.cn/problems/magic-index-lcci/description/">魔术索引</a>
     *
     * @param nums
     * @return
     */
    public int findMagicIndex(int[] nums) {
        /*
        魔术索引。 在数组 A[0...n-1]中，有所谓的魔术索引，满足条件 A[i] = i。
        给定一个 有序整数数组（存在相同元素的升序数组），编写一种方法找出魔术索引，若有的话，在数组 A 中找出一个魔术索引，如果没有，则返回-1。
        若有多个魔术索引，返回索引值最小的一个。
         */
        /*
        n <= 1000000
         */
        /*
        暴力的想法是，直接从小到达枚举。
        利用数组有序，从小到大搜索。
        如果 nums[i] > i，都是整数，等价 nums[i] >= i + 1。对于 [i, nums[i]) 元素，数组元素的值至少是 nums[i]。
            分类讨论：
            如果区间元素都相同 nums[nums[i]-1] = nums[i] 不等于下标 nums[i]-1；
            如果区间元素存在升序，nums[i] 本来就大于下标 i，中间也不会存在符合要求的下标；
            所以只需要考虑 nums[i] 是否和 nums[nums[i]] 的关系。
        如果 nums[i] < i，i++ 继续下一个比较；
        如果 nums[i] = i，返回 i。
         */
        for (int i = 0; i < nums.length; ) {
            if (nums[i] == i) return i;
            // 两者取最大，最坏的情况，当 nums[i] < i 时，时间复杂度 O(n)
            i = Math.max(nums[i], i + 1);
        }

        return -1;
    }

    public int binarySearch(int[] nums, int left, int right) {
        if (left > right) return -1;
        int mid = left + (right - left) >> 1;
        int leftAns = binarySearch(nums, left, mid - 1);
        if (leftAns != -1) {
            return leftAns;
        } else if (nums[mid] == mid) {
            return mid;
        }
        // 去右边查找
        return binarySearch(nums, mid + 1, right);
    }

}
