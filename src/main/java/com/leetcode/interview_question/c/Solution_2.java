package com.leetcode.interview_question.c;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_2 {


}

/**
 * 面试题 03.02. <a href="https://leetcode.cn/problems/min-stack-lcci/description/">栈的最小值</a>
 */
class MinStack {

    /*
    请设计一个栈，除了常规栈支持的pop与push函数以外，还支持min函数，该函数返回栈元素中的最小值。执行push、pop和min操作的时间复杂度必须为O(1)。
     */

    Deque<int[]> st = new ArrayDeque<>();

    public MinStack() {

    }

    public void push(int x) {
        if (st.isEmpty()) {
            st.push(new int[]{x, x});
        } else {
            st.push(new int[]{x, Math.min(st.peek()[1], x)});
        }
    }

    public void pop() {
        st.pop();
    }

    public int top() {
        return st.peek()[0];
    }

    public int getMin() {
        return st.peek()[1];
    }
}
