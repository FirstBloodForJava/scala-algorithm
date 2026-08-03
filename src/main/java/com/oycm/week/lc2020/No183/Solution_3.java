package com.oycm.week.lc2020.No183;

import java.util.PriorityQueue;

public class Solution_3 {

    /**
     * 1405. <a href="https://leetcode.cn/problems/longest-happy-string/description/">最长快乐字符串</a> 1821
     *
     * @param a
     * @param b
     * @param c
     * @return
     */
    public String longestDiverseString(int a, int b, int c) {
        /*
        如果字符串中不含有任何 'aaa'，'bbb' 或 'ccc' 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。
        给你三个整数 a，b ，c，请你返回 任意一个 满足下列全部条件的字符串 s：
            s 是一个尽可能长的快乐字符串。
            s 中 最多 有 a 个字母 'a'、b 个字母 'b'、c 个字母 'c' 。
            s 中只含有 'a'、'b' 、'c' 三种字母。
        如果不存在这样的字符串 s ，请返回一个空字符串 ""。
         */
        /*
        a, b, c 分别是各种字符数量的上限。
        最长的长度 a + b + c
        最短的长度 2
        怎么构造这个答案？长度一定是最长的
        堆，每次填字符数量最多，直到连续字符数量大于等于 2，换下一个数量更多的字符，使得 a, b, c 尽可能相等，实现最长
         */
        PriorityQueue<int[]> max = new PriorityQueue<>((x, y) -> y[0] - x[0]);
        StringBuilder sb = new StringBuilder();
        if (a > 0) {
            max.add(new int[]{a, 0});
        }
        if (b > 0) {
            max.add(new int[]{b, 1});
        }
        if (c > 0) {
            max.add(new int[]{c, 2});
        }
        while (!max.isEmpty()) {
            int[] cur = max.poll();
            int n = sb.length();
            if (n >= 2 && sb.charAt(n - 2) - 'a' == cur[1] && sb.charAt(n - 1) - 'a' == cur[1]) {
                if (max.isEmpty()) {
                    break;
                }
                int[] next = max.poll();
                sb.append((char) ('a' + next[1]));
                if (--next[0] > 0) max.add(next);
                max.add(cur);
            } else {
                sb.append((char) ('a' + cur[1]));
                if (--cur[0] > 0) max.add(cur);
            }
        }
        return sb.toString();
    }

}
