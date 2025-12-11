package com.oycm.algorithm.c;


public class Solution_11 {

    /**
     * 696. <a href="https://leetcode.cn/problems/count-binary-substrings/description/">计数二进制子串</a>
     * 返回相同数量 0 和 1 的子串数量，且 子串中 0 和 1 都是连续出现的
     *
     * @param s 只有 0 或 1 的字符串
     * @return
     */
    public static int countBinarySubstrings(String s) {
        /*
        000111
        010
        本质是求 ans += min(上一段相同字符数, 下一段相同字符数)
         */
        int ans = 0;
        int pre = 0, cur = 0, n = s.length();
        int i = 0;
        while (i < n) {
            // 不断维护前一组相同字符数量
            cur++;
            while (i < n - 1 && s.charAt(i) == s.charAt(i + 1)) {
                i++;
                cur++;
            }
            ans += Math.min(pre, cur);
            pre = cur;
            i++;
            cur = 0;
        }

        return ans;
    }

    public int simple(String s) {
        /*
        简化版
         */
        int ans = 0;
        int pre = 0, n = s.length();
        int i = 0;
        while (i < n) {
            char c = s.charAt(i);
            int cur = 0;
            while (i < n && s.charAt(i) == c) {
                cur++;
                i++;
            }
            ans += Math.min(cur, pre);
            pre = cur;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(countBinarySubstrings("00110011"));
        System.out.println(countBinarySubstrings("10101"));
    }

}
