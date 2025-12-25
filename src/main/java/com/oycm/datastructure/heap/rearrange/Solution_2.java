package com.oycm.datastructure.heap.rearrange;


import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution_2 {

    /**
     * 767. <a href="https://leetcode.cn/problems/reorganize-string/description/">重构字符串</a> 1681
     *
     * @param s
     * @return 重新排列字符串 s 的字母, 使得两相邻字符不同, 存在则返回任意排列, 否则则返回 空字符串
     */
    public String reorganizeString(String s) {
        /*
        多次出现的字符交替排列，才能让越来越多的字符 相邻不同出现 aabc, 如果 bca 后面则不符合要求, 选 a 后, 随便选 b/c , 后面的字符相邻都不会相同
         */

        Map<Character, int[]> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            int[] cnt = map.get(c);
            if (cnt != null) {
                cnt[1]++;
            } else {
                cnt = new int[]{c, 1};
                map.put(c, cnt);
            }
        }
        // 构建堆
        PriorityQueue<int[]> max = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        max.addAll(map.values());

        StringBuilder ans = new StringBuilder();
        while (!max.isEmpty()) {
            int[] poll = max.poll();
            ans.append((char) poll[0]);
            poll[1]--;
            if (poll[1] > 0 && max.isEmpty()) {
                return "";
            }

            if (poll[1] > 0) {
                // 取下一个元素
                int[] next = max.poll();
                ans.append((char) next[0]);
                next[1]--;
                max.add(poll);
                if (next[1] > 0) {
                    max.add(next);
                }
            }

        }

        return ans.toString();
    }

    public String answer(String s) {
        /*
        s 中出现次数最多的 字母 记为 m 次, n = s.length();
            当 m > n - m 时, n - m = n/2 才能填满


         */
        int[] cnt = new int[26];
        int m = 0;
        char mc = 0;
        for (char c : s.toCharArray()) {
            if (++cnt[c - 'a'] > m) {
                m = cnt[c - 'a'];
                mc = c;
            }
        }
        int n = s.length();
        if (m > n - m + 1) {
            return "";
        }
        char[] ans = new char[n];
        // 先填 最多次数字符
        int i = 0;
        while (m > 0) {
            m--;
            ans[i] = mc;
            i += 2;
        }

        for (int j = 0; j < cnt.length; j++) {
            int cs = cnt[j];
            while (cs-- > 0) {
                if (i >= n) {
                    i = 1;
                }
                ans[i] = (char) ('a' + j);
                i += 2;
            }
        }

        return new String(ans);
    }


}
