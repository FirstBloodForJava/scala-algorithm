package com.oycm.datastructure.stack.parse;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.IntStream;

public class Solution_3 {

    /**
     * 394. <a href="https://leetcode.cn/problems/decode-string/description/">字符串解码</a>
     * <p>
     * 编码规则为: k[encoded_string], 表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
     *
     * @param s
     * @return
     */
    public String decodeString(String s) {
        /*
        题解:
         */
        if (s.isEmpty()) {
            return s;
        }

        // s[0] 是字母
        if (Character.isLetter(s.charAt(0))) {
            // 分离出 s[0]，解码剩下的
            return s.charAt(0) + decodeString(s.substring(1));
        }

        // s[0] 是数字，后面至少有一对括号
        int i = s.indexOf('['); // 找左括号
        int balance = 1; // 左括号个数减去右括号个数
        for (int j = i + 1; ; j++) {
            char c = s.charAt(j);
            if (c == '[') {
                balance++;
            } else if (c == ']') {
                balance--;
                if (balance == 0) { // 找到与 s[i] 匹配的右括号 s[j]
                    int k = Integer.parseInt(s.substring(0, i));
                    String t = decodeString(s.substring(i + 1, j));
                    StringBuilder sb = new StringBuilder();
                    IntStream.range(0, k).forEach(o -> sb.append(t));
                    return sb.append(decodeString(s.substring(j + 1)))
                            .toString();
                }
            }
        }
    }

    private int i = 0;

    private String decode(char[] cs) {
        StringBuilder res = new StringBuilder();
        int k = 0;
        while (i < cs.length) {
            char c = cs[i];
            i++;
            if (Character.isLetter(c)) {
                res.append(c);
            } else if (Character.isDigit(c)) {
                k = k * 10 + (c - '0');
            } else if (c == '[') {
                // 递归
                String decode = decode(cs);
                IntStream.range(0, k).forEach(o -> res.append(decode));
                k = 0;
            } else {
                // 递归结束
                break;
            }
        }
        return res.toString();
    }

    public static String stackDecode(char[] cs) {
        Deque<Pair> stack = new ArrayDeque<>();
        StringBuilder res = new StringBuilder();
        int k = 0;
        for (char c : cs) {
            if (Character.isLetter(c)) {
                res.append(c);
            } else if (Character.isDigit(c)) {
                k = k * 10 + (c - '0');
            } else if (c == '[') {
                // 递归
                stack.push(new Pair(res.toString(), k));
                res.setLength(0);
                k = 0;
            } else {
                // 递归结束 head.s + k * res;
                Pair head = stack.pop();
                StringBuilder temp = new StringBuilder();
                for (int j = 0; j < head.k; j++) {
                    temp.append(res);
                }
                res = new StringBuilder(head.s).append(temp);
            }
        }
        return res.toString();
    }

    static class Pair {
        String s;
        int k;

        public Pair(String s, int k) {
            this.s = s;
            this.k = k;
        }
    }

    public static void main(String[] args) {
        System.out.println(stackDecode("3[a]2[bc]".toCharArray()));
    }

}
