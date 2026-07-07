package com.leetcode.interview.array;

import java.util.Random;

public class Solution_6 {


}

/**
 * 384. 打乱数组
 * <br>
 * 384. <a href="https://leetcode.cn/problems/shuffle-an-array/description/">打乱数组</a>
 *
 */
class Solution {

    /*
    给你一个整数数组 nums ，设计算法来打乱一个没有重复元素的数组。打乱后，数组的所有排列应该是 等可能 的。
    reset(): 设数组到它的初始状态并返回
    shuffle(): 返回数组随机打乱后的结果
     */

    int[] nums;
    int[] original;
    int n;

    public Solution(int[] nums) {
        this.nums = nums;
        n = nums.length;
        this.original = new int[nums.length];
        System.arraycopy(nums, 0, original, 0, n);
    }

    public int[] reset() {
        System.arraycopy(original, 0, nums, 0, n);
        return nums;
    }

    public int[] shuffle() {
        /*
        每个元素被选中第 1 个位置的概率 1/n
        每个元素被选中第 2 个位置的概率 n-1/n * 1/n-1 第一次不选中，第二次选中
        每个元素被选中第 3 个位置的概率 n-1/n * n-2/n-1 * 1/n-2 第一次不选中，第二次不选中，第三次选中
        都是 1/n
         */

        Random random = new Random();
        for (int i = 0; i < n; i++) {
            /*
            不移除元素，移除的元素和 待填位置交换
             */
            int j = i + random.nextInt(n - i);
            int temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;
        }

        return nums;
    }
}