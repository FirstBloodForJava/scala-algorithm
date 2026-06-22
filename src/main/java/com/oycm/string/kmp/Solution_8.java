package com.oycm.string.kmp;

public class Solution_8 {

    /**
     * 2800. <a href="https://leetcode.cn/problems/shortest-string-that-contains-three-strings/description/">包含三个字符串的最短字符串</a> 1856
     *
     * @param a
     * @param b
     * @param c
     * @return
     */
    public String minimumString(String a, String b, String c) {
        /*
        给你三个字符串 a，b 和 c，你的任务是找到长度 最短 的字符串，且这三个字符串都是它的 子字符串。
        如果有多个这样的字符串，请你返回 字典序最小 的一个。
        两个长度相同的字符串 a 和 b ，如果在第一个不相同的字符处，a 的字母在字母表中比 b 的字母 靠前 ，那么字符串 a 比字符串 b 字典序小 。
         */
        /*
        注意 abc 字典序 > bc
         */
        /*
        设 a, b, c 是从小到大 排序的字符串。分类讨论：
            如果 a 是 b 的子串，则只需要考虑 b c 如何合并；abc abcd
            如果 a 不是 b 的子串，要构建一个字符串包含 a 和 b 子串，且字典序要最小，就不能以 b 字符串作为开始；
            因为字符串 a < b，且 b 不是 a 的子串，则一定存在 a[i] < b[i]，那么一定有 [0, i) 之前的子串 a[0:i-1]=b[0:i-1]，
            如果以 b 作为开始字符串，则需要找到 b 中后缀字符串与 a 字符串前缀最长匹配长度，不够的后面补上剩余 a 字符串，就算这里构造的字符串够短；
            但是由于 a[i] < b[i]，就算 a 的后缀不包含任何 b 字符串前缀，构造一个 a+b 的字符串，其结果也是 < 以 b 开始的字符串。
            如果 a 和 b 有公共前缀(abca abcd)，则看 a 的 kmp 数组 a[n-1] 的长度是多，b - a[n-1] 就是补在 a 后面的 b 子串；
            如果 a 和 b 没有公共前缀(abc bca)，？构建一个 b + a[1:] 找出 next[n-1] 最大长度，这个长度不能大于 a[1:] abb bbbb
         */
        String[] cs = {a, b, c};
        int[][] ps = {{0, 1, 2}, {0, 2, 1}, {1, 0, 2}, {1, 2, 0}, {2, 0, 1}, {2, 1, 0}};
        String ans = null;
        for (int[] p : ps) {
            String s = merge(merge(cs[p[0]], cs[p[1]]), cs[p[2]]);
            if (ans == null || s.length() < ans.length() || s.length() == ans.length() && s.compareTo(ans) < 0) {
                ans = s;
            }

        }

        return ans;
    }

    public String merge(String a, String b) {
        if (b.contains(a)) return b;
        if (a.contains(b)) return a;
        String s = a + "#" + b;
        int n = s.length();
        int[] next = new int[s.length()];
        int cnt = 0;
        for (int i = 1; i < n; i++) {
            while (cnt > 0 && s.charAt(i) != s.charAt(cnt)) {
                cnt = next[cnt - 1];
            }
            if (s.charAt(i) == s.charAt(cnt)) {
                cnt++;
            }
            next[i] = cnt;
        }
        return b + a.substring(next[n - 1]);
    }

}
