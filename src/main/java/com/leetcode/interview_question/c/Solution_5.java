package com.leetcode.interview_question.c;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_5 {

}

/**
 * 面试题 03.05. <a href="https://leetcode.cn/problems/sort-of-stacks-lcci/">栈排序</a>
 *
 *
 */
class SortedStack {

    /*
    栈排序。 编写程序，对栈进行排序使最小元素位于栈顶。
    最多只能使用一个其他的临时栈存放数据，但不得将元素复制到别的数据结构（如数组）中。
    该栈支持如下操作：push、pop、peek 和 isEmpty。当栈为空时，peek 返回 -1。
     */
    public SortedStack() {

    }

    Deque<Integer> stack = new ArrayDeque<>();

    public void push(int val) {
        /*
        单调栈：栈底到栈顶递减
         */
        if (stack.isEmpty() || stack.peek() >= val) {
            stack.push(val);
        } else {
            // 栈顶元素用递归栈记录，先入后出
            int pop = stack.pop();
            push(val);
            stack.push(pop);
        }

    }

    public void pop() {
        if (!stack.isEmpty()) stack.pop();
    }

    public int peek() {
        if (stack.isEmpty()) return -1;
        return stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
