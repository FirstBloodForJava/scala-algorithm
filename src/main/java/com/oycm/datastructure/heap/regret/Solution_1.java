package com.oycm.datastructure.heap.regret;


import java.util.PriorityQueue;

public class Solution_1 {


    /**
     * LCP 30. <a href="https://leetcode.cn/problems/p0NxJO/description/">魔塔游戏</a>
     * <p>
     * 血量要一直为正数, 求将 负数 nums[i] 调整至访问顺序末尾的最小调整次数
     *
     * @param nums nums[i] 表示 正数加血/负数扣血
     * @return
     */
    public int magicTower(int[] nums) {
        /*
        先判断数组和是否 大于 0, 如果 sum >= 0, 不考虑最小值
        [1,1,-2,-1,1,2]
        上面 如果不把 -2 先 移到后面, 就需要移动两次负数才行
        初始化 sum = 0, 如果 sum < 0, 把前面为负数的最小值移动到最后,
         */
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum < 0) {
            return -1;
        }
        int ans = 0;
        PriorityQueue<Integer> min = new PriorityQueue<>();
        sum = 0;
        for (int num : nums) {
            sum += num;
            if (num < 0) {
                min.add(num);
            }
            // 小于 0 堆中一定有负值
            if (sum < 0) {
                sum -= min.poll();
                ans++;
            }
        }


        return ans;
    }
}
