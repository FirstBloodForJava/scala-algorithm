package com.oycm.hot100.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_70 {

    /**
     * 155. <a href="https://leetcode.cn/problems/min-stack/description/">最小栈</a>
     */
    class MinStack {

        /*
        使用一个元素，维护栈中元素和最小值的插值，再通过差值还原栈中的值。
        如果当前 val 比前面的最小值更小，那么栈顶元素将是负数。否则 val + mn 才是原来的元素
            push 记录 val - mn；
            pop 需要还原最小值（栈顶是负数时才需要还原）st.pop = val - preMin = min - preMin, preMin = min - st.pop
            peek 栈顶是正数时需要加上最小值还原，否则就是最小值
         */
        Deque<Long> st = new ArrayDeque<>();
        long mn = Long.MAX_VALUE / 2;

        public MinStack() {
        }

        /**
         * 将 val 入栈
         *
         * @param val
         */
        public void push(int val) {
            st.push(val - mn);
            mn = Math.min(mn, val);
        }

        /**
         * 删除栈顶元素
         */
        public void pop() {
            mn -= Math.min(st.pop(), 0);
        }

        /**
         * 获取栈顶元素
         *
         * @return
         */
        public int top() {
            return (int) (mn + Math.max(st.peek(), 0));
        }

        /**
         * 获取栈中最小元素
         *
         * @return
         */
        public int getMin() {
            return (int) mn;
        }
    }

}
