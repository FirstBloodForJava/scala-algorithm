package com.oycm.hot100.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_72 {

    /**
     * 739. <a href="https://leetcode.cn/problems/daily-temperatures/description/">每日温度</a>
     *
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        /*
        给定一个整数数组 temperatures ，表示每天的温度，
        返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。
        如果气温在这之后都不会升高，请在该位置用 0 来代替。
         */
        /*
        暴力计算思路：对于 temperatures[i]，在 [i+1, n-1] 找到第一个大于 temperatures[i] 的下标 j，j = n 时，表示不存在，否则 j - i 就是答案
        单调栈，从右到左，栈底 -> 栈顶 是单调递减的，栈顶元素温度 小于等于 当前温度，则弹栈
         */
        int n = temperatures.length;
        Deque<Integer> st = new ArrayDeque<>();
        int[] answer = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && temperatures[i] >= temperatures[st.peek()]) {
                st.pop();
            }
            if (!st.isEmpty()) {
                answer[i] = st.peek() - i;
            }
            st.push(i);

        }

        return answer;
    }

}
