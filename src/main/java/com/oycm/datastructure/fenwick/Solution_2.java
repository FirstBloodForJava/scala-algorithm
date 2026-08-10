package com.oycm.datastructure.fenwick;

import java.util.Arrays;

public class Solution_2 {

    /**
     * 3072. <a href="https://leetcode.cn/problems/distribute-elements-into-two-arrays-ii/description/">将元素分配到两个数组中 II</a> 2053
     *
     * @param nums
     * @return
     */
    public int[] resultArray(int[] nums) {
        /*
        给你一个下标从 1 开始、长度为 n 的整数数组 nums 。
        现定义函数 greaterCount ，使得 greaterCount(arr, val) 返回数组 arr 中 严格大于 val 的元素数量。
        你需要使用 n 次操作，将 nums 的所有元素分配到两个数组 arr1 和 arr2 中。
        在第一次操作中，将 nums[1] 追加到 arr1 。在第二次操作中，将 nums[2] 追加到 arr2 。
        之后，在第 i 次操作中：
            如果 greaterCount(arr1, nums[i]) > greaterCount(arr2, nums[i]) ，将 nums[i] 追加到 arr1 。
            如果 greaterCount(arr1, nums[i]) < greaterCount(arr2, nums[i]) ，将 nums[i] 追加到 arr2 。
            如果 greaterCount(arr1, nums[i]) == greaterCount(arr2, nums[i]) ，将 nums[i] 追加到元素数量较少的数组中。
            如果仍然相等，那么将 nums[i] 追加到 arr1 。
        连接数组 arr1 和 arr2 形成数组 result 。例如，如果 arr1 == [1,2,3] 且 arr2 == [4,5,6] ，那么 result = [1,2,3,4,5,6] 。
        返回整数数组 result 。
         */
        /*
        3 <= n <= 1e5
        1 <= nums[i] <= 1e9
         */
        /*
        问题的关键在于，怎么优化 greaterCount(arr, val)，快速知道 arr1, arr2 有多少元素严格大于 nums[i]。
        如果能知道 nums[i] 在 nums 中排第几，可以利用树状树状快速更新和统计区间和。
        映射 nums[i] 排序后的值。
            1. 将一个索引数组，将这个数组的值 按照 nums 数组排序
            2. 数组中有重复元素
         */
        int n = nums.length;
        Integer[] idxes = new Integer[n];
        for (int i = 0; i < n; i++) {
            idxes[i] = i;
        }
        Arrays.sort(idxes, (a, b) -> nums[a] - nums[b]);
        // mapping[i] 表示 nums[i] 在 nums 中排第几
        int[] mapping = new int[n];
        int m = 1;
        for (int i = 0; i < n; i++) {
            if (i == 0 || nums[idxes[i - 1]] != nums[idxes[i]]) {
                // 第一个，当前数和第一个数不同
                mapping[idxes[i]] = m++;
            } else {
                mapping[idxes[i]] = m - 1;
            }
        }
        int a1 = 1;
        int a2 = 1;
        FenwickTree f1 = new FenwickTree(n);
        FenwickTree f2 = new FenwickTree(n);
        f1.update(mapping[0], 1);
        f2.update(mapping[1], 1);
        mapping[0] = nums[1];
        for (int i = 2; i < n; i++) {
            // 统计严格大于 mapping[i] 的数，相当于统计 [mapping[i] + 1, n-1] 区间和
            int left = mapping[i];
            int g1 = a1 - f1.prefixSum(left);
            int g2 = a2 - f2.prefixSum(left);
            /*
            nums 记录 a1
            mapping 记录 a2
             */
            if (g1 > g2) {
                nums[a1++] = nums[i];
                f1.update(left, 1);
            } else if (g1 < g2) {
                mapping[a2++] = nums[i];
                f2.update(left, 1);
            } else if (a2 < a1) {
                mapping[a2++] = nums[i];
                f2.update(left, 1);
            } else {
                nums[a1++] = nums[i];
                f1.update(left, 1);
            }
        }
        System.arraycopy(mapping, 0, nums, a1, a2);

        return nums;
    }

    public static void main(String[] args) {
        Solution_2 solution2 = new Solution_2();
        solution2.resultArray(new int[]{10, 79, 12});
    }

}

class FenwickTree {
    private int[] tree;

    public FenwickTree(int n) {
        tree = new int[n + 1];
    }

    public void update(int index, int val) {
        for (int i = index; i < tree.length; i += i & -i) {
            tree[i] += val;
        }
    }

    public int prefixSum(int i) {
        int s = 0;
        for (; i > 0; i &= i - 1) {
            s += tree[i];
        }
        return s;
    }

    public int sumRange(int left, int right) {
        return prefixSum(right + 1) - prefixSum(left);
    }

}
