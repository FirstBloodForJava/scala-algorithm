package com.oycm.datastructure.stack.parse;

import java.util.stream.IntStream;

public class Solution_3 {

    /**
     * 394. <a href="https://leetcode.cn/problems/decode-string/description/">字符串解码</a>
     *
     * 编码规则为: k[encoded_string], 表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
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

}
