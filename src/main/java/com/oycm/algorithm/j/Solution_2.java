package com.oycm.algorithm.j;

import java.util.Random;

public class Solution_2 {

    /**
     * 215. <a href="https://leetcode.cn/problems/kth-largest-element-in-an-array/description/">数组中的第K个最大元素</a>
     *
     * @param nums
     * @param k
     * @return 找的是数组排序后的第 k 个最大的元素
     */
    public int findKthLargest(int[] nums, int k) {
        /*
        题解思路：第 k 大的元素，在升序数组中的位置是 n - k
            1. 在 nums 中随机选择一个基准元素 pivot
            2. 划分 nums。通过交换，把 < pivot 的元素放在 pivot 的左侧；把 >= pivot 的元素放在 pivot 的右侧。这样划分之后，可以粗略地排序 nums。
            划分后， pivot 此刻的位置就等于 pivot 在升序数组中的位置。
                确定基准元素之前，不知道排第几，而是经过第二部的处理后，得到下标 i。
            3. 设 pivot 在 nums 中的下标为 i。
                如果 i == n - k，那么答案就是 pivot
                如果 i > n - k，说明答案在 pivot 左侧，在左侧继续寻找，回到第一步
                如果 i < n - k，说明答案在 pivot 右侧，在右侧继续寻找，回到第一步
        注意：如果按照 < pivot 和 >= pivot 划分数组，数组中存在大量相同元素时，划分后的 i 往往是子数组的第一个元素下标，算法可能退化成 n^2
        修改第二步，把 < 换成 <=，左侧元素是 <= pivot 元素，如果子数组中元素都相同，就会返回子数组中心下标

        如果记录 pivot 在 nums 中的下标？
            先在 nums 子数组中找到随机下标，把这个下标的值和子数组第一个下标交换。
        如何进行交换？
         */
        int n = nums.length;
        // 目标下标
        int targetIndex = n - k;
        int left = 0;
        int right = n - 1; // 闭区间
        while (true) {
            int i = partition(nums, left, right);
            if (i == targetIndex) {
                // 找到第 k 大元素
                return nums[i];
            }
            if (i > targetIndex) {
                // 回到第一步继续查找
                right = i - 1;
            } else {
                // 回到第一步继续查找
                left = i + 1;
            }
        }
    }

    private static final Random rand = new Random();

    private int partition(int[] nums, int left, int right) {
        // 1. 在子数组 [left, right] 中随机选择一个基准元素 pivot
        int i = left + rand.nextInt(right - left + 1);
        int pivot = nums[i];
        // 把 pivot 与子数组第一个元素交换，避免 pivot 干扰后续划分，从而简化实现逻辑
        swap(nums, i, left);

        // 2. 相向双指针遍历子数组 [left + 1, right]
        // 循环不变量：在循环过程中，子数组的数据分布始终如下图
        // [ pivot | <=pivot | 尚未遍历 | >=pivot ]
        //   ^                 ^     ^         ^
        //   left              i     j         right

        i = left + 1;
        int j = right;
        while (true) {
            while (i <= j && nums[i] < pivot) {
                i++;
            }
            // 此时 nums[i] >= pivot

            while (i <= j && nums[j] > pivot) {
                j--;
            }
            // 此时 nums[j] <= pivot

            if (i >= j) {
                break;
            }

            // 维持循环不变量
            swap(nums, i, j);
            i++;
            j--;
        }

        // 循环结束后
        // [ pivot | <=pivot | >=pivot ]
        //   ^             ^   ^     ^
        //   left          j   i     right

        // 3. 把 pivot 与 nums[j] 交换，完成划分（partition）
        /*
        为什么和 j 交换？
        i 可能有以下情况
            i = right + 1，可能导致下标越界
            可能 nums[i] > pivot，在 pivot 的左边出现了较大的数，不是有效划分
         */
        swap(nums, left, j);

        // 交换后

        /*
        [ <=pivot | pivot | >=pivot ]
                      ^
                      j
         */
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
