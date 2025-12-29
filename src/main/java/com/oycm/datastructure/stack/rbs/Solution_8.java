package com.oycm.datastructure.stack.rbs;


import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_8 {

    /**
     * 1963. <a href="https://leetcode.cn/problems/minimum-number-of-swaps-to-make-the-string-balanced/description/">使字符串平衡的最小交换次数</a> 1689
     *
     * @param s n/2 '[', n/2 个 ']'
     * @return 交换 s 中 两个字符位置最小次数使得 s 是平衡字符串
     */
    public static int minSwaps(String s) {
        /*
        首先要证明怎么样的交换逻辑次数才能最少, 出现不平衡的情况是 右括号出现在了左边, 对应的左括号出现在右边
        ][][   和不成对的 左括号交换才能次数最少 [[]] ,把最左边的括号 右括号和最右边的括号进行消消乐, 所有的都能消除 按这种交换模式，次数能最少
        交换一次左边能消除一个括号对，右边也能消除一个括号对, 如果括号对是偶数 剩余数量是 4 的倍数, 执行一次交换可以消除 4 个括号, 答案就是 size/4
        ]]][[[ [[[]]] 3次 [[][]]
        ]]][][[[ => [[][][]]
         */
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == '[' && c == ']') {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        if (stack.size() % 4 == 0) {
            return stack.size() / 4;
        } else {
            return stack.size() / 4 + 1;
        }
    }

    public static int answer(String s) {
        /*
        平衡字符串的特性: 平衡字符串的任意一个前缀, 其左括号的数量和 总是大于等于 右括号的数量和。因为在前缀中，左括号总是先出现的，出现左括号不一定会有对应的右括号。
        根据这一性质，从左到右遍历字符串 s, 统计未匹配的左括号数量 c, 出现 '[' c++; 出现 ']' c--; 如果任何时刻 c 的数量都是大于等于 0 的, 说明 s 就是平衡字符串。
        当 c = 0 时，出现了 ']' c = -1, 说明前缀右括号数量比左括号多, 无论后面的字符是什么情况, s 都不可能是 平衡字符串。
        左边出现多了 ']', 右边肯定会对应多 '[', 怎么样才能最优交换?
        出现 '[' +1, 出现 '[' -1, 为了是 c 的数量不为负数, ']' 应该和最靠后的 '[' 交换才是优的
        ][][ ] 和 i = 1 交换, i = 2, 还需要再交换一次
         */
        // 不需要执行真正的交换, 当 c == 0, 右边都是 [[[[ 没有切换成 ]] c 的值之后越来越大, 后续并不会再 更新 ans
        // 去掉 ans
        int c = 0;
        for (char b : s.toCharArray()) {
            if (b == '[' || c == 0) {
                c++;
            } else {
                c--;
            }
        }

        return c / 2;
    }

    public static void main(String[] args) {

    }

}
