package com.oycm.week2024.No385;

public class Solution_4 {

    /**
     * 3045. <a href="https://leetcode.cn/problems/count-prefix-and-suffix-pairs-ii/description/">统计前后缀下标对 II</a> 2328
     *
     * @param words words.length [1, 1e5]; words[i].length [1, 1e5]; 所有 words[i] 的长度之和不超过 5 * 105 。
     * @return
     */
    public long countPrefixSuffixPairs(String[] words) {
        /*
        给你一个下标从 0 开始的字符串数组 words。
        定义一个 布尔函数 isPrefixAndSuffix ，它接受两个字符串参数 str1 和 str2：
            当 str1 同时是 str2 的前缀（prefix）和后缀（suffix）时，isPrefixAndSuffix(str1, str2) 返回 true，否则返回 false。
        以整数形式，返回满足 i < j 且 isPrefixAndSuffix(words[i], words[j]) 为 true 的下标对 (i, j) 的 数量 。
         */
        /*
        如果只看字符串前缀匹配，则倒序遍历 words 数组，维护经过该路径的数量，路径匹配则累加数量即可。
        后缀匹配，可以看作 str1 和 str2 同时反转后的前缀是否匹配，是否匹配可同上判断。
        题解思路：上面两点有以下结论，长为 m 的字符串 s，s 既是 t 的前缀，又是 t 的后缀，则 t 长为 m 的前后缀必须相等。
        如果 s = "abc", t 的一种格式：abc...abc
        但是如何判断 两个匹配的路径，是否同时经过 j ?
         */
        /*
        z 函数：
        约定：字符串下标以 0 为起点。
        对于个长度为 n 的字符串 。定义函数 z[i] 表示 s 和 s[i, n-1]（即以 s[i] 开头的后缀）的最长公共前缀（LCP）的长度。
        被称为 s 的 z 函数。特别地，z[0] = 0（因为 abc 和从下标 0 开始的字符就是自己，一定匹配）。
         */
        return 0;
    }


    public int[] squareZ(char[] cs) {
        /*
        n^2 初始化 z 函数
         */
        int n = cs.length;
        int[] z = new int[cs.length];

        for (int i = 1; i < n; i++) {
            /*
            计算 [1, n-1] 开头的后缀 和 字符串 s LCP
            如果字符串都相同的情况下，会执行 n-1 + n-2 + ... + 1
             */
            while (i + z[i] < n && cs[z[i]] == cs[i + z[i]]) z[i]++;
        }

        return z;
    }

}
