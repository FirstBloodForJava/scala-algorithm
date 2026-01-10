package com.oycm.datastructure.linked.fast_slow;


public class Solution_10 {

    /**
     * 287. <a href="https://leetcode.cn/problems/find-the-duplicate-number/description/">寻找重复数</a>
     *
     * @param nums 长度为 n+1, 数字范围在 [1,n], 可知至少存在一个重复的数
     * @return 假设 nums 只有一个重复的数, 返回这个数
     */
    public static int findDuplicate(int[] nums) {
        /*
        题解思路: 构建一个 i -> nums[i] 的有向图, 重复元素就是 入环口
        题解思路1: 二分查找, cnt[i] 表示 nums 中小于等于 数字 i 个个数, 未出现重复数时 cnt[i] <= i, 出现时,及后面 cnt[i] > i;
        题解思路2: 位运算, 算出每个 bit 位 是 1 还是 0, x 表示所有 nums[i] 第 i 个比特位是 1 的数量; y 表示 [1,n] 所有数字 第 i 个比特位是 1 的数量, 当 x > y 时, 重复数字第 i 个比特位就是 1
         */
        int slow = 0;
        int fast = 0;
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            // 相遇
            if (slow == fast) {
                break;
            }
        }
        int head = 0;
        while (head != slow) {
            head = nums[head];
            slow = nums[slow];
        }
        return slow;
    }

    public static void main(String[] args) {
        System.out.println(findDuplicate(new int[]{3, 1, 3, 4, 2}));
    }

}
