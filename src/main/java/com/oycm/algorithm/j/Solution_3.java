package com.oycm.algorithm.j;

import java.util.Random;

public class Solution_3 {

    /**
     * 912. <a href="https://leetcode.cn/problems/sort-an-array/description/">排序数组</a>
     *
     * @param nums
     * @return 排序 nums 数组的结果
     */
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private static final Random rand = new Random();

    public void quickSort(int[] nums, int left, int right) {
        boolean ordered = true;
        for (int i = left; i < right; i++) {
            if (nums[i] > nums[i + 1]) {
                ordered = false;
                break;
            }
        }
        if (ordered) {
            return;
        }
        int i = partition(nums, left, right);
        quickSort(nums, left, i - 1);
        quickSort(nums, i + 1, right);
    }

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
