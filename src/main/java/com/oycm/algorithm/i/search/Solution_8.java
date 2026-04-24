package com.oycm.algorithm.i.search;

import java.util.ArrayList;
import java.util.List;

public class Solution_8 {

    /**
     * 2002. <a href="https://leetcode.cn/problems/maximum-product-of-the-length-of-two-palindromic-subsequences/description/">两个回文子序列长度的最大乘积</a> 1869
     *
     * @param s s.length() [2, 12]
     * @return
     */
    public int maxProduct(String s) {
        /*
        找到 s 中两个 不相交回文子序列 ，使得它们长度的 乘积最大。
        两个子序列在原字符串中如果没有任何“相同下标的字符”，则它们是“不相交”的。两个回文串不能选同一个字符
         */
        /*
        思路：生成所有两个不相交的子序列方案，求每个子序列最长的回文子序列
        怎么求字符串中最长的回文子序列？
        题解思路：不用在子序列中求最长的回文子序列，一个字符一共三种操作：不选；选入一个子序列；选入另一个子序列
        最终判断两个子序列是否为回文串
        todo 状态压缩 DP
         */
        dfs(0, s, new ArrayList<>(), new ArrayList<>());
        return ans;
    }
    int ans = 0;
    public void dfs(int i, String s, List<Character> a, List<Character> b) {
        if (i == s.length()) {
            if (a.size() * b.size() > ans && isPalindrome(a) && isPalindrome(b)) {
                ans = a.size() * b.size();
            }
            return;
        }
        // 不选
        dfs(i + 1, s, a, b);

        // 选入一个子序列
        a.add(s.charAt(i));
        dfs(i + 1, s, a, b);
        a.remove(a.size() - 1);

        // 选入另一个子序列
        b.add(s.charAt(i));
        dfs(i + 1, s, a, b);
        b.remove(b.size() - 1);
    }

    public boolean isPalindrome(List<Character> cs) {
        int l = 0, r = cs.size() - 1;
        while (l < r) {
            if (cs.get(l++) != cs.get(r--)) {
                return false;
            }
        }
        return true;
    }

}
