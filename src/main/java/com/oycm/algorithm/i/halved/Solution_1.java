package com.oycm.algorithm.i.halved;

import java.util.HashSet;
import java.util.Set;

public class Solution_1 {

    /**
     * 805. <a href="https://leetcode.cn/problems/split-array-with-same-average/description/">数组的均值分割</a> 1983
     *
     * @param nums nums.length [1, 30]
     * @return
     */
    public boolean splitArraySameAverage(int[] nums) {
        /*
        将 nums 数组中的每个元素移动到 A 数组 或者 B 数组中，使得 A 数组和 B 数组不为空，并且 average(A) == average(B) 。
        可以完成返回 true，否则返回 false
         */
        /*
        如果存在，这个平均值会有什么特点？
            nums 长度为 n，x = average(A) == average(B)
            A 的 长度为 k，B 的长度 n - k
            k * x + (n - x) * k = sum => n * x = sum, 能均分，平均固定为 sum / n
        对于数组 nums[i] 选进入 A，不选进入 B，进入 A 的集合存在平均值满足条件，则 B 的一定符合要求吧
        这样运行超时，世界复杂度 2^n
         */
        /*
        题解思路：可以将 nums 所有元素都减去平均值，看是否能选出一些数，使得数组和为 0。
        平均值可能为小数，会出现精度丢失，可以将 nums[i] 都乘以 n，这样平均值一定是整数。
        枚举的过程，可以把数组 nums 拆成两部分，看是否存在左边的和为 x，右边和为 -x 的存在，这样就减少了枚举的复杂度
         */
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int n = nums.length, m = n / 2;
        if (n == 1) return false;
        // nums[i] 减去平均值
        for (int i = 0; i < n; i++) {
            nums[i] = n * nums[i] - sum;
        }
        Set<Integer> left = new HashSet<>();
        // 二进制枚举所有组合
        for (int i = 1; i < (1 << m); i++) {
            int total = 0;
            for (int j = 0; j < m; j++) {
                if ((i & (1 << j)) != 0) {
                    total += nums[j];
                }
            }
            if (total == 0) return true;
            left.add(total);
        }
        // 用来标记右边的 B 数组不能全选
        int rSum = 0;
        for (int i = m; i < n; i++) {
            rSum += nums[i];
        }
        for (int i = 1; i < (1 << (n - m)); i++) {
            int total = 0;
            for (int j = m; j < n; j++) {
                if ((i & (1 << (j - m))) != 0) {
                    total += nums[j];
                }
            }
            if (total == 0 || (rSum != total && left.contains(-total))) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Solution_1 solution = new Solution_1();

        System.out.println(solution.splitArraySameAverage(new int[]{3, 1}));
    }


}
