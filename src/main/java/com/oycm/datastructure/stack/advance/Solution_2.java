package com.oycm.datastructure.stack.advance;

import java.util.Stack;

public class Solution_2 {

    /**
     * 155. <a href="https://leetcode.cn/problems/min-stack/description/">最小栈</a>
     */
    class MinStack {

        /*
        栈中是二元组, 维护 当前值和前面值 比较的最小值
         */
        Stack<int[]> stack = new Stack<>();

        public MinStack() {
            stack.push(new int[]{0, Integer.MAX_VALUE});
        }

        public void push(int val) {
            stack.push(new int[]{val, Math.min(stack.peek()[1], val)});
        }

        public void pop() {
            stack.pop();
        }

        public int top() {
            return stack.peek()[0];
        }

        public int getMin() {
            return stack.peek()[1];
        }
    }

}
