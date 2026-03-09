package com.oycm.week.No492;

public class Solution_3 {

    /**
     * 3863. <a href="https://leetcode.cn/problems/minimum-operations-to-sort-a-string/description/">将一个字符串排序的最小操作次数</a>
     *
     * @param s
     * @return
     */
    public int minOperations(String s) {
        /*
        选择 s 的任意 子字符串（但 不能 是整个字符串）, 不是原 s 的字符串子串
        排序的子串不能包含第一个字符和最后一个字符
        如果第一个字符或最后一个字符是正确的, 则至多只需要排序 1 次; 否则至少需要 2 次
        分类讨论:
            情况一: 已经有序, 无需操作
            情况二: 长度等于 2, 无法实现
            情况三: 第一个字母或最后一个字母在正确位置上, 排序 [1, n-1] 或 [0, n-2], 操作 1 次
            情况四: 最大值/最小值在中间
                [1, n-2] 有最小值, 先排序 [0, n-2], 变成情况三, 操作 2 次
                [1, n-2] 有最大值, 先排序 [1, n-1], 变成情况三, 操作 2 次
            情况五: s[0] 是最大值, s[n-1] 是最小值, 且 [1, n-2] 不含最大值和最小值
                先排序 [0, n-2], s[n-2] 是最大值
                再排序 [1, n-1], s[n-1] 是最大值 变成情况三
         */
        int n = s.length();
        char[] cs = s.toCharArray();
        boolean isSorted = true;
        for (int i = 1; i < n; i++) {
            if (cs[i - 1] > cs[i]) {
                isSorted = false;
                break;
            }
        }
        if (isSorted) {
            return 0;
        }
        if (n == 2) {
            return -1;
        }
        char min = cs[0];
        char max = cs[0];
        for (char c : cs) {
            min = (char) Math.min(min, c);
            max = (char) Math.max(max, c);
        }
        if (cs[0] == min || cs[n - 1] == max) {
            return 1;
        }
        for (int i = 1; i < n - 1; i++) {
            if (cs[i] == min || cs[i] == max) {
                return 2;
            }
        }

        return 3;
    }

}
