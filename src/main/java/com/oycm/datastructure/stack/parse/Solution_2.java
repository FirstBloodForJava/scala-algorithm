package com.oycm.datastructure.stack.parse;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_2 {

    /**
     * 150. <a href="https://leetcode.cn/problems/evaluate-reverse-polish-notation/description/">逆波兰表达式求值</a>
     * <p>
     * 逆波兰式 将运算符写在操作数之后
     * <p>
     * ["2","1","+","3","*"] ((2 + 1) * 3)
     * <p>
     * ["4","13","5","/","+"] (4 + (13 / 5))
     *
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        /*
        逆波兰表达式: 遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中
         */
        Deque<Integer> stack = new ArrayDeque<>();

        for (String token : tokens) {
            if ("+".equals(token)) {
                stack.push(stack.pop() + stack.pop());
            } else if (("-".equals(token))) {
                int suf = stack.pop();
                stack.push(stack.pop() - suf);
            } else if ("*".equals(token)) {
                stack.push(stack.pop() * stack.pop());
            } else if (("/".equals(token))) {
                int suf = stack.pop();
                stack.push(stack.pop() / suf);
            } else {
                stack.push(Integer.valueOf(token));
            }
        }

        return stack.peek();
    }

}
