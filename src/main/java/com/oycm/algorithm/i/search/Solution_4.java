package com.oycm.algorithm.i.search;

import java.util.ArrayList;
import java.util.List;

public class Solution_4 {

    /**
     * 1415. <a href="https://leetcode.cn/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/description/">长度为 n 的开心字符串中字典序第 k 小的字符串</a> 1576
     *
     * @param n
     * @param k
     * @return 返回排序后的第 k 个开心字符串，如果长度为 n 的开心字符串少于 k 个，那么请你返回 空字符串
     */
    public String getHappyString(int n, int k) {
        /*
        开心字符串 定义为：
            仅包含小写字母 ['a', 'b', 'c']
            对所有在 1 到 s.length - 1 之间的 i ，满足 s[i] != s[i + 1] （字符串的下标从 1 开始）。
        将长度为 n 的所有开心字符串按字典序排序。
         */
        /*
        dfs 找出所有的开心字符串
         */
        List<String> ans = new ArrayList<>();
        dfs(0, new char[n], ans, n);
        return k > ans.size() ? "" : ans.get(k - 1);
    }

    public void dfs(int i, char[] path, List<String> ans, int n) {
        if (i == n) {
            ans.add(new String(path));
            return;
        }
        for (char j = 'a'; j < 'd'; j++) {
            if (i == 0 || j != path[i - 1]) {
                path[i] = j;
                dfs(i + 1, path, ans, n);
            }
        }
    }

}

class Solution_1415 {

    public String getHappyString(int n, int k) {
        /*
        题解思路：第一个字母可以有 3 种选法，后面的只能有 2 种选法，总的选法 3 * 2^(n-1)
        k-- 之后 k / (2^(n-1))
        n = 4, k = 13, k-- = 1100, k mod = 100
        000 aba
        001 abc
        010 aca
        011 acb
        100 cab 开始字符是 b
        101 cac
        110 cba
        111 cbc
        根据 k mod (2^(n-1)) 二进制高位到低位填字母，1 填可选的较大字母，0 优先填 a
        填入逻辑：
            比特位是 0，填入 a；否则填入 b
            修正逻辑：看填入的字符是否大于等于左侧（前一个）字符，如果大于等于，填入的字符增加 1；
         */
        if (k > 3 << (n - 1)) {
            return "";
        }
        char[] ans = new char[n];
        k--;
        ans[0] = (char) ('a' + (k >> (n - 1)));
        for (int i = 1; i < n; i++) {
            ans[i] = (char) ('a' + (k >> (n - 1 - i) & 1));
            if (ans[i] >= ans[i - 1]) {
                ans[i]++;
            }
        }

        return new String(ans);
    }
}