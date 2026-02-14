package com.oycm.algorithm.f.prefix_sum_two;

import java.util.HashMap;
import java.util.Map;

public class Solution_20 {

    /**
     * 3714. <a href="https://leetcode.cn/problems/longest-balanced-substring-ii/description/">最长的平衡子串 II</a> 2202
     * <p>
     * 平衡子串: 子串 中所有 不同 字符出现的次数都 相同
     *
     * @param s 只包含 a, b, c 三种字符 的字符串
     * @return
     */
    public int longestBalanced(String s) {
        /*
        题解思路: 分成三类问题, 依次解答
            子串只包含一种字符
            子串只包含两种字符
            子串只包含三种字符
         */
        char[] cs = s.toCharArray();
        int ans = 0, n = cs.length;

        // 一种字符
        for (int i = 0; i < n;) {
            int start = i;
            for (i++; i < n && cs[i] == cs[i-1]; i++);
            ans = Math.max(ans, i - start);
        }

        // 两种字符
        ans = Math.max(ans, f(cs, 'a', 'b'));
        ans = Math.max(ans, f(cs, 'a', 'c'));
        ans = Math.max(ans, f(cs, 'b', 'c'));

        // 三种字符
        /*
        平衡字符串[l, r) Sa/Sb/Sc 分别表示 a b c 字符在字符串中出现的次数
        平衡字符串则满足
            Sa[r] - Sa[l] = Sb[r] - Sb[l]
            Sb[r] - Sb[l] = Sc[r] - Sc[l]
        移项 =>
            Sa[r] - Sb[r] = Sa[l] - Sb[l]
            Sb[r] - Sc[r] = Sb[l] - Sc[l]
        定义数组 a[i] = (Sa[r] - Sb[r], Sb[r] - Sc[r])
        计算数组 a 一对相等元素的最远距离
         */
        Map<Long, Integer> pos = new HashMap<>();
        pos.put((long) n << 20 | n, -1); // 前缀和数组的首项是 0，位置相当于在 -1
        // cnt[] 0, 1, 2 分别表示 a, b, c 字符前缀和数量
        int[] cnt = new int[3];
        for (int i = 0; i < n; i++) {
            cnt[cs[i] - 'a']++;
            // x = Sa[r] - Sb[r], y = Sb[r] - Sc[r] 为了避免负数
            // 把 x, y 压缩成 long 时, 为了避免负数各自添加 n , (x + n) << 20 | y + n
            long p = (long) (cnt[0] - cnt[1] + n) << 20 | (cnt[1] - cnt[2] + n);
            if (pos.containsKey(p)) {
                ans = Math.max(ans, i - pos.get(p));
            } else {
                pos.put(p, i);
            }
        }
        return ans;

    }

    private int f(char[] s, char x, char y) {
        int n = s.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            Map<Integer, Integer> pos = new HashMap<>();
            // 前缀和 sum[0]
            pos.put(0, i - 1);
            // x 表示 1, y 表示 -1, d 表示 前缀和
            int d = 0;
            for (; i < n && (s[i] == x || s[i] == y); i++) {
                d += s[i] == x ? 1 : -1;
                if (pos.containsKey(d)) {
                    ans = Math.max(ans, i - pos.get(d));
                } else {
                    pos.put(d, i);
                }
            }
        }
        return ans;
    }

}
