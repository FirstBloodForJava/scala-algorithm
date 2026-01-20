package com.oycm.datastructure.stack.monotonic.basic;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_1 {

    /**
     * 739. <a href="https://leetcode.cn/problems/daily-temperatures/description/">每日温度</a>
     *
     * @param temperatures 整数数组
     * @return ans[], ans[i] 对于第 i 天，下一个更高温度出现在几天后。
     */
    public int[] dailyTemperatures(int[] temperatures) {
        /*
        如果第二天的温度大于前一天的那么, 那么第一天的下一个高温就是第二天
        如果第二天的温度小于等于前一天，这样就得到了一个单调递减的记录，如果 当天大于 前一天的温度，继续弹栈
         */
        int n = temperatures.length;
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                Integer j = stack.pop();
                ans[j] = i - j;
            }
            stack.push(i);
        }

        return ans;
    }

}
