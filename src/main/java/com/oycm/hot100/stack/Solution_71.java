package com.oycm.hot100.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_71 {

    /**
     * 394. <a href="https://leetcode.cn/problems/decode-string/description/">字符串解码</a>
     *
     * @param s
     * @return
     */
    public String decodeString(String s) {
        /*
        给定一个经过编码的字符串，返回它解码后的字符串。
        编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
         */
        /*
        题解思路：
        明确 s 中有哪些类型：
            基础，不含括号，s = abs；
            嵌套，s = 2[a3[b]]；
            组合，s = 2[a]abs3[b]
        分类讨论：
            如果 s 是空串，返回空串；
            如果 s[0] 是字母，可以递归到 s [1, n-1]
                如果 s 是基础类型，s = abs，a + bc 任然是 abs
                如果 s 是组合类型，s = ab2[ac]，s = a + ab2[ac]，后续可以继续递归解码
            否则 s[0] 一定是数组，这意味者 s 至少包含一对括号。
                找到第一个左括号的下标 i；
                找到与第一个左括号匹配的右括号下标 j，注意：对于嵌套类型，需要忽略中间内层的括号。
                可以用一个变量记录 左括号 - 右括号 数量，当数量为 0 时，找到就是第一个匹配的右括号
                把 s 分为三部分：
                    [0, i-1] 转换成数字 k；
                    [i+1, j-1] 继续递归解码，得到结果 a。
                    [j+1, n-1] 继续递归解码，得到结果 b。
                返回 k * a + b
         */
        if (s.isEmpty()) return s;
        if (Character.isLetter(s.charAt(0))) {
            return s.charAt(0) + decodeString(s.substring(1));
        }
        int i = s.indexOf('[');
        int balance = 1;
        int j = i + 1;
        while (balance > 0) {
            if (s.charAt(j) == '[') {
                balance++;
            } else if (s.charAt(j) == ']') {
                balance--;
            }
            j++;
        }
        // 这里找到的 j 已经 +1 了
        int k = Integer.parseInt(s.substring(0, i));
        String a = decodeString(s.substring(i + 1, j - 1));
        String b = decodeString(s.substring(j));
        StringBuilder sb = new StringBuilder();

        return sb.append(b).toString();
    }

    public String decodeString_1(String s) {
        StringBuilder res = new StringBuilder();
        Deque<Pair> st = new ArrayDeque<>();
        int k = 0;
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                res.append(c);
            } else if (Character.isDigit(c)) {
                k = k * 10 + (c - '0');
            } else if (c == '[') {
                // 开始递归
                st.push(new Pair(res.toString(), k));
                res.setLength(0);
                k = 0;
            } else {
                Pair p = st.pop();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < p.k; i++) {
                    sb.append(res);
                }
                res = new StringBuilder(p.s).append(sb);
            }
        }
        return res.toString();
    }

    public record Pair(String s, int k) {
    }

}
