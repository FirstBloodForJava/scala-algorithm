package com.oycm.datastructure.stack.parse;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_1 {

    /**
     * 1006. <a href="https://leetcode.cn/problems/clumsy-factorial/description/">笨阶乘</a> 1408
     * <p>
     * * / + - 顺序执行
     *
     * @param n
     * @return
     */
    public int clumsy(int n) {
        /*
        栈表示 每个表达式的 最终结果
         */
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(n);
        int i = 0;
        while (n > 1) {
            n--;
            if (i % 4 == 0) {
                stack.push(stack.pop() * n);
            } else if (i % 4 == 1) {
                stack.push(stack.pop() / n);
            } else if (i % 4 == 2) {
                stack.push(n);
            } else {
                stack.push(-n);
            }
            i++;
        }

        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }

}
