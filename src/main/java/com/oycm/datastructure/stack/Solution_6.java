package com.oycm.datastructure.stack;


import java.util.Stack;

public class Solution_6 {

    /**
     * 946. <a href="https://leetcode.cn/problems/validate-stack-sequences/description/">验证栈序列</a> 1462
     * <p>
     * 只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时，返回 true
     * <p>
     * 否则，返回 false
     *
     * @param pushed 数组中的值都不重复
     * @param popped 数组中的值都不重复
     * @return
     */
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        /*
        用一个数组 stack 记录栈当前情况，cnt 表示栈中元素数量，cnt-1 表示栈顶元素
        先压栈，判断是否需要弹栈 stack[cnt] == popped[i]
         */
        int n = pushed.length;
        int[] stack = new int[n];
        int cnt = 0;
        int i = 0;
        int j = 0;
        while (i < n || j < n) {
            if (i < n && pushed[i] == popped[j]) {
                i++;
                j++;
            } else {
                // 入栈前，要考虑栈不为空的情况
                if (i < n) {
                    if (cnt > 0 && stack[cnt - 1] == popped[j]){
                        cnt--;
                        j++;
                    } else {
                        stack[cnt++] = pushed[i++];
                    }
                } else {
                    if (stack[cnt - 1] == popped[j]) {
                        j++;
                        cnt--;
                    } else {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public static boolean validateStackSequences_1(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0, j = 0; i < pushed.length; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(validateStackSequences(new int[]{2, 1, 0}, new int[]{1, 2, 0}));
        System.out.println(validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1}));
        System.out.println(validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 3, 5, 1, 2}));
    }
}
