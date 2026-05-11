package com.oycm.hot100.ordinary_array;

public class Solution_17 {

    /**
     * 41. <a href="https://leetcode.cn/problems/first-missing-positive/description/">缺失的第一个正数</a>
     *
     * @param nums 未排序的整数数组 nums[i] [-2^31, 2^31-1]
     * @return 找出其中没有出现的最小的正整数
     */
    public int firstMissingPositive(int[] nums) {
        /*
        题解思路：先思考暴力的做法
        O(n) 空间，hash 表记录 nums 所有元素，遍历 [1, n + 1]，如果哪个数不存在，哪个就是缺失的第一个正整数
        O(1) 空间，O(n^2) 时间，遍历 [1, n + 1] 看 nums 中是否存在该数
        对于一个长为 n 的数组，第一个最小的正整数，范围一定在 [1, n+1] 中。
            如果 [1, n] 都在数组中出现，答案就是 n+1；
            否则，答案就是 [1, n] 中的最小正整数。
        数组的长度恰好为 n，是否能设计一个 hash 表，表示整数在 [1, n] 中存在。
        遍历 nums[i] 如果 num = nums[i] <= n，对数组进行打标记，逻辑如下：
            nums[num-1] = -abs(nums[num-1]) 用负数符号标记这个数存在，前面的判断也需要假设绝对值处理；注意，后续再访问，负号一直存在
            这样打完标记后，第一个整数下标 + 1 就是答案
        因为 nums 中存在负数，所以需要对其进行预处理，将负数转换成不在 [1, n] 范围的正整数
         */
        int n = nums.length;
        // 预处理小于等于 0 的数
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }
        for (int i = 0; i < n; i++) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                // 打标记
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    public int firstMissingPositive_swap(int[] nums) {
        /*
        题解思路：交换位置
        记 x = nums[i]，把 x 交换到正确的位置。
            如果 x > 0 && x <= n，temp = nums[x-1]，把 nums[x-1] = x, x = temp 继续保持这个逻辑交换，直到 nums[x-1] == x
         */
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (0 < nums[i] && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                int j = nums[i] - 1;
                // 交换，把 nums[i] 交换到正确的位置
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            if (i + 1 != nums[i]) {
                return i + 1;
            }
        }
        return n + 1;
    }

}
