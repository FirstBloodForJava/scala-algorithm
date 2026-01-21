package com.oycm.datastructure.stack.monotonic.minimum_dic;


import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_1 {

    /**
     * 402. <a href="https://leetcode.cn/problems/remove-k-digits/description/">移掉 K 位数字</a>
     *
     * @param num 表示数字的字符串
     * @param k 整数
     * @return 移除 k 个数字后, 剩下这些字符串构成最小的字符串
     */
    public String removeKdigits(String num, int k) {
        /*
        1432219, k = 3
        如果前 k + 1 个数字中, 没有 0, cs[i] 和 cs[i+1] 比较
        题解思路: 123a456 和 123b456 的大小, 取决于 第一个不同数的大小
         */
        int n = num.length();
        if (n == k) {
            return "0";
        }
        Deque<Character> queue = new ArrayDeque<>();
        for (char c : num.toCharArray()) {
            while (!queue.isEmpty() && k > 0 && queue.peekLast() > c) {
                queue.pollLast();
                k--;
            }
            queue.addLast(c);
        }
        for (int i = 0; i < k; i++) {
            queue.pollLast();
        }
        StringBuilder ans = new StringBuilder();
        boolean isZeroBegin = true;
        while (!queue.isEmpty()) {
            Character c = queue.pollFirst();
            if (isZeroBegin && c == '0') {
                continue;
            }
            isZeroBegin = false;
            ans.append(c);
        }


        return ans.length() == 0 ? "0" : ans.toString();
    }

}
