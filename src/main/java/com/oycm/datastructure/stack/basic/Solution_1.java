package com.oycm.datastructure.stack.basic;


import java.util.ArrayList;
import java.util.List;

public class Solution_1 {

    /**
     * 1441. <a href="https://leetcode.cn/problems/build-an-array-with-stack-operations/description/">用栈操作构建数组</a> 1180
     * <p>
     * 给一个空栈和两种操作：
     * <p>
     * - Push：将一个整数加到栈顶。
     * <p>
     * - Pop：从栈顶删除一个整数。
     * <p>
     * 使用两个栈操作，使得栈中的数组（从底部到顶部）等于 target。操作过程中，遵循一下规则：
     * <p>
     * - 如果整数流不为空，从流中选取下一个整数并将其推送到栈顶。
     * <p>
     * - 如果栈不为空，弹出栈顶的整数。
     * <p>
     * - 如果，在任何时刻，栈中的元素等于 target，则不要从流中读取新的整数，也不要对栈进行更多操作。
     *
     * @param target 严格递增数组数组
     * @param n      [1, n] 的 整数流
     * @return
     */
    public List<String> buildArray(int[] target, int n) {
        /*
        栈最开始是空的，所以最开始的操作一定是 Push
        创建一个和 target 一样长的 stack 数组，用 cnt 维持栈数量
        先入栈，如果入栈元素 == target[cnt], 则 cnt++, 整数流取下一个;
        如果 入栈元素 != target[cnt], 则弹栈，cnt 不变，整数流取下一个;
         */
        List<String> ans = new ArrayList<>();

        int cnt = 0;
        int k = 1;
        while (cnt < target.length) {
            ans.add("Push");
            if (k == target[cnt]) {
                cnt++;
            } else {
                ans.add("Pop");
            }

            k++;
        }

        return ans;
    }


}
