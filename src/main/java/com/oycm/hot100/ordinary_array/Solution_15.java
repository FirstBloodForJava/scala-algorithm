package com.oycm.hot100.ordinary_array;

public class Solution_15 {

    /**
     * 189. <a href="https://leetcode.cn/problems/rotate-array/description/">轮转数组</a>
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        /*
        给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
         */
        /*
        循环数组向前移动 k 次
        计算 i 向前移动后的位置
            ans[(i + k) % n] = nums[i]
        计算最终 i 由哪个位置移动过来
            ans[i] = ans[(i + n - k) % n]
        怎么原地修改？
        题解思路：反转数组
        数组分为长 n-k, k 两部分，向右轮转 k 次后，变成 k, n-k 两部分，两个长度部分顺序都保持一致
        可以将数组反转一次
            [0, k-1] 反转一次，k 部分移到前面，两次反转后，顺序恢复
            [k, n-1] 反转一次，n-k 部分移到后面，两次反转后，顺序恢复
         */
        k %= nums.length;
        if (k == 0) return;
        int n = nums.length;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int l, int r) {
        while (l < r) {
            int temp = nums[r];
            nums[r--] = nums[l];
            nums[l++] = temp;
        }
    }


}
