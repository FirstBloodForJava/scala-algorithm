package com.oycm.hot100.trick;

public class Solution_97 {

    /**
     * 75. <a href="https://leetcode.cn/problems/sort-colors/description/">颜色分类</a>
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        /*
        给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，
        原地 对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
        整数 0、 1 和 2 分别表示红色、白色和蓝色。
         */
        /*
        遍历 nums 使用一个数组记录 红色、白色和蓝色的数量，
        遍历 nums 排序，颜色数量减少 1，降为 0 时，使用下一个颜色填充
         */
        int[] cnt = new int[3];
        for (int x : nums) {
            cnt[x]++;
        }
        int cur = 0;
        for (int i = 0; i < nums.length; i++) {
            while (cnt[cur] == 0) cur++;
            nums[i] = cur;
            cnt[cur]--;
        }
        /*
        插入排序：假设现在有一个有序数组 a = [0,0,1,1,2,2]，需要在 a 中插入一个 0，同时要保证数组有序，
        最暴力的做法，把 0 插入 a 的最左边，原来的全体元素向右移动一位。
        对比 插入前后：
            [0,0,1,1,2,2]
            [0,0,0,1,1,2,2]
        可以发现只有三个位置发生了变化 a[2] 变成 0，a[4] 变成 1，新增一个元素 2。
        可以发现按以下位置更新：
            记录数组中 0 的个数，记为 p0，数组中 1 的个数记为 p1
            p0 位置要改为 0；
            p0 + p1 位置要改为 1；
            nums[i] 改为 2；
        如果 a = [1] 最后一个位置会错误的修改为 2
        可以根据插入前一个元素的值来修改 nums[i]。
        还有一种简单的方式，倒着更新：p1 表示 0 和 1 的数量和
            先记录 x = nums[i]，把 nums[i] 更新成 2
            如果 x <= 1，更新 nums[p1++] = 1
            如果 x = 0，更新 nums[p0++] = 1
         */

    }

}
