package com.oycm.week2022.No274;

public class Solution_1 {

    /**
     * 2124. <a href="https://leetcode.cn/problems/check-if-all-as-appears-before-all-bs/description/">检查是否所有 A 都在 B 之前</a> 1202
     *
     * @param s
     * @return
     */
    public boolean checkString(String s) {
        /*
        给你一个 仅 由字符 'a' 和 'b' 组成的字符串  s。
        如果字符串中 每个 'a' 都出现在 每个 'b' 之前，返回 true ；否则，返回 false 。
         */
        /*
        出现 b 之后不能出现 a，s 不包含 ba 即可
         */
        boolean foundB = false;
        for (char c : s.toCharArray()) {
            if (!foundB && c == 'b') {
                foundB = true;
            }
            if (foundB && c == 'a') {
                return false;
            }
        }

        return true;
    }

}
