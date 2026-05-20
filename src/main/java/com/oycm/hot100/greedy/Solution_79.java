package com.oycm.hot100.greedy;

import java.util.ArrayList;
import java.util.List;

public class Solution_79 {

    /**
     * 45. <a href="https://leetcode.cn/problems/jump-game-ii/description/">跳跃游戏 II</a>
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        /*
        给定一个长度为 n 的 0 索引整数数组 nums。初始位置在下标 0。
        每个元素 nums[i] 表示从索引 i 向后跳转的最大长度。换句话说，如果你在索引 i 处，你可以跳转到任意 (i + j) 处：
        返回到达 n - 1 的最小跳跃次数。测试用例保证可以到达 n - 1。
         */
        /*
        题解思路：记录当前能走到的最远距离 curMax，记录 [i, curMax] 距离能到的最远距离，
        当到达当前最远距离时，回退到前一个能走的最远距离下标处开始跳跃
         */
        int ans = 0;
        int curMax = 0;
        int nextMax = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            nextMax = Math.max(nextMax, i + nums[i]);
            if (i == curMax) {
                curMax = nextMax;
                ans++;
            }
        }

        return ans;
    }

    public int jump_record(int[] nums) {
        /*
        输出具体的跳跃方案：
        更新 nextMax 的过程中，记录当前的位置下标，当 i+nums[i] > nextMax 才更新下标，
        当出现多个下标能到达同一个最远点时，取前面的下标
         */
        List<Integer> path = new ArrayList<>();
        int ans = 0;
        int curMax = 0;
        int nextMax = 0;
        int nextMaxIdx = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (i + nums[i] > nextMax) {
                nextMaxIdx = i;
                nextMax = i + nums[i];
            }
            if (i == curMax) {
                path.add(nextMaxIdx);
                curMax = nextMax;
                ans++;
            }
        }
        System.out.println(path);
        return ans;
    }

}
