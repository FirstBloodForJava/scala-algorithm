package com.oycm.hot100.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_70 {

    /**
     * 155. <a href="https://leetcode.cn/problems/min-stack/description/">最小栈</a>
     */
    class MinStack {

        /*
        维护栈中元素的前缀最小值
        栈中存一个二元组，一个表示当前值，一个表示栈底到栈顶的最小值
         */
        Deque<int[]> st = new ArrayDeque<>();

        public MinStack() {
            st.push(new int[]{0, Integer.MAX_VALUE});
        }

        /**
         * 将 val 入栈
         *
         * @param val
         */
        public void push(int val) {
            st.push(new int[]{val, Math.min(st.peek()[1], val)});
        }

        /**
         * 删除栈顶元素
         */
        public void pop() {
            st.pop();
        }

        /**
         * 获取栈顶元素
         *
         * @return
         */
        public int top() {
            return st.peek()[0];
        }

        /**
         * 获取栈中最小元素
         *
         * @return
         */
        public int getMin() {
            return st.peek()[1];
        }
    }

}
